package com.family.jww.realtoimage;

import android.app.Activity;
import android.content.Intent;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextWord;
    private Button buttonSave;
    private Button buttonPractise;
    public static final String fileName="resource.txt";

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
                    save();
                    editTextWord.setText("");
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

    private void save() {
        String content=editTextWord.getText().toString()+" ";

        try {
            /* �����û��ṩ���ļ������Լ��ļ���Ӧ��ģʽ����һ�������.�ļ�����ϵͳ��Ϊ�㴴��һ���ģ�
             * ����Ϊʲô����ط�����FileNotFoundException�׳�����Ҳ�Ƚ����ơ���Context�������������
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * ��һ�������������ļ����ƣ�ע��������ļ����Ʋ��ܰ����κε�/����/���ַָ�����ֻ�����ļ���
             *          ���ļ��ᱻ������/data/data/Ӧ������/files/chenzheng_java.txt
             * �ڶ��������������ļ��Ĳ���ģʽ
             *          MODE_PRIVATE ˽�У�ֻ�ܴ�������Ӧ�÷��ʣ� �ظ�д��ʱ���ļ�����
             *          MODE_APPEND  ˽��   �ظ�д��ʱ�����ļ���ĩβ����׷�ӣ������Ǹ��ǵ�ԭ�����ļ�
             *          MODE_WORLD_READABLE ����  �ɶ�
             *          MODE_WORLD_WRITEABLE ���� �ɶ�д
             *  */
            FileOutputStream outputStream = openFileOutput(fileName, Activity.MODE_APPEND);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "sucessful", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initialComponot() {
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextWord = (EditText) findViewById(R.id.editTextWord);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonPractise = (Button) findViewById(R.id.buttonPractise);
    }
}
