package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by shannonalexander-navarro on 6/9/18.
 */

public class TabooViewModel extends AndroidViewModel {

    private TabooRepository repository;
    private LiveData<List<TabooCard>> allCards;

    public TabooViewModel(Application application) {
        super(application);

        repository = new TabooRepository(application);
        allCards = repository.getCards();
    }

    public LiveData<List<TabooCard>> getAllCards() {
        return allCards;
    }

    public void insert(TabooCard card) {
        repository.insert(card);
    }

}
