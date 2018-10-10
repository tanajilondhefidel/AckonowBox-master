package com.acknowbox.project.acknowbox.Thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.acknowbox.project.acknowbox.Utils.Webutils;

import java.io.IOException;

/**
 * Created by punam on 14/3/16.
 */
public class ThreadUserLogin extends AsyncTask<Object,Object,Boolean>
{
    private Handler mhandler;
    private ProgressDialog mprogressdialog;
    private Context context;
    String musername,mpassword,mre_enter_pass;

    public ThreadUserLogin(Context context, String username, String password,Handler handler)
    {
        this.context = context;
        musername = username;
        mpassword = password;
        mhandler = handler;


    }
    @Override
    protected Boolean doInBackground(Object... params) {
        try {
            return Webutils.ValidateUserLogin(musername, mpassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        mprogressdialog.dismiss();

        Message message = new Message();
        message.obj = result;
        mhandler.sendMessage(message);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mprogressdialog = new ProgressDialog(context);
        mprogressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mprogressdialog.setMessage("Loading...");
        mprogressdialog.show();
    }
}