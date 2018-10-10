package com.acknowbox.project.acknowbox.Utils;

import android.util.Log;

import com.acknowbox.project.acknowbox.QuestionAnswer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by punam on 14/3/16.
 */
public class Webutils {
    private static final String SignupUri = "http://50.62.22.176:8080/Feedbox/user/createUser/";
    private static final String LoginUri = "http://50.62.22.176:8080/Feedbox/user/userLogin/";
    private static final String QuestionAnswerUri="http://50.62.22.176:8080/Feedbox/user/QAlist/";

    public static Boolean ValidateUser(String musername, String mpassword, String mRe_enter_pass) throws IOException{

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SignupUri);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("password", mpassword);
            jsonParams.put("re_enter_pass",mRe_enter_pass);
            jsonParams.put("username",musername);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = new StringEntity(jsonParams.toString());
        httpPost.setEntity(entity);
        httpPost.addHeader(HTTP.CONTENT_TYPE,"application/json");
        HttpResponse res = httpClient.execute(httpPost);

        InputStream in = res.getEntity().getContent();


        StringBuffer buffer = new StringBuffer();
        int count;
        byte[] arr = new byte[1024];

        while ((count = in.read(arr)) != -1) {
            buffer.append(new String(arr, 0, count));
        }
        in.close();
        try {
            JSONObject json = new JSONObject(buffer.toString());
            Log.e("WebUtil res:", "" + json);
            String resultData = json.getString("UserAdded");
            if("User added successfully".equalsIgnoreCase(resultData)){
                return true;
            }else{
                return false;
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return true;
    }
    public static Boolean ValidateUserLogin(String musername, String mpassword) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(LoginUri);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("password", mpassword);
            jsonParams.put("username", musername);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = new StringEntity(jsonParams.toString());
        httpPost.setEntity(entity);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        HttpResponse res = httpClient.execute(httpPost);

        InputStream in = res.getEntity().getContent();


        StringBuffer buffer = new StringBuffer();
        int count;
        byte[] arr = new byte[1024];
        try {
           while ((count = in.read(arr)) != -1) {
              buffer.append(new String(arr, 0, count));
           }
           in.close();

            JSONObject json = new JSONObject(buffer.toString());
            Log.e("WebUtil res:", "" + json);
            String loginData = json.getString("login");
            String sessionId = json.getString("sessionId");

            if ("successful".equalsIgnoreCase(loginData)) {
                return true;
            } else {
                return false;
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }
    public static Boolean ValidateQuestion_answer() throws IOException {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(QuestionAnswerUri);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse httpResponse = httpClient.execute(httpPost);

            //Status Line

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = httpResponse.getEntity();
                String data = EntityUtils.toString(entity);

                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("QA");

                for (int i = 0; i < jsonArray.length(); i++) {
                    QuestionAnswer qa = new QuestionAnswer();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    //String name=jsonObject1.getString("Ques name");
                    qa.setQuestion(jsonObject1.getString("Ques name"));
                    qa.setAns1(jsonObject1.getString("answer1"));
                    qa.setAns3(jsonObject1.getString("answer3"));
                    qa.setAns2(jsonObject1.getString("answer2"));
                    qa.setAns4(jsonObject1.getString("answer4"));

                }
            }
            return true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}




