package com.example.pcworld.mynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //refer to Edittext
      /*  EditText titleInput=(EditText) findViewById(R.id.titleInput);
    EditText descInput=(EditText) findViewById(R.id.descInput);
        //Get inputs from user
        String titleStr=titleInput.getText().toString();
        String descStr=descInput.getText().toString();
Note noteObj =new Note();
        noteObj.setTitle(titleStr);
        noteObj.setDes(descStr);
        NotesActivityFragment.notes.add(noteObj);
*/


    }
}
