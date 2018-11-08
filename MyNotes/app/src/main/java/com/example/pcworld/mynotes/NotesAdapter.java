package com.example.pcworld.mynotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.pcworld.mynotes.Note;
import com.example.pcworld.mynotes.R;

import java.util.ArrayList;

/**
 * Created by pc world on 08/02/2017.
 */

public class NotesAdapter extends ArrayAdapter<Note> {

    public NotesAdapter(Context context, int resource, ArrayList<Note> notesList) {
        super(context, resource);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Note noteObj = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element, parent, false);
        }

        // Lookup view for data population
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
        TextView descTextView = (TextView) convertView.findViewById(R.id.desc_text_view);
        final CheckBox noteCheckBox = (CheckBox) convertView.findViewById(R.id.note_check_box);


        if (noteObj != null) {
            titleTextView.setText(noteObj.getTitle());
            descTextView.setText(noteObj.getDescription());


            noteCheckBox.setVisibility(NotesActivityFragment.isCheckBoxVisible);


            if (!NotesActivityFragment.checkedNotes.contains(noteObj))
                noteCheckBox.setChecked(false);
            else
                noteCheckBox.setChecked(true);


            noteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (noteCheckBox.isChecked())
                        NotesActivityFragment.checkedNotes.add(noteObj);
                    if (!noteCheckBox.isChecked())
                        NotesActivityFragment.checkedNotes.remove(noteObj);
                }
            });
        }

        // Return the completed view to render on screen
        return convertView;

    }


}