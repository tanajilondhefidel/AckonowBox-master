package com.acknowbox.project.acknowbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acknowbox.project.acknowbox.Thread.ThreadUserLogin;
import com.acknowbox.project.acknowbox.Thread.ThreadUserSignup;

/**
 * Created by punam on 14/3/16.
 */
public class Login_Activity extends Activity implements View.OnClickListener {
    EditText user, pass;
    Button sign_in, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initializeView();
    }

    public void initializeView() {
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        sign_in = (Button) findViewById(R.id.signin);
        sign_up = (Button) findViewById(R.id.signup);
        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.signin:
                    if (validateInfo()) {
                        new ThreadUserLogin(Login_Activity.this,user.getText().toString(),pass.getText().toString(),new HandlerStatus()).execute((Object[])null);

                    }else{

                    }
                    break;
                case R.id.signup:
                    Intent intent=new Intent(Login_Activity.this,Sign_up_Acctivity.class);
                    startActivity(intent);
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public boolean validateInfo()
    {
        return true;
    }
        class HandlerStatus extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            boolean res = (Boolean)msg.obj;

            if(res) {


                Toast.makeText(Login_Activity.this, "Successfully Login...!!!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Login_Activity.this,Questions_Answers.class);
                startActivity(i);
            }
            else {
                Toast.makeText(Login_Activity.this, "No Such a User...", Toast.LENGTH_LONG).show();
            }
        }
    }
}
