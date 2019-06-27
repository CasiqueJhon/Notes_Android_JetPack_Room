package db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import db.entity.NoteEntity;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(NoteEntity note);

    @Update
    void update(NoteEntity note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("SELECT * FROM notes WHERE id = :idNote")
    void deleteById(NoteEntity idNote);

    //LiveData convert the data into a dynamics data.
    //Apply ViewModel to update the Notes
    @Query("SELECT * FROM notes Order by title ASC")
    LiveData<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM notes WHERE favorite LIKE 1")
    LiveData<List<NoteEntity>> getFavoriteNote();

}
