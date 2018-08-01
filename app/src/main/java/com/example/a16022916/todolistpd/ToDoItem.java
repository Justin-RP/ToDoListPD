package com.example.a16022916.todolistpd;


import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by 16022916 on 15/11/2017.
 */

public class ToDoItem {

    private int id;
    private String itemName;
    private int time;
    private boolean isChecked;

    public ToDoItem(int id, String itemName,int time,boolean isChecked){
        this.id = id;
        this.isChecked = isChecked;
        this.itemName = itemName;
        this.time = time;
    }

    public ToDoItem(String itemName,int time,boolean isChecked){
        this.isChecked = isChecked;
        this.itemName = itemName;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
