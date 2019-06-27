package db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import db.dao.NoteDao;
import db.entity.NoteEntity;

public class NoteRepository {
    //1 must be connect with the DAO
    private NoteDao mNoteDao;

    //only for this sample
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allFavoritesNotes;

    //2 constructor must have the context
    public NoteRepository(Application application) {
        //3 Connect database/API here
        NoteRoomDataBase db = NoteRoomDataBase.getDataBaseInstance(application);
        //4 get the instanceof DAO
        mNoteDao = db.mNoteDao();
        allNotes = mNoteDao.getAllNotes();
        allFavoritesNotes = mNoteDao.getFavoriteNote();
    }

    //5 declare the operations to do into the data base or API
    //the repository can denied this operations from DAO
    //If the app is going to use API, is need it to declare the operations to get here.

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public LiveData<List<NoteEntity>> getAllFavoritesNotes() {
        return allFavoritesNotes;
    }

    public void insert(NoteEntity note) {
        //this action can't be done in the main thread, is necessary create one asyncTask class.
        //once is create the asyncTask, make the instance from here and execute.
        new insertAsyncTask(mNoteDao).execute(note);
    }

    //6 create the asyncTask class
    public static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        //7 need to access DAO from here
        private NoteDao noteDaoAsyncTask;

        //8 constructor need have the DAO
        insertAsyncTask(NoteDao dao) {
            noteDaoAsyncTask = dao;
        }

        //9 implement method doInBackground to make the action from a second thread
        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            //10 call the REST from number 0
            noteDaoAsyncTask.insertNote(noteEntities[0]);
            return null;
        }
    }

    //SAMPLE ONLY
    public void update(NoteEntity note) {
        new updateAsyncTask(mNoteDao).execute(note);
    }

    public static class updateAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoUpdateAsyncTask;

        updateAsyncTask(NoteDao dao) {
            noteDaoUpdateAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoUpdateAsyncTask.update(noteEntities[0]);
            return null;
        }
    }

}
