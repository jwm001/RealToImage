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
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
             *          该文件会被保存在/data/data/应用名称/files/chenzheng_java.txt
             * 第二个参数，代表文件的操作模式
             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
             *          MODE_WORLD_READABLE 公用  可读
             *          MODE_WORLD_WRITEABLE 公用 可读写
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
