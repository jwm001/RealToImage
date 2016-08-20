package com.family.jww.realtoimage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;

import static android.R.id.list;

public class PractiseActivity extends AppCompatActivity implements SensorEventListener {
    private TextView[] textView;
    private SensorManager mSensorManager;
    private String[] contentSlip;
    private final static int UPDATE_TEXT =1;

    private Handler handler=new Handler() {

        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    if(contentSlip.length>0){
                        setView((int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1));
                    }else {
                        Toast.makeText(getApplicationContext(), "please input data!!!",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practise);
        init();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    private void setView(int ran1,int ran2,int ran3,int ran4,int ran5,int ran6,int ran7,int ran8,int ran9,int ran0) {

        textView[0].setText(contentSlip[ran0]);
        textView[1].setText(contentSlip[ran1]);
        textView[2].setText(contentSlip[ran2]);
        textView[3].setText(contentSlip[ran3]);
        textView[4].setText(contentSlip[ran4]);
        textView[5].setText(contentSlip[ran5]);
        textView[6].setText(contentSlip[ran6]);
        textView[7].setText(contentSlip[ran7]);
        textView[8].setText(contentSlip[ran8]);
        textView[9].setText(contentSlip[ran9]);

    }

    private void init() {
        textView[0]= (TextView) findViewById(R.id.textView10);
        textView[1]= (TextView) findViewById(R.id.textView1);
        textView[2]= (TextView) findViewById(R.id.textView2);
        textView[3]= (TextView) findViewById(R.id.textView3);
        textView[4]= (TextView) findViewById(R.id.textView4);
        textView[5]= (TextView) findViewById(R.id.textView5);
        textView[6]= (TextView) findViewById(R.id.textView6);
        textView[7]= (TextView) findViewById(R.id.textView7);
        textView[8]= (TextView) findViewById(R.id.textView8);
        textView[9]= (TextView) findViewById(R.id.textView9);

        contentSlip=read().split(" ");
    }

    private String read() {
        String content = null;
        try {
            FileInputStream inputStream = this.openFileInput(MainActivity.fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            content = new String(arrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int sensorType = event.sensor.getType();

        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {

            if (((Math.abs(values[0]) > 15 || Math.abs(values[1]) > 15 || Math.abs(values[2]) > 15)) ) {
                new MyThread().start();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度转变时，调用此方法

    }

    @Override
    protected void onStop(){

        mSensorManager.unregisterListener(this);
        super.onStop();

    }

    @Override
    protected void onPause(){

        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume(){

        super.onResume();
        //加速度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                //还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
                //根据不同应用，需要的反应速率不同，具体根据实际情况设定
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    class MyThread extends Thread
    {
        @Override
        public void run() {
            Message message = new Message();
            message.what = UPDATE_TEXT;
            handler.sendMessage(message);
        }
    }

}
