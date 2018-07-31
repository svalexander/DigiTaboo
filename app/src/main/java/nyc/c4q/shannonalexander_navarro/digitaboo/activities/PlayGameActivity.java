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

    private TextView countDownTV, startTV, roundTV, teamTV;
    private Button correctButton, skipButton, tabooButton;
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private  static  final int DEFAULT_NUM_ROUNDS = 10;
    private static final int TURNS = 2;
    private static final String TEAM_1 = "Team 1";
    private static final String TEAM_2 = "Team 2";
    private int score = 0;
    private String currentTeam;

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
        roundTV = findViewById(R.id.current_round_tv);
        teamTV = findViewById(R.id.current_team_tv);
        startTV = findViewById(R.id.start_tv);
        countDownTV = findViewById(R.id.countdown_tv);
        correctButton = findViewById(R.id.correct_btn);
        skipButton = findViewById(R.id.skip_btn);
        tabooButton = findViewById(R.id.buzz_btn);
    }

    private void startRound(){
        hideButtons();
        startTV.setOnClickListener(v -> {
            gamePlay();
            initRv();
            observeDB();
            startTV.setVisibility(View.INVISIBLE);
            showButtons();
        });
    }

    private void gamePlay(){
        int currentRound = 1;
        roundTV.setText("Round: " + currentRound);
        while (currentRound <= DEFAULT_NUM_ROUNDS) {
            for (int i = 0; i < TURNS ; i++) {
                if(i == 0){
                    currentTeam = TEAM_1;
                    teamTV.setText(currentTeam);
                    startCountDown();
                } else{
                    currentTeam = TEAM_2;
                    teamTV.setText(currentTeam);
                    startCountDown();
                }
            }
            currentRound+=1;
        }
    }

    private void hideButtons() {
        correctButton.setVisibility(View.INVISIBLE);
        skipButton.setVisibility(View.INVISIBLE);
        tabooButton.setVisibility(View.INVISIBLE);
    }

    private void showButtons() {
        correctButton.setVisibility(View.VISIBLE);
        skipButton.setVisibility(View.VISIBLE);
        tabooButton.setVisibility(View.VISIBLE);
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
        new CountDownTimer(10000, 1000) {

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
