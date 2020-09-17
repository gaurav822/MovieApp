package Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavouriteMoviesDao {

    @Insert
    public void insert(FavouriteMovies favouriteMovies);

    @Query("SELECT * FROM FavouriteData")
    public LiveData<List<FavouriteMovies>> readData();

    @Delete
    public void delete(FavouriteMovies favouriteMovies);

    @Update
    public void update(FavouriteMovies favouriteMovies);
}
