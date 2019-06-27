package ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jhonisaac.recyclerviewsample.R;

import java.util.List;

import db.entity.NoteEntity;


public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context mContext;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context context) {
        mValues = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitle.setText(holder.mItem.getTitle());
        holder.tvDescription.setText(holder.mItem.getDescription());
        if (holder.mItem.isFavorite()) {
            holder.ivFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        }


        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //create this method to update the new note
    public void setNewNotes(List<NoteEntity> newNotes) {
        this.mValues = newNotes;
        //update the adapter to refresh the list
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvDescription;
        public final ImageView ivFavorite;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tv_note_title);
            tvDescription = view.findViewById(R.id.tv_note_description);
            ivFavorite = view.findViewById(R.id.iv_favorite);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitle.getText() + "'";
        }
    }
}
