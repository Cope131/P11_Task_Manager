package com.myapplicationdev.android.p11_task_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText etName, etDescription;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAddTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etDescription.getText().toString().equals("") && !etName.getText().toString().equals("")) {
                    String nameInput = etName.getText().toString();
                    String descriptionInput = etDescription.getText().toString();

                    try {
                        DBHelper dbh = new DBHelper(AddActivity.this);

                        Task task = new Task(nameInput, descriptionInput);
                        dbh.insertTask(task);
                        dbh.close();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                Intent intentBack = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });
    }
}