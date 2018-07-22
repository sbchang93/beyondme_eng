package com.example.toronto.mystudyapp.view;

// Reference Homepage URL (참조 홈페이지 링크)
// http://webnautes.tistory.com/1094

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toronto.mystudyapp.R;

/**
 */
public class DialogFragmentExample extends DialogFragment {


    public interface OnCompleteListener{
        void onInputedData(String id, String pass);
    }

    private OnCompleteListener mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnCompleteListener) activity;
        }
        catch (ClassCastException e) {
            Log.d("DialogFragmentExample", "Activity doesn't implement the OnCompleteListener interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        builder.setView(view);
        final Button submit = (Button) view.findViewById(R.id.buttonSubmit);
        final EditText email = (EditText) view.findViewById(R.id.edittextEmailAddress);
        final EditText password = (EditText) view.findViewById(R.id.edittextPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();
                dismiss();
                mCallback.onInputedData(strEmail, strPassword);
            }
        });

        return builder.create();
    }

}
