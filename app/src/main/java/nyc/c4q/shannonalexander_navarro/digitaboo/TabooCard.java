package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "tabooCard_table")
public class TabooCard implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "answer")
    private String wordToGuess;

    @ColumnInfo(name = "no_1")
    private String tabooWord1;

    @ColumnInfo(name = "no_2")
    private String tabooWord2;

    @ColumnInfo(name = "no_3")
    private String tabooWord3;

    @ColumnInfo(name = "no_4")
    private String tabooWord4;

    @ColumnInfo(name = "no_5")
    private String tabooWord5;

    //points to the deck it belongs to
    @ColumnInfo(name = "deck_id")
    private String deckID;

    public TabooCard(@NonNull String wordToGuess, String tabooWord1, String tabooWord2, String tabooWord3, String tabooWord4, String tabooWord5, String deckID) {
        this.wordToGuess = wordToGuess;
        this.tabooWord1 = tabooWord1;
        this.tabooWord2 = tabooWord2;
        this.tabooWord3 = tabooWord3;
        this.tabooWord4 = tabooWord4;
        this.tabooWord5 = tabooWord5;
        this.deckID = deckID;
    }

    @NonNull
    public String getWordToGuess() {
        return wordToGuess;
    }

    public void setWordToGuess(@NonNull String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public String getTabooWord1() {
        return tabooWord1;
    }

    public void setTabooWord1(String tabooWord1) {
        this.tabooWord1 = tabooWord1;
    }

    public String getTabooWord2() {
        return tabooWord2;
    }

    public void setTabooWord2(String tabooWord2) {
        this.tabooWord2 = tabooWord2;
    }

    public String getTabooWord3() {
        return tabooWord3;
    }

    public void setTabooWord3(String tabooWord3) {
        this.tabooWord3 = tabooWord3;
    }

    public String getTabooWord4() {
        return tabooWord4;
    }

    public void setTabooWord4(String tabooWord4) {
        this.tabooWord4 = tabooWord4;
    }

    public String getTabooWord5() {
        return tabooWord5;
    }

    public void setTabooWord5(String tabooWord5) {
        this.tabooWord5 = tabooWord5;
    }

    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }
}
