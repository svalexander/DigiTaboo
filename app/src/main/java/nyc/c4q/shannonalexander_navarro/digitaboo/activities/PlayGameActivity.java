package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

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
    private Button correctButton, skipButton, tabooButton;
    private RecyclerView recyclerView;
    private int score = 0;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initRv();
        observeDB();
        initViews();
        handleButtons();
    }

    //TODO: get random cards from db
    //TODO: swipe left "did not answer or pass", swipe right "answered correctly"

    private void observeDB() {
        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> playAdapter.setCards(words));

    }

    private void initRv() {
        recyclerView = findViewById(R.id.play_rv);
        playAdapter = new PlayAdapter(this);
        recyclerView.setAdapter(playAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        correctButton = findViewById(R.id.correct_btn);
        skipButton = findViewById(R.id.skip_btn);
        tabooButton = findViewById(R.id.buzz_btn);
    }

    private void handleButtons() {
        correctButton.setOnClickListener(v -> {
            score+=1;
            handleSeenCard();
            //TODO: set overlay
        });
    }

    private void handleSeenCard() {
        int position = linearLayoutManager.findFirstVisibleItemPosition();
        TabooCard seenCard = playAdapter.getCardAtCurrentPosition(position);
        seenCards.add(seenCard);
        playAdapter.deleteCard(position);
    }

}
