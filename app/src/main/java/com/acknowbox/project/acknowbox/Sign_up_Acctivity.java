package com.acknowbox.project.acknowbox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acknowbox.project.acknowbox.Thread.ThreadUserLogin;
import com.acknowbox.project.acknowbox.Thread.ThreadUserSignup;
import com.acknowbox.project.acknowbox.Utils.Util;

/**
 * Created by punam on 14/3/16.
 */
public class Sign_up_Acctivity extends Activity implements View.OnClickListener
{
    EditText username,password,re_enter_pass;
    private  String mUserName=null;
    private  String mPassword=null;
    private  String mReenterpass=null;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        initializeView();
    }

    private void initializeView()
    {
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        re_enter_pass=(EditText)findViewById(R.id.re_enter_pass);
        sign_up=(Button)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);
    }
    public boolean validateInfo(){
        boolean isValidated=true;
        try {

            if (!Util.getInstance().isOnline(this)){
                Util.getInstance().showToast(this,getResources().getString(R.string.internet_alert_message));
                isValidated=false;
            }else if( re_enter_pass==null || re_enter_pass.getText().toString().equalsIgnoreCase("")){
                isValidated=false;
                Util.getInstance().showAlertDialog(this,getResources().getString(R.string.Re_Enter_pass),getResources().getString(R.string.alert_message_header));
            }else if (username==null || username.getText().toString().equalsIgnoreCase("")){
                isValidated=false;
                Util.getInstance().showAlertDialog(this,getResources().getString(R.string.email_empty_alert),getResources().getString(R.string.alert_message_header));
            }else if (password==null || password.getText().toString().equalsIgnoreCase("")){
                isValidated=false;
                Util.getInstance().showAlertDialog(this,getResources().getString(R.string.password_empty_alert),getResources().getString(R.string.alert_message_header));
            }
        }catch (Exception e){
            e.printStackTrace();
            return  false;}
        return  isValidated;
    }

    @Override
    public void onClick(View v)
    {
        if (validateInfo()) {
            new ThreadUserSignup(Sign_up_Acctivity.this,username.getText().toString(),password.getText().toString(),re_enter_pass.getText().toString(),new HandlerStatus()).execute((Object[])null);

        }else{

        }

    }
    class HandlerStatus extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            boolean res = (Boolean)msg.obj;

            if(res) {


                Toast.makeText(Sign_up_Acctivity.this, "Successfully Login...!!!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Sign_up_Acctivity.this,Login_Activity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(Sign_up_Acctivity.this, "No Such a User...", Toast.LENGTH_LONG).show();
            }
        }
    }
}
