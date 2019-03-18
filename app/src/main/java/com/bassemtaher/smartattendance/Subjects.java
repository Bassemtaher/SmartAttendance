package com.bassemtaher.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bassemtaher.smartattendance.R;

public class Subjects extends AppCompatActivity {
    TextView textView;
    RadioGroup rr;
    RadioButton exp;
    RadioButton img;
    RadioButton com;
    Button button;
    String gender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        textView=findViewById(R.id.textView);
        rr=findViewById(R.id.radioG);

        com=findViewById(R.id.radioButton);
        img=findViewById(R.id.radioButton2);
        exp=findViewById(R.id.radioButton3);
        button=findViewById(R.id.button2);


        rr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==R.id.radioButton)
                    {
                        gender = com.getText().toString();
                    }
                    else if(checkedId==R.id.radioButton2)
                       {
                           gender = img.getText().toString();
                       }

                       else if (checkedId==R.id.radioButton3)
                           {
                                 gender = exp.getText().toString();

                           }



            }
        });
        Toast.makeText(this,gender,Toast.LENGTH_SHORT).show();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rr.getCheckedRadioButtonId();
                exp = (RadioButton) findViewById(selectedId);






                openActivity4();

            }
        });
    }
    public void openActivity4 (){


        Intent intent=new Intent(this,Generator.class);
        startActivity(intent);
    }
}