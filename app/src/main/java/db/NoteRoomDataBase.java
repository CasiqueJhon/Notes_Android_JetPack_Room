package db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import db.dao.NoteDao;

public abstract class NoteRoomDataBase extends RoomDatabase {

    //Declaro el dao de donde se obtienen los datos
    public abstract NoteDao mNoteDao();
    //control de la instancia de la base de datos
    private static volatile NoteRoomDataBase INSTANCE;

    ///Hago un metodo para obtener esa instancia de la base datos.
    public static NoteRoomDataBase getDataBaseInstance(final Context context) {
        //verifico que no se haya instanciado antes
        if (INSTANCE == null) {
            //en este caso se sincroniza con la base de datos
            synchronized (NoteRoomDataBase.class) {
                //despues vuelvo a verificar si es o no nula
                if (INSTANCE == null) {
                    //Si lo es paso a crear la primera instancia de la base de datos
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDataBase.class,
                            "first data base")
                            .build();
                }
            }
        }

        return INSTANCE;
    }


}
