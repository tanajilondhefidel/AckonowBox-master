package com.acknowbox.project.acknowbox.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by punam on 14/3/16.
 */
public class Util
{
    private static Util mContext = null;
    public static String SESSION_ID = "SESSION_ID";



    public static Util getInstance() {
        if (mContext == null) {
            mContext = new Util();
        }
        return mContext;
    }

    public boolean isOnline(Context _act) {
        try {
            final ConnectivityManager conManager = (ConnectivityManager) _act.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo netInfo = conManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void showAlertDialog(Context context, String message, String title) {
        try {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle(title);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setNeutralButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
