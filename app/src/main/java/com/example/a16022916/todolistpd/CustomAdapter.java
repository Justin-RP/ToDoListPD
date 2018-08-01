package com.example.a16022916.todolistpd;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 16022916 on 15/11/2017.
 */


public class CustomAdapter extends ArrayAdapter {

//    public DatabaseHelper db = new DatabaseHelper(this);

    ListActivity list = new ListActivity();

    public static CheckBox checked;
    public static TextView tvName;
    TextView tvDesc;

    Context parent_context;
    int layout_id;

    ArrayList<ToDoItem> toDoList;


    public CustomAdapter(Context context, int resource, ArrayList<ToDoItem> objects){
        super(context,resource,objects);

        parent_context = context;
        layout_id = resource;
        toDoList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent,false);

        checked = (CheckBox) rowView.findViewById(R.id.checkBoxIsChecked);
        tvName = (TextView) rowView.findViewById(R.id.textViewName);
        tvDesc = (TextView) rowView.findViewById(R.id.textViewDesc);
        TextView tvTime = (TextView) rowView.findViewById(R.id.textViewTime);

        final ToDoItem currentItem = toDoList.get(position);

        String strTime = String.valueOf(currentItem.getTime());
        tvTime.setText(strTime);
        tvDesc.setText("Item");
//        if(currentItem.isChecked()){
//            tvDesc.setText("Complete");
//        }
//        else {
//            tvDesc.setText("Incomplete");
//        }
        tvName.setText(currentItem.getName());
        checked.setChecked(currentItem.isChecked());

        rowView.setTag(currentItem.getId());
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!currentItem.isChecked()){
                    Toast.makeText(getContext(),"Completed!",Toast.LENGTH_SHORT).show();
                    currentItem.setChecked(true);
                    toDoList.get(position).setChecked(true);
                    notifyDataSetChanged();

                    //change database checked
//                    tvDesc.setText("Complete");

//                    Toast.makeText(getContext(),"HI",Toast.LENGTH_SHORT).show();

                }else{
//                    tvDesc.setText("InComplete");
                    currentItem.setChecked(false);
                    toDoList.get(position).setChecked(false);
                    notifyDataSetChanged();
                }
            }
        });



        return rowView;
    }



    //extract of storage for checked
//    CompoundButton.OnCheckedChangeListener myListener = new CompoundButton.OnCheckedChangeListener() {
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
//            m
//        }
//    }
}
