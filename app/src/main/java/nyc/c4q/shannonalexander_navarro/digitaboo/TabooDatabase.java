package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by shannonalexander-navarro on 6/6/18.
 */

@Database(entities = {TabooDeck.class, TabooCard.class}, version = 1)
public abstract class TabooDatabase extends RoomDatabase {

    public abstract TabooDeckDao deckDao();

    public abstract TabooCardDao wordDao();

    private static TabooDatabase INSTANCE;

    public static TabooDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            //if an object is visible to more than 1 thread all reading/writing to that object should be done thru
            //a synchronized method (java docs)
            synchronized (TabooDatabase.class) {
                if (INSTANCE == null) { //why do we need  2nd check?
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TabooDatabase.class, "taboo_database")
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();

        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TabooCardDao cardDao;
        private final TabooDeckDao deckDao;

        PopulateDbAsync(TabooDatabase db) {
            cardDao = db.wordDao();
            deckDao = db.deckDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
           cardDao.deleteAll(); //without deleteall manage crashes
            TabooCard defaultCard = new TabooCard("Chicken Salad", "lunch", "dressing",
                    "eat", "lettuce", "tomato", "default");
            cardDao.insert(defaultCard);

            TabooCard defaultCard2 = new TabooCard("Birthday Party", "balloons", "candles",
                    "cake", "gifts", "song", "default");
            cardDao.insert(defaultCard2);
            return null;
        }
    }
}
