package com.example.toronto.mystudyapp.view;

// Reference Homepage URL (참조 홈페이지 링크)
// http://webnautes.tistory.com/1094

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    private static final int DIALOG_REQUEST_CODE = 1234;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        return view;
    }

    void show()
    {
       DialogFragment newFragment = new DialogFragmentExample5();
        newFragment.setTargetFragment(this, DIALOG_REQUEST_CODE );
        newFragment.show(getFragmentManager(), "dialog"); //"dialog"라는 태그를 갖는 프래그먼트를 보여준다.
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DIALOG_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {
                String id = data.getExtras().getString("id");
                String pass = data.getExtras().getString("pass");

                Toast.makeText(getActivity(), id+"/"+pass,Toast.LENGTH_LONG).show();
            }
        }
    }
}

