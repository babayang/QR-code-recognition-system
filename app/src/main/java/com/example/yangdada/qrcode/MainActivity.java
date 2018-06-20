package com.example.yangdada.qrcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import com.example.yangdada.qrcode.encoding.EncodingHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE = 111;
    public static final int CALL_ME = 100;

    Button scanButton,fButton,bdh,bsc,bddh,bfdx;
    TextView scanTv;
    EditText et;

    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton = (Button)findViewById(R.id.b1);
        scanTv = (TextView)findViewById(R.id.tv1);

        fButton = (Button)findViewById(R.id.b3);
        bddh=(Button)findViewById(R.id.bddh);
        bddh.setOnClickListener(new mClick());
        et = (EditText)findViewById(R.id.et1);
        bdh = (Button)findViewById(R.id.bdh);
        bdh.setOnClickListener(new mClick());
        bsc=(Button)findViewById(R.id.bsc);
        bsc.setOnClickListener(new mClick());
        bfdx=(Button)findViewById(R.id.bfdx);
        bfdx.setOnClickListener(new mClick());



        fButton.setOnClickListener(new mClick());
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

        if(resultCode == RESULT_OK)
        {
            String result = data.getExtras().getString("result");
            scanTv.setText(result);
        }

    }

    public class mClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
             if(v == fButton)
            {

                String url = scanTv.getText().toString();
                wb = (WebView)findViewById(R.id.wb);
                wb.loadUrl("http://" + url);


            }
            if(v==bdh)
            {
                Intent intent = new Intent(MainActivity.this,Second.class);
                startActivity(intent);
            }
            if(v==bsc)
            {
                Intent intent = new Intent(MainActivity.this,Third.class);
                startActivity(intent);
            }
            if(v==bddh)
            {

                String phone = scanTv.getText().toString().trim();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
               intent.setData(Uri.parse("tel:" + phone));
               startActivity(intent);
            }
            if(v==bfdx)
            {
                String content =et.getText().toString();
                String phone = scanTv.getText().toString().trim();
                SmsManager sm = SmsManager.getDefault();
                List<String> sms = sm.divideMessage(content);
                for (String smslist :sms){
                    sm.sendTextMessage(phone,null,smslist,null,null);
                }
            }




        }
    }
}
