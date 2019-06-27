package db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//Implement Room from JetPack.
//Creating Entity
@Entity (tableName = "notes")
public class NoteEntity {
    //autoGenerate allows the system make a new id for every new note by him self inside data base.
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public boolean favorite;
    public String colorNote;

    public NoteEntity(String title, String description, boolean favorite, String colorNote) {
        this.title = title;
        this.description = description;
        this.favorite = favorite;
        this.colorNote = colorNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getColorNote() {
        return colorNote;
    }

    public void setColorNote(String colorNote) {
        this.colorNote = colorNote;
    }
}
