package com.jhonisaac.recyclerviewsample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class NewNoteDialogFragment extends DialogFragment {

    private NewNoteDialogViewModel mViewModel;
    private View mView;
    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }
    private EditText et_title;
    private EditText et_description;
    private Switch isFavorite;
    private RadioGroup colorSelect;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_note_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewNoteDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Create a new dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(mView)
                .setTitle((R.string.new_note))
                .setMessage((R.string.data_new_note))
                .setPositiveButton((R.string.save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //save note
                        String title = et_title.getText().toString();
                        String description = et_description.getText().toString();
                        String color = "Blue";
                        switch (colorSelect.getCheckedRadioButtonId()){
                            case (R.id.rb_red):
                                color = "Red";
                                break;
                            case (R.id.rb_green):
                                color = "Green";
                                break;
                        }
                        boolean favoriteNote = isFavorite.isChecked();
                    }
                })
                .setNegativeButton((R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close dialog
                        dismiss();
                    }
                });

        //Inflate layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.new_note_dialog_fragment, null);
        //getting the components of mView
        et_title = mView.findViewById(R.id.et_title_dialog);
        et_description = mView.findViewById(R.id.et_description_dialog);
        colorSelect = mView.findViewById(R.id.switch_favorite);
        isFavorite = mView.findViewById(R.id.radio_group_color);

        return builder.create();
    }
}
