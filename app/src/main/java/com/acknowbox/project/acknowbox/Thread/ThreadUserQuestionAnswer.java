package com.acknowbox.project.acknowbox.Thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.acknowbox.project.acknowbox.Questions_Answers;
import com.acknowbox.project.acknowbox.Utils.Webutils;

import java.io.IOException;

/**
 * Created by punam on 16/3/16.
 */
public class ThreadUserQuestionAnswer extends AsyncTask<Object,Object,Boolean>
{
    private Handler mhandler;
    private Context context;
    private ProgressDialog mprogressdialog;

    public ThreadUserQuestionAnswer(Context context,Handler handler ) {

        this.context = context;
        mhandler = handler;
    }

    @Override
    protected Boolean doInBackground(Object... params)
    {
        try {
            return Webutils.ValidateQuestion_answer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mprogressdialog = new ProgressDialog(context);
        mprogressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mprogressdialog.setMessage("Loading...");
        mprogressdialog.show();
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        mprogressdialog.dismiss();

        Message message = new Message();
        message.obj = s;
        mhandler.sendMessage(message);
    }
}
