package com.example.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText etEditNoteName, etEditNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        String selectedNoteName = getIntent().getStringExtra("selectedNoteName");
        etEditNoteName = findViewById(R.id.etEditNoteName);
        etEditNoteName.setText(selectedNoteName);

        Button saveEditNoteButton = findViewById(R.id.save_edit_note_btn);
        saveEditNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedNoteName = etEditNoteName.getText().toString();
                if (updatedNoteName.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Note title cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    com.example.lab4.MainActivity.notesList.remove(selectedNoteName);
                    com.example.lab4.MainActivity.notesList.add(updatedNoteName );
                    com.example.lab4.MainActivity.notesAdapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
    }
}
