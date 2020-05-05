package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.happybom.tabscreen.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import io.reactivex.Observable;


// EditBox Reference Homepage
// https://itpangpang.xyz/191
// https://recipes4dev.tistory.com/61

// AES Algorithm
// https://stackoverrun.com/ko/q/12660507
// https://g-y-e-o-m.tistory.com/141
// https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html

public class TabFragment4 extends Fragment {
    private static final String TAG = "TabFragment4";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String ALIAS_AES = "com.happybom.tabscreen.ui_AES";
    private SecretKey secretKeyAes;
    private byte[] secretKeyIv;
    private final static int GCM_IV_LENGTH = 12;
    private final static int GCM_TAG_LENGTH = 16;

    private static final String SHARED_PREFERENCE_REPOSITORY = "SHARED_PREFERENCE_REPOSITORY";
    private static final String SHARED_PREF_EDIT_TEXT = "SHARED_PREF_EDIT_TEXT";
    private static final String ENCRYPTED_SHARED_PREF_EDIT_TEXT = "ENCRYPTED_SHARED_PREF_EDIT_TEXT";
    private static final String SECRET_KEY_IV_AEC = "SECRET_KEY_IV_AEC";

    SharedPreferences sharedPreference;
    EditText sharedPrefEditText;
    EditText encryptedSharedPrefEditText;

    public TabFragment4() {
    }

    public TabFragment4 newInstance(int index) {
        TabFragment4 f = new TabFragment4();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment4, container, false);
        initKeySetup(); // setup AES Secret Key

        sharedPrefEditText = (EditText) view.findViewById(R.id.shared_preference_edittext);
        sharedPrefEditText.setHint("Set Hint Text...");
        sharedPrefEditText.setHintTextColor(Color.parseColor("#007700"));
        InputFilter[] EditFilter = new InputFilter[1];
        EditFilter[0] = new InputFilter.LengthFilter(10);
        sharedPrefEditText.setFilters(EditFilter);
        sharedPrefEditText.setBackground(null);
        sharedPrefEditText.setFocusable(true);
        sharedPrefEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        sharedPrefEditText.setCursorVisible(true);

        sharedPrefEditText.addTextChangedListener(sharedPrefTextWatcher);

        sharedPreference = getActivity().getSharedPreferences(SHARED_PREFERENCE_REPOSITORY, Context.MODE_PRIVATE);
        String sharedPrefText = sharedPreference.getString(SHARED_PREF_EDIT_TEXT, "");
        sharedPrefEditText.setText(sharedPrefText);


        // Save encrypted data into Shared Preference
        encryptedSharedPrefEditText = (EditText) view.findViewById(R.id.encrypted_shared_preference_edittext);
        encryptedSharedPrefEditText.addTextChangedListener(encryptedSharedPrefTextWatcher);

        if(!isSecretKeyEmpty()) {
            String encrytpedSharedPrefText = sharedPreference.getString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, "");
            String decrytpedSharedPrefText = decryptWithAes(encrytpedSharedPrefText);
            sharedPrefEditText.setText(decrytpedSharedPrefText);
        }

        return view;
    }

    TextWatcher sharedPrefTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(sharedPrefEditText.length() >= 7) {
                sharedPrefEditText.setEnabled(false);

                String text = sharedPrefEditText.getText().toString();
                sharedPreference.edit().putString(SHARED_PREF_EDIT_TEXT, text).commit();

                Observable.just("afterTextChanged")
                        .delay(5, TimeUnit.SECONDS)
                        .subscribe(v -> {
                            sharedPrefEditText.setEnabled(true);
                        }, t ->{});
            }
        }
    };

    TextWatcher encryptedSharedPrefTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(encryptedSharedPrefEditText.length() >= 10) {
                encryptedSharedPrefEditText.setEnabled(false);

                String text = encryptedSharedPrefEditText.getText().toString();
                String encryptedText = ecryptWithAes(text);
                sharedPreference.edit().putString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, encryptedText).commit();

                Observable.just("afterTextChanged")
                        .delay(5, TimeUnit.SECONDS)
                        .subscribe(v -> {
                            encryptedSharedPrefEditText.setEnabled(true);
                        }, t ->{});
            }
        }
    };


    // AES Algorithm
    // https://stackoverrun.com/ko/q/12660507
    // https://g-y-e-o-m.tistory.com/141
    // https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html
    void initKeySetup() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
            boolean containsAlias = keyStore.containsAlias(ALIAS_AES);

            if (!containsAlias) {
                final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
                final KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(ALIAS_AES,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .build();
                keyGenerator.init(keyGenParameterSpec);
                secretKeyAes = keyGenerator.generateKey();
            } else {
                final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(ALIAS_AES, null);
                secretKeyAes = secretKeyEntry.getSecretKey();
            }
        } catch ( NoSuchProviderException | KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

    public boolean isSecretKeyEmpty() {
        return TextUtils.isEmpty(sharedPreference.getString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, ""));
    }


    String ecryptWithAes(String plainText) {
        try {
            secretKeyIv = new byte[GCM_IV_LENGTH];
            for(int i=0; i<GCM_IV_LENGTH; i++) secretKeyIv[i] = 0;

            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, secretKeyIv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeyAes, ivSpec);
//            secretKeyIv = cipher.getIV();
//            String ivString = new String(secretKeyIv);
//            sharedPreference.edit().putString(SECRET_KEY_IV_AEC, ivString).commit();

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            String encoded = Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    String decryptWithAes(String encryptedText) {
        try {
            if (secretKeyAes == null) {
                KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                keyStore.load(null);
                boolean containsAlias = keyStore.containsAlias(ALIAS_AES);
                final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(ALIAS_AES, null);
                secretKeyAes = secretKeyEntry.getSecretKey();
            }

//            String ivString  = sharedPreference.getString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, "");
//            secretKeyIv = ivString.getBytes("UTF-8");
            secretKeyIv = new byte[GCM_IV_LENGTH];
            for(int i=0; i<GCM_IV_LENGTH; i++) secretKeyIv[i] = 0;

            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            final GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, secretKeyIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeyAes, spec);

            byte[] bytes = encryptedText.getBytes("UTF-8");
            byte[] base64encryptedBytes = Base64.decode(bytes, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(base64encryptedBytes);
            return new String(decryptedBytes);

        } catch ( KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | UnrecoverableEntryException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
