package Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavouriteData")
public class FavouriteMovies {

    @PrimaryKey @NonNull
    String Posterpath;

    String Title;

    String Overview;

    String Releasedate;

    public String getPosterpath() {
        return Posterpath;
    }

    public void setPosterpath(String posterpath) {
        Posterpath = posterpath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getReleasedate() {
        return Releasedate;
    }

    public void setReleasedate(String releasedate) {
        Releasedate = releasedate;
    }
}
