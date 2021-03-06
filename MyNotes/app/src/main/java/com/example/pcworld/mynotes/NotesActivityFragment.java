package com.example.pcworld.mynotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.pcworld.mynotes.R.id.notesList;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotesActivityFragment extends Fragment {
    static ArrayList<Note> notesList = new ArrayList<>();
    // Control visibility of all checkboxes at once
    static int isCheckBoxVisible = View.GONE;
    // Store all checked notes
    static ArrayList<Note> checkedNotes = new ArrayList<>();
    private NotesAdapter mNotesAdapter;
    public NotesActivityFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment
        setRetainInstance(true);

        // Allow options menu at this fragment
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_notes, container, false);
        FloatingActionButton fab=(FloatingActionButton) root.findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent =new Intent(getActivity(),DetailActivity.class);
                startActivity(detailIntent);
            }
        });*/
        ListView notesListView=(ListView)root.findViewById(R.id.notesList);
        mNotesAdapter = new NotesAdapter(getActivity(), 0, notesList);

        // Attach the Adapter to the ListView
        notesListView.setAdapter(mNotesAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Send intent with the note details
                Intent detailsIntent = new Intent(getActivity(), DetailActivity.class);
                detailsIntent.putExtra("timeKey", notesList.get(i).getTime());
                detailsIntent.putExtra("titleKey", notesList.get(i).getTitle());
                detailsIntent.putExtra("descKey", notesList.get(i).getDescription());
                detailsIntent.putExtra("positionKey", i);
                startActivity(detailsIntent);
            }
        });

        return root;
    }
    @Override
    public void onResume() {
        super.onResume();

        updateAdapter();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_notes, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_delete:
                if (notesList.size() > 0) {
                    if (isCheckBoxVisible == View.GONE)
                        //Allow user to check items
                        isCheckBoxVisible = View.VISIBLE;
                    else {
                        // Disallow user to check items
                        if (checkedNotes.size() == 0)
                            isCheckBoxVisible = View.GONE;
                    }
                }

                updateAdapter();

                if (checkedNotes.size() > 0)
                    confirmDelete();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateAdapter() {
        mNotesAdapter.notifyDataSetChanged();
    }

    // Show confirm message before deleting items
    public void confirmDelete() {
        new AlertDialog.Builder(getActivity())
                .setMessage(getActivity().getResources().getString(R.string.confirm_message))
                .setPositiveButton(getActivity().getResources().getString(R.string.action_delete)
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                for (int i = 0; i < checkedNotes.size(); i++) {
                                    notesList.remove(checkedNotes.get(i));
                                }

                                // Hide checkboxes
                                isCheckBoxVisible = View.GONE;

                                // Reset checked items to not be used again
                                checkedNotes.clear();

                                updateAdapter();
                            }

                        })
                .setNegativeButton(
                        getActivity().getResources().getString(R.string.action_cancel), null)
                .show();
    }

}
