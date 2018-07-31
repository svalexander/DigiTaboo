package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;
import nyc.c4q.shannonalexander_navarro.digitaboo.rv.PlayAdapter;

public class PlayGameActivity extends AppCompatActivity {

    private PlayAdapter playAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TabooViewModel viewModel;

    private TextView countDownTV, startTV;
    private Button correctButton, skipButton, tabooButton;
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private int default_num_rounds = 10;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initViews();
        startRound();
        handleButtons();
    }

    //TODO: randomize cards

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
        startTV = findViewById(R.id.start_tv);
        countDownTV = findViewById(R.id.countdown_tv);
        correctButton = findViewById(R.id.correct_btn);
        skipButton = findViewById(R.id.skip_btn);
        tabooButton = findViewById(R.id.buzz_btn);
    }

    private void startRound(){
        hideButtons();
        startTV.setOnClickListener(v -> {
            startCountDown();
            initRv();
            observeDB();
            startTV.setVisibility(View.INVISIBLE);
        });
    }

    private void hideButtons() {
        correctButton.setVisibility(View.INVISIBLE);
        skipButton.setVisibility(View.INVISIBLE);
        tabooButton.setVisibility(View.INVISIBLE);
    }

    private void handleButtons() {
        correctButton.setOnClickListener(v -> {
            score += 1;
            handleSeenCard();
        });
        tabooButton.setOnClickListener(v -> {
            score -= 1;
            handleSeenCard();
        });
        skipButton.setOnClickListener(v -> handleSeenCard());
    }

    private void startCountDown() {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long time = millisUntilFinished / 1000;
                countDownTV.setText(String.valueOf(time));
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    private void handleSeenCard() {
        int position = linearLayoutManager.findFirstVisibleItemPosition();
        TabooCard seenCard = playAdapter.getCardAtCurrentPosition(position);
        seenCards.add(seenCard);
        playAdapter.deleteCard(position);
    }

}
