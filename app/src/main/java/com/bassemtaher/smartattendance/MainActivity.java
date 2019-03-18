package com.bassemtaher.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    ImageView img;
    Button stubutton;
    Button ssbutton;
    Button docbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stubutton=findViewById(R.id.stuB);
        ssbutton=findViewById(R.id.ssB);
        docbutton=findViewById(R.id.docB);
        text=findViewById(R.id.textView2);
        img=findViewById(R.id.imageView);
        stubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });
        docbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });

    }
    public void openActivity2(){

        Intent intent=new Intent(this, loginStudent.class);
        startActivity(intent);

    }
    public void openActivity4(){

        Intent intent=new Intent(this, loginDr.class);
        startActivity(intent);

    }

}