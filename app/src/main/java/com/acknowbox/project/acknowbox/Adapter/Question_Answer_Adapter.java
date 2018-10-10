package com.acknowbox.project.acknowbox.Adapter;


import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.acknowbox.project.acknowbox.QuestionAnswer;
import com.acknowbox.project.acknowbox.R;
import java.util.List;

/**
 * Created by punam on 16/3/16.
 */
public class Question_Answer_Adapter extends BaseAdapter implements View.OnClickListener
{
    private Context context;
    private List<QuestionAnswer> mList;

    public Question_Answer_Adapter (Context context,List<QuestionAnswer> mList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,R.layout.list_item,null);
        TextView textView=(TextView)v.findViewById(R.id.que_ans);
        ImageView v_good=(ImageView)v.findViewById(R.id.v_good);
        ImageView good=(ImageView)v.findViewById(R.id.v_good);
        ImageView moderate=(ImageView)v.findViewById(R.id.v_good);
        ImageView poor=(ImageView)v.findViewById(R.id.v_good);

        //Set text for textview
      textView.setText(mList.get(position).getQuestion());
        textView.setText("punam");
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
