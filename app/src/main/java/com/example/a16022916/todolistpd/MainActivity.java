package com.example.a16022916.todolistpd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    Button btnHoursPage;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHoursPage = (Button)findViewById(R.id.buttonToHoursPage);


        btnHoursPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = pref.edit();

                //Intent to start activity
                Intent intent = new Intent(getBaseContext(), HoursActivity.class);
                etName = (EditText)findViewById(R.id.editTextUserName);


                if(etName.length() != 0){
                    String name = etName.getText().toString();
                    editor.putString("name",name);
                    editor.apply();
                    intent.putExtra("Name",name);
                    startActivity(intent);
//                    AddData("Justin","HI","false");
//                    AddData(name);
                }

            }
        });

//    }
//    public void AddData(String newItemName,String newItemDetails, String newIsChecked){
//        boolean insertData = mDatabaseHelper.addData(newItemName,newItemDetails,newIsChecked);
//
//        if(insertData){
//            toastMessage("Data Successfully Inserted!");
//        } else {
//            toastMessage("Something went wrong");
//        }
//    }
//
//    private void toastMessage(String message){
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        Intent intent = new Intent(getBaseContext(), HoursActivity.class);

        if(pref.contains("name")) {
            String name = pref.getString("name", "");
            intent.putExtra("Name", name);
            startActivity(intent);
        }


    }

}
