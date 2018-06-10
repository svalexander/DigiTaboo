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
public interface TabooDeckDao {

    @Insert
    void insert(TabooDeck tabooDeck);

    @Delete
    void delete(TabooDeck tabooDeck);

    @Query("SELECT * FROM taboo_deck_table")
    LiveData<List<TabooDeck>> getAllDecks();

    //is this correct? do i need this here?
    //deck_id is the name from the entity class
    @Query("SELECT * FROM tabooCard_table WHERE deck_id=:deckID")
    LiveData<List<TabooCard>> getAllWordsInDeck(final String deckID);
}
