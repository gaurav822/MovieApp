package Models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteMovies.class},version = 1,exportSchema = false)
public abstract class FavouriteMoviesDatabase extends RoomDatabase {

    public abstract FavouriteMoviesDao myDao();

    public static FavouriteMoviesDatabase database;

    public static synchronized FavouriteMoviesDatabase getDatabase(Context context){

        if(database==null){

            database = Room.databaseBuilder(context,FavouriteMoviesDatabase.class,"MYDB")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }

        return database;
    }

}