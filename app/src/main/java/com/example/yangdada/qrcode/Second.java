package com.example.yangdada.qrcode;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yangdada on 2018/6/20.
 */
public class Second extends AppCompatActivity {
    Button b21,b22;
    EditText et21;
    TextView tv21;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        b21=(Button)findViewById(R.id.b21);
        b22=(Button)findViewById(R.id.b22);
        et21=(EditText)findViewById(R.id.et21);
        b21.setOnClickListener(new mClick());
        b22.setOnClickListener(new mClick());

    }

    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v==b21)
            {
                Toast.makeText(Second.this, "你可以扫描二维码或者条形码", Toast.LENGTH_LONG).show();
                Intent startScan = new Intent(Second.this, CaptureActivity.class);
                startScan.setAction("android.intent.action.second");
                //startActivity(startScan);
                startActivityForResult(startScan,0);

            }
            if(v==b22)
            {

            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String result = data.getExtras().getString("result");
            tv21.setText(result);
        }
    }

}
