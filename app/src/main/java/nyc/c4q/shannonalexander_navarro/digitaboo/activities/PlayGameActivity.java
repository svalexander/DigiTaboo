package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;
import nyc.c4q.shannonalexander_navarro.digitaboo.rv.PlayAdapter;

public class PlayGameActivity extends AppCompatActivity {

    private int default_num_rounds = 10;
    private TabooViewModel viewModel;
    private ArrayList<TabooCard> unseenCards = new ArrayList<>();
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private PlayAdapter playAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initRv();
        observeDB();
    }

    //TODO: get random cards from db
    //TODO: swipe left "did not answer or pass", swipe right "answered correctly"

    private void observeDB() {
        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> playAdapter.setCards(words));

    }

    private void initRv() {
        RecyclerView recyclerView = findViewById(R.id.play_rv);
        playAdapter = new PlayAdapter(this);
        recyclerView.setAdapter(playAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}
