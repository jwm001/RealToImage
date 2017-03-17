package com.family.jww.realtoimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextWord;
    private Button buttonSave;
    private Button buttonPractise;
    private ExternalSD externalSD;
    private String folderName =ExternalSD.getSDPath() + "/" + "MyAPPFolder";
    static String fileName =ExternalSD.getSDPath() + "/" + "MyAPPFolder" + "/" + "think.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialComponot();
        buttonPractise.setOnClickListener(listener);
        buttonSave.setOnClickListener(listener);

    }

    private View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()){
                case R.id.buttonSave:
                    String inputWord = editTextWord.getText().toString();
                    if (!inputWord.isEmpty()){
                        externalSD.save(folderName,fileName,inputWord);
                        editTextWord.setText("");
                    } else{
                        Toast.makeText(MainActivity.this,"please input word",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.buttonPractise:
                    jump();
                    break;
            }
        }
    };

    private void jump() {

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, PractiseActivity.class);
        startActivity(intent);

    }



    private void initialComponot() {
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextWord = (EditText) findViewById(R.id.editTextWord);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonPractise = (Button) findViewById(R.id.buttonPractise);
        externalSD = new ExternalSD();
//        folderName = getSDPath() + "/" + "MyAPPFolder";
//        fileName = getSDPath() + "/" + "MyAPPFolder"+ "/" + "think.txt";
    }

}
