package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements getDataFromNativeClass{

    // Used to load the 'native-lib' library on application startup.
     NativeClass nativeClass;
     EditText emailfield,passwordfield;
     Button submit;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailfield=findViewById(R.id.emailtext);
        passwordfield=findViewById(R.id.passwordtext);
        submit=findViewById(R.id.submitbutton);
        tv = findViewById(R.id.textview);
        nativeClass=new NativeClass(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailfield.getText().toString();
                String password = passwordfield.getText().toString();

                if(TextUtils.isEmpty(email)){
                    emailfield.setError("Field empty!");
                    emailfield.requestFocus();
                }else if(TextUtils.isEmpty(password)){
                    passwordfield.setError("Field empty!");
                    passwordfield.requestFocus();
                }else {
                    nativeClass.setVerificationResponseFromJNI(email,password);
                }
            }
        });

    }
    @Override
    public void getResult(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(result);
            }
        });
    }
}
