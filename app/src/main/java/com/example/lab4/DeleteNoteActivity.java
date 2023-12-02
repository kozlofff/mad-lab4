package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private ListView lvExistingNotes;
    private ArrayList<String> existingNotesList;
    private ArrayAdapter<String> existingNotesAdapter;
    private ArrayList<String> selectedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        lvExistingNotes = findViewById(R.id.lvExistingNotes);
        existingNotesList = new ArrayList<>();
        existingNotesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, existingNotesList);
        lvExistingNotes.setAdapter(existingNotesAdapter);
        lvExistingNotes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        existingNotesList.addAll(com.example.lab4.MainActivity.notesList);
        existingNotesAdapter.notifyDataSetChanged();

        selectedNotes = new ArrayList<>();
        Log.d("checkboxes", "stuff is working");

        lvExistingNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = existingNotesList.get(position);
                if (lvExistingNotes.isItemChecked(position)) {
                    selectedNotes.add(selectedNote);
                } else {
                    selectedNotes.remove(selectedNote);
                }
                Log.d("checkboxes", "stuff is working");
            }

        });


        Button deleteSelectedNoteButton = findViewById(R.id.delete_selected_note_btn);
        deleteSelectedNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (String selectedNote : selectedNotes) {
                    com.example.lab4.MainActivity.notesList.remove(selectedNote);
                }

                com.example.lab4.MainActivity.notesAdapter.notifyDataSetChanged();

                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("deletedNotes", selectedNotes);
                setResult(RESULT_OK, resultIntent);


                selectedNotes.clear();

                finish();
            }
        });
    }
}
