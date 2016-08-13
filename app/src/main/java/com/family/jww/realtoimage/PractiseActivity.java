package com.family.jww.realtoimage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;

import static android.R.id.list;

public class PractiseActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private SensorManager mSensorManager;
    private String[] contentSlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practise);
        init();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    private void setView(int ran1,int ran2,int ran3,int ran4,int ran5,int ran6,int ran7,int ran8,int ran9,int ran10) {

        textView1.setText(contentSlip[ran1]);
        textView2.setText(contentSlip[ran2]);
        textView3.setText(contentSlip[ran3]);
        textView4.setText(contentSlip[ran4]);
        textView5.setText(contentSlip[ran5]);
        textView6.setText(contentSlip[ran6]);
        textView7.setText(contentSlip[ran7]);
        textView8.setText(contentSlip[ran8]);
        textView9.setText(contentSlip[ran9]);
        textView10.setText(contentSlip[ran10]);

    }

    private void init() {
        textView1= (TextView) findViewById(R.id.textView1);
        textView1= (TextView) findViewById(R.id.textView2);
        textView1= (TextView) findViewById(R.id.textView3);
        textView1= (TextView) findViewById(R.id.textView4);
        textView1= (TextView) findViewById(R.id.textView5);
        textView1= (TextView) findViewById(R.id.textView6);
        textView1= (TextView) findViewById(R.id.textView7);
        textView1= (TextView) findViewById(R.id.textView8);
        textView1= (TextView) findViewById(R.id.textView9);
        textView1= (TextView) findViewById(R.id.textView10);
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
  /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
  *的时候，瞬时加速度才会突然增大或减少。
  *所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置
  *就OK了~~~
  */
            if (((Math.abs(values[0]) > 10 || Math.abs(values[1]) > 10 || Math.abs(values[2]) > 10)) ) {
                System.out.println(Math.abs(values[0]));
                System.out.println(Math.abs(values[1]));
                System.out.println(Math.abs(values[2]));
                System.out.println((int) (Math.random()*contentSlip.length-1));
                int x1=(int) (Math.random()*contentSlip.length-1);
//                int x2=(int) (Math.random()*contentSlip.length-1);
//                int x3=(int) (Math.random()*contentSlip.length-1);
//                int x4=(int) (Math.random()*contentSlip.length-1);
//                int x5=(int) (Math.random()*contentSlip.length-1);
//                int x6=(int) (Math.random()*contentSlip.length-1);
//                int x7=(int) (Math.random()*contentSlip.length-1);
//                int x8=(int) (Math.random()*contentSlip.length-1);
//                int x9=(int) (Math.random()*contentSlip.length-1);
//                int x10=(int) (Math.random()*contentSlip.length-1);
                textView1.setText(contentSlip[x1]);
//                textView2.setText(contentSlip[x2]);
//                textView3.setText(contentSlip[x3]);
//                textView4.setText(contentSlip[x4]);
//                textView5.setText(contentSlip[x5]);
//                textView6.setText(contentSlip[x6]);
//                textView7.setText(contentSlip[x7]);
//                textView8.setText(contentSlip[x8]);
//                textView9.setText(contentSlip[x9]);
//                textView10.setText(contentSlip[x10]);


//                setView((int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1),(int) (Math.random()*contentSlip.length-1));
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

}
