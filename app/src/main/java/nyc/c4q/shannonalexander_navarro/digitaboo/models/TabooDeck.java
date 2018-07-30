package nyc.c4q.shannonalexander_navarro.digitaboo.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "taboo_deck_table",
        foreignKeys = @ForeignKey(
        entity = TabooCard.class,
        parentColumns = "answer",
        childColumns = "deck"
))
public class TabooDeck {

    //each word/card will include a reference to the deck name to establish the relationship
   @PrimaryKey
   @NonNull
    private String deck;

    public TabooDeck(String deck) {
        this.deck = deck;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }
}
