package com.example.yangdada.qrcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import zxing.activity.CaptureActivity;
import zxing.encoding.EncodingHandler;

public class MainActivity extends AppCompatActivity {

    Button scanButton,creatButton;
    TextView scanTv;
    EditText et;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton = (Button)findViewById(R.id.b1);
        scanTv = (TextView)findViewById(R.id.tv1);
        creatButton = (Button)findViewById(R.id.b2);
        img = (ImageView)findViewById(R.id.img);
        et = (EditText)findViewById(R.id.et1);

        creatButton.setOnClickListener(new mClick());

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你可以扫描二维码或者条形码", Toast.LENGTH_LONG).show();
                Intent startScan = new Intent(MainActivity.this, CaptureActivity.class);
                //startActivity(startScan);
                startActivityForResult(startScan, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String result = data.getExtras().getString("result");
            scanTv.setText(result);
        }
    }

    public class mClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            String in = et.getText().toString();
            if(in.equals("")){
                Toast.makeText(MainActivity.this,"请输入文本",Toast.LENGTH_LONG).show();
            }else{
                try {

                    Bitmap qrcode = EncodingHandler.createQRCode(in,400);
                    img.setImageBitmap(qrcode);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
