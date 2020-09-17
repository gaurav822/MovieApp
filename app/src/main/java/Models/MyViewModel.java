package Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    MyRepository repository;

    LiveData<List<FavouriteMovies>> getallData;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository=new MyRepository(application);
        getallData=repository.readALLData();

    }

    public void insert(FavouriteMovies favouriteMovies){
        repository.insert(favouriteMovies);
    }

    public void update(FavouriteMovies favouriteMovies){

        repository.update(favouriteMovies);
    }

    public void delete(FavouriteMovies favouriteMovies){

        repository.delete(favouriteMovies);
    }

    public LiveData<List<FavouriteMovies>> readData(){

        return getallData;
    }
}

