package Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {

    FavouriteMoviesDatabase favouriteMoviesDatabase;
    LiveData<List<FavouriteMovies>> readData;

    public MyRepository(Application application) {

        favouriteMoviesDatabase=FavouriteMoviesDatabase.getDatabase(application);
        readData=favouriteMoviesDatabase.myDao().readData();
    }

    public void insert(FavouriteMovies favouriteMovies){

        new InsertTask().execute(favouriteMovies);
    }

    public LiveData<List<FavouriteMovies>> readALLData(){
        return readData;
    }

    public void update(FavouriteMovies favouriteMovies){

        new UpdateTask().execute(favouriteMovies);
    }

    public void delete(FavouriteMovies favouriteMovies){

        new DeleteTask().execute(favouriteMovies);
    }

    class InsertTask extends AsyncTask<FavouriteMovies,Void,Void> {

        @Override
        protected Void doInBackground(FavouriteMovies... favouriteMovie) {
            favouriteMoviesDatabase.myDao().insert(favouriteMovie[0]);
            return null;
        }
    }

    class UpdateTask extends AsyncTask<FavouriteMovies,Void,Void>{

        @Override
        protected Void doInBackground(FavouriteMovies... favouriteMovie) {
            favouriteMoviesDatabase.myDao().update(favouriteMovie[0]);
            return null;
        }
    }

    class DeleteTask extends AsyncTask<FavouriteMovies,Void,Void>{

        @Override
        protected Void doInBackground(FavouriteMovies... favouriteMovie) {
            favouriteMoviesDatabase.myDao().delete(favouriteMovie[0]);
            return null;
        }
    }
}