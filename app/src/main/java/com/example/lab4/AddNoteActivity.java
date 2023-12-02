package com.example.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etNoteName, etNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNoteName = findViewById(R.id.etNoteName);
        etNoteContent = findViewById(R.id.etNoteContent);

        Button saveNoteButton = findViewById(R.id.save_note_btn);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = etNoteName.getText().toString();
                String noteContent = etNoteContent.getText().toString();

                if (noteName.isEmpty()) {

                    Toast.makeText(AddNoteActivity.this, "Note title cannot be empty", Toast.LENGTH_SHORT).show();
                } else {

                    com.example.lab4.MainActivity.notesList.add(noteName + "\n" + noteContent);
                    com.example.lab4.MainActivity.notesAdapter.notifyDataSetChanged();

                    finish();
                }
            }
        });
    }
}
