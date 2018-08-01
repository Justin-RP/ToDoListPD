package com.example.a16022916.todolistpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 16022916 on 15/11/2017.
 */

public class HoursActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnListPage;
    EditText etHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        tvWelcome = (TextView)findViewById(R.id.textViewWelcome);
        btnListPage = (Button)findViewById(R.id.buttonToList);

        //Get the Intent that started this activity and extract String
        Intent intent = getIntent();
        String welcome = intent.getStringExtra("Name");

        tvWelcome.setText("Welcome " + welcome);

        btnListPage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                etHours = (EditText)findViewById(R.id.editTextHours);
                String hours = etHours.getText().toString();
                intent.putExtra("Hours",hours);
                startActivity(intent);
            }
        });

    }
}