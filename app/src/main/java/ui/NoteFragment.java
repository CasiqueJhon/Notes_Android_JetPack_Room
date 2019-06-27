package ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jhonisaac.recyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

import db.entity.NoteEntity;
import viewmodel.NewNoteDialogViewModel;


public class NoteFragment extends Fragment {

    private List<NoteEntity> mNoteEntityList;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    MyNoteRecyclerViewAdapter adapterNotes;

    public NoteFragment() {
    }

    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //Indicate the fragment has a own menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                //calculate by himself the width on the screen tha display the app (tablet)
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpwidth = displayMetrics.widthPixels / displayMetrics.density;
                int columnNumber = (int) (dpwidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnNumber, StaggeredGridLayoutManager.VERTICAL));
            }

            mNoteEntityList = new ArrayList<>();

            adapterNotes = new MyNoteRecyclerViewAdapter(mNoteEntityList, context);
            recyclerView.setAdapter(adapterNotes);

            updateViewModel();
        }
        return view;
    }

    //this method update the data base when a new note is create it
    private void updateViewModel() {
        //with this variable from the ViewModel.class, you get the same instantiate
        NewNoteDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
        //invoque the method to know if a new note was create it through a observer
        mViewModel.getAllNotes().observe(getActivity(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                //When onChanged detect a change, is going to get the new list of object
                //update the adapter
                adapterNotes.setNewNotes(noteEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_note:
                showDialogNewNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogNewNote() {
        //instantiate fragment manager for dialog
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NewNoteDialogFragment noteDialogFragment = new NewNoteDialogFragment();
        noteDialogFragment.show(fm, "NewDialogFragment");
    }
}
