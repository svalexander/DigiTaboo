package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TabooRepository {

    private TabooDeckDao tabooDeckDao;
    private TabooCardDao tabooCardDao;
    private LiveData<List<TabooCard>> cards;

    public TabooRepository(Application application) {
        TabooDatabase db = TabooDatabase.getDatabase(application);

        tabooCardDao = db.wordDao();
        tabooDeckDao = db.deckDao();
        cards = tabooDeckDao.getAllWordsInDeck("default");
    }

    LiveData<List<TabooCard>> getCards() {
        return cards;
    }


    public void insert(TabooCard card) { new insertAsyncTask(tabooCardDao).execute(card);}

    private static class insertAsyncTask extends AsyncTask<TabooCard, Void, Void> {

        private TabooCardDao mAsyncTaskDao;

        insertAsyncTask(TabooCardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TabooCard... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
