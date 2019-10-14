package com.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.taskapp.ui.home.HomeFragment;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesc;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        task = (Task) getIntent().getSerializableExtra("task");
        if(task != null) {
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
        }
    }

    public void onClick(View view) {

        String title = editTitle.getText().toString();
        String desc = editDesc.getText().toString();
        if (TextUtils.isEmpty(title)) {
            editTitle.setError("Заполните это поле");
            return;
        }

        if (TextUtils.isEmpty(desc)) {
            editDesc.setError("Заполните это поле");
            return;
        }

        if(task != null) {
            task.setTitle(title);
            task.setDesc(desc);
            App.getInstance().getDatabase().taskDao().update(task);
        } else {
            task = new Task (title, desc);
            App.getInstance().getDatabase().taskDao().insert(task);
        }
        Task task = new Task(title, desc);
        App.getInstance().getDatabase().taskDao().insert(task);


        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();

    }
}
