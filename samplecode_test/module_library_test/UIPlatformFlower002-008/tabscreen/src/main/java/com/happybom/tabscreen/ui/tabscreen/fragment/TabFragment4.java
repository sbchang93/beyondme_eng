package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.happybom.tabscreen.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

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

    KeyStore.Entry rsaEntry;
    Certificate certificate;

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
        initKeySetup(getActivity()); // setup AES Secret Key

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
            String encrptedText = sharedPreference.getString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, "");
            String decrytpedSharedPrefText = decryptWithAes(encrptedText);
            encryptedSharedPrefEditText.setText(decrytpedSharedPrefText);
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
    void initKeySetup(Context context) {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
            boolean containsAlias = keyStore.containsAlias(ALIAS_AES);

            if (!containsAlias) {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                Calendar start = Calendar.getInstance(Locale.ENGLISH);
                Calendar end = Calendar.getInstance(Locale.ENGLISH);
                end.add(Calendar.YEAR, 1);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                        .setAlias(ALIAS_AES)
                        .setSubject(new X500Principal("CN=" + ALIAS_AES))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();
                kpg.initialize(spec);
                kpg.generateKeyPair();
            }
            rsaEntry = keyStore.getEntry(ALIAS_AES, null);
            certificate = ((KeyStore.PrivateKeyEntry) rsaEntry).getCertificate();
            rsaEntry = rsaEntry;
        } catch ( NoSuchProviderException | KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

//    void initKeySetup() {
//        try {
//            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
//            keyStore.load(null);
//            boolean containsAlias = keyStore.containsAlias(ALIAS_AES);
//
//            if (!containsAlias) {
//                final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
//                final KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(ALIAS_AES,
//                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
//                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
//                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//                        .build();
//                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//                random.setSeed("1234567890123456".getBytes("UTF-8"));
//                keyGenerator.init(keyGenParameterSpec, random);
//                secretKeyAes = keyGenerator.generateKey();
//            } else {
//                final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(ALIAS_AES, null);
//                secretKeyAes = secretKeyEntry.getSecretKey();
//            }
//            secretKeyAes = secretKeyAes;
//        } catch ( NoSuchProviderException | KeyStoreException | CertificateException | IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | UnrecoverableEntryException e) {
//            e.printStackTrace();
//        }
//    }


    public boolean isSecretKeyEmpty() {
        return TextUtils.isEmpty(sharedPreference.getString(ENCRYPTED_SHARED_PREF_EDIT_TEXT, ""));
    }


    String ecryptWithAes(String plainText) {
        byte[] secretByte = new byte[16];
        byte[] certifcateByte = certificate.getPublicKey().getEncoded();
        for(int i=0; i<16; i++)
            secretByte[i] = certifcateByte[i];
        //byte[] secretByte = secretKeyAes.getEncoded();
        SecretKey skey = new SecretKeySpec(secretByte, "AES");
        try {
            byte[] iv = new byte[GCM_IV_LENGTH];
            (new SecureRandom()).nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivSpec);

            byte[] ciphertext = cipher.doFinal(plainText.getBytes("UTF8"));
            byte[] encrypted = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

            String encoded = Base64.encodeToString(encrypted, Base64.DEFAULT);

            return encoded;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d(TAG, "ecryptWithAes: encrypt - exception");
        }
        return null;
    }

    String decryptWithAes(String encryptedText) {
        byte[] secretByte = new byte[16];
        byte[] certifcateByte = certificate.getPublicKey().getEncoded();
        for(int i=0; i<16; i++)
            secretByte[i] = certifcateByte[i];
        //byte[] secretByte = secretKeyAes.getEncoded();
        SecretKey skey = new SecretKeySpec(secretByte, "AES");
        try {
            byte[] decoded = Base64.decode(encryptedText, Base64.DEFAULT);

            byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.DECRYPT_MODE, skey, ivSpec);

            byte[] decryptedCiphertext = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);

            String result = new String(decryptedCiphertext, "UTF8");

            return result;
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            Log.d(TAG, "(for Transactions) : decrypt - exception");
        }
        return null;
    }

}
