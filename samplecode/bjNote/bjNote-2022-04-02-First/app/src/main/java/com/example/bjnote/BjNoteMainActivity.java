package com.example.bjnote;

import static com.example.bjnote.constants.Constants.ExternalDownloadsFolderPath;
import static com.example.bjnote.constants.Constants.bjDefaultFileName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BjNoteMainActivity extends AppCompatActivity {
    private final static String TAG = "BjNoteMainActivity";


    AppCompatEditText bjEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bjEditText = findViewById(R.id.bj_edit_text);
        saveButton = findViewById(R.id.save_button);

        // Read BJ Note Contents
        String notes = ReadTextFile(ExternalDownloadsFolderPath + "/" + bjDefaultFileName);
        bjEditText.setText(notes);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bjEditTextContents = String.valueOf(bjEditText.getText());

                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File file = new File(ExternalDownloadsFolderPath, bjDefaultFileName);
                    try{
                        FileWriter fw = new FileWriter(file, false);
                        fw.write(bjEditTextContents);
                        fw.close();
                    } catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(BjNoteMainActivity.this,"It is not saved.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    Toast.makeText(BjNoteMainActivity.this,"It is not moundted",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BjNoteMainActivity.this, "It is saved ...", Toast.LENGTH_SHORT).show();
            }
        });


        //TODO: Tools
        String bjNote = "bjNote";
        String nowTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String defaultFileName = bjNote + nowTimeStamp;

    }

    public String ReadTextFile(String path){
        StringBuffer strBuffer = new StringBuffer();
        try{
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                strBuffer.append(line+"\n");
            }
            reader.close();
            is.close();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return strBuffer.toString();
    }


}



//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String bjEditTextContents = String.valueOf(bjEditText.getText());
//                try {
//                    FileOutputStream fos = openFileOutput(bjDefaultFileName, MODE_PRIVATE);
//                    fos.write(bjEditTextContents.getBytes());
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Toast.makeText(BjNoteMainActivity.this, "It is saved ...", Toast.LENGTH_SHORT).show();
//            }
//        });
