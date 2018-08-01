package com.example.a16022916.todolistpd;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;


/**
 * Created by 16022916 on 15/11/2017.
 */

public class ListActivity extends AppCompatActivity {
    public DatabaseHelper db = new DatabaseHelper(this);

    private static final String TAG = "ListActivity";

//    final SQLiteDatabase myDB = this.openOrCreateDatabase("toDoList",MODE_PRIVATE,null);

    ListView lvToDo;
    ArrayList<ToDoItem> toDoList;
    CustomAdapter caToDo;

    EditText etName;
    EditText etTime;
    Button btnSubmit;
    Button btnDelete;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        lvToDo = (ListView) findViewById(R.id.ListViewToDo);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnLoad = (Button) findViewById(R.id.btnLoad);
//        toDoList = new ArrayList<ToDoItem>();
        toDoList = db.getAllItems();
        caToDo = new CustomAdapter(this, R.layout.to_do_item_row, toDoList);
        lvToDo.setAdapter(caToDo);


//        ToDoItem testing = new ToDoItem("Check2",5,false);
//        db.addData(testing);
//        toDoList.add(testing);
//        caToDo.notifyDataSetChanged();


//        for (ToDoItem tdItem : toDoList) {
//            String log = "Name: " + tdItem.getName() + " ,Time: " + tdItem.getTime() + " ,Checked: " + tdItem.isChecked();
//            Log.d("Name: ", log);
//        }

        //populate toDoListView

//        toDoList = mDatabaseHelper.getAllItems();
//        caToDo.notifyDataSetChanged();

        //storage retrieval... but load will not be edited, never commit
//        ArrayList<ToDoItem> load = new ArrayList<ToDoItem>();
//        load.add(new ToDoItem("Abigail","LOL",false));
//        load.add(new ToDoItem("Alicia","LOL",true));
//        load.add(new ToDoItem("Justin","LOL",false));
//        for(int i = 0; i<load.size();i++) {
//            toDoList.add(load.get(i));
//        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName = (EditText) findViewById(R.id.editTextName);
                etTime = (EditText) findViewById(R.id.editTextTime);

                if (etTime.getText().toString().equals("") || etName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill in the details", Toast.LENGTH_SHORT).show();
                } else {
                    String name = etName.getText().toString();
                    int time = Integer.parseInt(etTime.getText().toString());
                    ToDoItem item = new ToDoItem(toDoList.size()+1,name, time, false);
                    db.addData(item);
                    syncListToDatabase();
                    syncDatabaseToList();
                    etName.setText("");
                    etTime.setText("");
                }


            }


        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toDoList.size() != 0) {
                    db.deleteItem(toDoList.get(0));
                    syncDatabaseToList();
                } else {
                    Toast.makeText(getApplicationContext(), "No item to delete!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncListToDatabase();
                syncDatabaseToList();
            }
        });




        lvToDo.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG,"Working");
                Toast.makeText(getApplicationContext(), String.valueOf(parent.getItemIdAtPosition(position)),Toast.LENGTH_SHORT).show();
            }
        });
//        lvToDo.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.v(TAG,"UMMM IM WORKGIN");
//                Toast.makeText(getApplicationContext(), String.valueOf(parent.getItemIdAtPosition(position)),Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//
    protected void onStop(){
        super.onStop();
        syncListToDatabase();
    }


    public void syncDatabaseToList(){
        toDoList.clear();
        toDoList.addAll(db.getAllItems());
        caToDo.notifyDataSetChanged();
        lvToDo.setAdapter(caToDo);
    }

    public void syncListToDatabase(){
        db.updateAllData(toDoList);
    }
//    public void saveToDB(){
//        db.updateList(toDoList.get(0));
//    }
//    @Override
//    public void onResume(){
//        super.onResume();
//        toDoList = db.getAllItems();
//    }




//    public void AddData(String newItemName, String newItemDesc, boolean newisChecked){
//        String stringIsChecked;
//        if(newisChecked == true){
//            stringIsChecked = "true";
//        } else {
//            stringIsChecked = "false";
//        }
//        boolean insertData = mDatabaseHelper.addData(newItemName,newItemDesc,stringIsChecked);
//
//        if(insertData){
//            toastMessage("Data Successfully Inserted!");
//        } else {
//            toastMessage("Something went wrong");
//        }
//    }
//    private void toastMessage(String message){
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public boolean onTouch(View v, MotionEvent me) {
//
//        float x = me.getX();
//        float y = me.getY()
//        return false;
//    }
}