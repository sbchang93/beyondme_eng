package com.example.fragmentexample.view.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fragmentexample.R;

// Reference URL : https://www.youtube.com/watch?v=pG6OkJ3rSjg&list=PLxTmPHxRH3VWTd-8KB67Itegihkl4SVKe&index=52
public class RoomDbActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdb_exam);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();

        mResultTextView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultTextView.setText(db.todoDao().getAll().toString());
                mTodoEditText.setText("");
            }
        });
    }
}