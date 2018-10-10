package com.acknowbox.project.acknowbox;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.acknowbox.project.acknowbox.Adapter.Question_Answer_Adapter;
import com.acknowbox.project.acknowbox.Thread.ThreadUserQuestionAnswer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by punam on 15/3/16.
 */
public class Questions_Answers extends Activity
{
    private ListView lv;

    public Question_Answer_Adapter adapter;
    private List<QuestionAnswer> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_answer);
        lv = (ListView) findViewById(R.id.listview1);
        mList=new ArrayList<>();
        new ThreadUserQuestionAnswer(Questions_Answers.this,new HandlerStatus()).execute((Object[]) null);


        //We can get data from webservice here
    }
    public class HandlerStatus extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            boolean res = (Boolean)msg.obj;

            if(res)
            {
                mList.add(new QuestionAnswer());

                //Init Adapter
                adapter=new Question_Answer_Adapter(getApplicationContext(),mList);
                //Set adapter
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });

            }
            else {
                Toast.makeText(Questions_Answers.this, "No  Question and answers", Toast.LENGTH_LONG).show();
            }
        }
    }
}
