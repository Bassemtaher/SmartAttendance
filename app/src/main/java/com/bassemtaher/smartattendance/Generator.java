package com.bassemtaher.smartattendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Calendar;
import java.util.Date;

public class Generator extends Subjects{
    Button button1,button2;
    ImageView imageView;
    Date currentTime= Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);


        imageView =findViewById(R.id.image);
        button1=findViewById(R.id.GenerB);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= gender +currentTime;
                try{
                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);



                }
                catch (WriterException e)
                {

                    e.printStackTrace();

                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}

