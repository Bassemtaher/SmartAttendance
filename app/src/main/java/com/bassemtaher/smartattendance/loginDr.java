package com.bassemtaher.smartattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class loginDr extends AppCompatActivity {

    private Button registerAccount;
    private EditText name,phone,password;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dr);
        registerAccount=findViewById(R.id.register_btn);
        name=findViewById(R.id.register_name_input);
        phone=findViewById(R.id.register_phone_no_input);
        password=findViewById(R.id.register_password_input);
        loading=new ProgressDialog(this);
        registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

    }

    private void createAccount()
    {

        String number=phone.getText().toString();
        String fullname=name.getText().toString();
        String pass=password.getText().toString();

        if (TextUtils.isEmpty(fullname))
        {

            Toast.makeText(this,"please wrie your name....",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(pass))
        {

            Toast.makeText(this,"please wrie your password....",Toast.LENGTH_SHORT).show();
        }


        else if (TextUtils.isEmpty(number))
        {

            Toast.makeText(this,"please wrie your number....",Toast.LENGTH_SHORT).show();
        }

        else

        {
            loading.setTitle("create account");
            loading.setMessage("please wait");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            validateName(fullname,number,pass);

        }


    }

    private void validateName(final String fullname, final String number, final String pass)
    {

        final DatabaseReference reference;
        reference= FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("doctors").child(fullname).exists()))
                {
                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("full name",fullname);
                    userdataMap.put("phone",number);
                    userdataMap.put("password",pass);
                    reference.child("doctors").child(fullname).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(loginDr.this,"Congratulations your account has been created",Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                                Intent intent=new Intent(loginDr.this,Yers.class);
                                startActivity(intent);

                            }

                            else
                            {
                                loading.dismiss();
                                Toast.makeText(loginDr.this,"network error please try again later",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });



                }
                else
                {
                    Toast.makeText(loginDr.this,"this"+fullname+"already exists",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Toast.makeText(loginDr.this,"please try again using your wright name",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(loginDr.this,MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }


}
