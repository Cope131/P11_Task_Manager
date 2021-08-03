package com.myapplicationdev.android.p11_task_manager;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int REQUEST_CODE = 201;

    // List View Components
    private ListView tasksListView;
    private ArrayAdapter<Task> arrayAdapter;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListComps();
        loadTasks();
    }

    private void initViews() {
        Button addTaskBtn = findViewById(R.id.add_task_button);
        addTaskBtn.setOnClickListener(this);
    }

    private void initListComps() {
        tasksListView = findViewById(R.id.tasks_list_view);
        tasks = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);
        tasksListView.setAdapter(arrayAdapter);
    }

    private void loadTasks() {
        DBHelper db = new DBHelper(this);
        tasks.addAll(db.getAllTasks());
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        // TODO: go to add activity
        Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intentAdd, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            loadTasks();
        }
    }
}