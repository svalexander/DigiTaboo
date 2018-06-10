package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by shannonalexander-navarro on 6/6/18.
 */

@Dao
public interface TabooCardDao {

    @Insert
    void insert(TabooCard tabooCard);

    @Delete
    void delete(TabooCard tabooCard);

    @Query("DELETE FROM tabooCard_table")
    void deleteAll();

    @Query("SELECT * FROM tabooCard_table")
    LiveData<List<TabooCard>> getAllCards();

}
