package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        String selectedNoteName = getIntent().getStringExtra("selectedNoteName");
        String selectedNoteContent = getIntent().getStringExtra("selectedNoteContent");

        TextView tvNoteName = findViewById(R.id.tvNoteName);
        tvNoteName.setText(selectedNoteName);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("NoteDetailActivity", "Edit button clicked");

                Intent intent = new Intent(NoteDetailActivity.this, EditNoteActivity.class);
                intent.putExtra("selectedNoteName", selectedNoteName);
                startActivity(intent);
            }
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote();
            }
        });
    }

    private void deleteNote() {
        com.example.lab4.MainActivity.notesList.remove(getIntent().getStringExtra("selectedNoteName"));
        com.example.lab4.MainActivity.notesAdapter.notifyDataSetChanged();
        finish();
    }
}
