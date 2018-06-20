package com.example.yangdada.qrcode;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangdada.qrcode.encoding.EncodingHandler;
import com.google.zxing.WriterException;

/**
 * Created by yangdada on 2018/6/20.
 */
public class Third extends AppCompatActivity {
    Button b31;
    EditText et31;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        img = (ImageView)findViewById(R.id.img31);
        b31 = (Button)findViewById(R.id.b31);
        et31=(EditText)findViewById(R.id.et31);
        b31.setOnClickListener(new mClick());
    }
    class mClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v==b31)
            {
                String in = et31.getText().toString();
                if(in.equals("")){
                    Toast.makeText(Third.this, "请输入文本", Toast.LENGTH_LONG).show();
                }else{
                    try {

                        Bitmap qrcode = EncodingHandler.createQRCode(in, 400);
                        img.setImageBitmap(qrcode);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
