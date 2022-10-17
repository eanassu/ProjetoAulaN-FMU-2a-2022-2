package br.fmu.projetoaulan;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Funcionario.class}, version=1)
@TypeConverters(DateConverter.class)
abstract public class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    public static final String DATABASE_NAME="teste.db";
    public abstract FuncionarioDao funcionarioDao();
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    public static AppDatabase getInstance(final Context context) {
        if ( sInstance == null ) {
            synchronized (AppDatabase.class) {
                if ( sInstance==null ) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updataDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,AppDatabase.class,
                DATABASE_NAME).allowMainThreadQueries().build();
    }

    private void updataDatabaseCreated(final Context context) {
        if( context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }
}
