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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    public ListView lvNotes;
    public static ArrayList<String> notesList;
    public static ArrayAdapter<String> notesAdapter;
    private static final int DELETE_NOTE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = findViewById(R.id.lvNotes);
        notesList = new ArrayList<>();
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(notesAdapter);

        Button deleteNoteButton = findViewById(R.id.delete_note_btn);



        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNoteName = notesList.get(position);
                String selectedNoteContent = getNoteContentFromStorage(selectedNoteName);

                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("selectedNoteName", selectedNoteName);
                intent.putExtra("selectedNoteContent", selectedNoteContent);
                startActivity(intent);
            }



            private String getNoteContentFromStorage(String selectedNoteName) {
                int noteIndex = notesList.indexOf(selectedNoteName);
                if (noteIndex != -1) {
                    return notesList.get(noteIndex);
                } else {
                    return "";
                }
            }
        });
        Button addNoteButton = findViewById(R.id.add_note_btn);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Delete button clicked");
                Intent intent = new Intent(MainActivity.this, DeleteNoteActivity.class);
                startActivityForResult(intent, DELETE_NOTE_REQUEST);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity", "onActivityResult called with requestCode: " + requestCode);
        if (requestCode == DELETE_NOTE_REQUEST && resultCode == RESULT_OK) {

            ArrayList<String> deletedNotes = data.getStringArrayListExtra("deletedNotes");

            if (deletedNotes != null) {
                notesList.removeAll(deletedNotes);
                notesAdapter.notifyDataSetChanged();
            }
        }
    }

}
