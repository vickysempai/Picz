package Posts.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {PostRoom.class}, version = 3)/*
@TypeConverters({DateTypeConverter.class})
@Database (entities = {Movies.class}, version = 1, exportSchema = false)*/
public abstract class PostDataBase extends RoomDatabase {

    private static PostDataBase INSTANCE;


    public abstract PostDAO PostDAO();

    public static PostDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PostDataBase.class, "database-name")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}