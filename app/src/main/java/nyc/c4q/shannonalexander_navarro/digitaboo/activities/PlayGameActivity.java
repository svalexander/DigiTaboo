package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.Game;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;
import nyc.c4q.shannonalexander_navarro.digitaboo.rv.PlayAdapter;

public class PlayGameActivity extends AppCompatActivity {

    private PlayAdapter playAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TabooViewModel viewModel;

    private TextView countDownTV, startTV, roundTV, teamTV, promptTV, teamOneScoreTV, teamTwoScoreTV;
    private Button correctButton, skipButton, tabooButton;
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private static final int DEFAULT_NUM_ROUNDS = 1;
    private static final int TURNS = 2;
    private static final String TEAM_1 = "Team 1";
    private static final String TEAM_2 = "Team 2";
    private int teamOneScore = 0;
    private int teamTwoScore = 0;
    private String currentTeam;
    int currentRound = 1;
    int currentTurn = 1;
    boolean isPlaying;
    Game game;
    public static final String GAME_BUNDLE_KEY = "Game bundle";
    public static final String GAME_INTENT_KEY = "Game extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initViews();
        startRound();
        handleButtons();
        hideViews();
    }

    private void observeDB() {
        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> {
            Collections.shuffle(words);
            playAdapter.setCards(words);
        });

    }

    private void initRv() {
        recyclerView = findViewById(R.id.play_rv);
        playAdapter = new PlayAdapter(this);
        recyclerView.setAdapter(playAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        teamOneScoreTV = findViewById(R.id.team_one_score);
        teamTwoScoreTV = findViewById(R.id.team_two_score);
        promptTV = findViewById(R.id.prompt_tv);
        roundTV = findViewById(R.id.current_round_tv);
        teamTV = findViewById(R.id.current_team_tv);
        startTV = findViewById(R.id.start_tv);
        countDownTV = findViewById(R.id.countdown_tv);
        correctButton = findViewById(R.id.correct_btn);
        skipButton = findViewById(R.id.skip_btn);
        tabooButton = findViewById(R.id.buzz_btn);
    }

    private void startRound() {
        hideButtons();
        startTV.setOnClickListener(v -> {
            teamOneScoreTV.setText("Team One Score: 0");
            teamTwoScoreTV.setText("Team Two Score: 0");
            initRv();
            observeDB();
            teamTV.setText(currentTeam);
            startTV.setVisibility(View.INVISIBLE);
            showButtons();
            promptTV.setVisibility(View.INVISIBLE);
            startCountDown(currentTurn);
            gamePlay();

        });
    }

    private void gamePlay() {
        //  game.setCurrentRound(currentRound);
        roundTV.setText("Round: " + currentRound);
        promptTV.setOnClickListener(v -> {
            if (currentRound <= DEFAULT_NUM_ROUNDS) {
                startCountDown(currentTurn);
            }
        });
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

    private void hideViews() {
        roundTV.setVisibility(View.INVISIBLE);
        teamTV.setVisibility(View.INVISIBLE);
    }

    private void showViews() {
        roundTV.setVisibility(View.VISIBLE);
        teamTV.setVisibility(View.VISIBLE);
    }


    private void handleButtons() {
        correctButton.setOnClickListener(v -> {
            if (currentTurn == 1 && isPlaying) {
                teamOneScore += 1;
                teamOneScoreTV.setText("Team One Score: " + teamOneScore);
            }
            if (currentTurn == 2 && isPlaying) {
                teamTwoScore += 1;
                teamTwoScoreTV.setText("Team Two Score: " + teamTwoScore);
            }
            handleSeenCard();
        });
        tabooButton.setOnClickListener(v -> {
            if (currentTurn == 1 && isPlaying) {
                teamOneScore -= 1;
                teamOneScoreTV.setText("Team One Score: " + teamOneScore);
            }
            if (currentTurn == 2 && isPlaying) {
                teamTwoScore -= 1;
                teamTwoScoreTV.setText("Team Two Score: " + teamTwoScore);
            }
            handleSeenCard();
        });
        skipButton.setOnClickListener(v -> handleSeenCard());
    }

    private void startCountDown(int turn) {
        showViews();
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                promptTV.setVisibility(View.INVISIBLE);
                if (currentTurn == 1 && currentRound == 1) {
                    teamTV.setText(TEAM_1);
                } else {
                    teamTV.setText(currentTeam);
                }
                long time = millisUntilFinished / 1000;
                countDownTV.setText(String.valueOf(time));
                isPlaying = true;
            }

            @Override
            public void onFinish() {
                promptTV.setVisibility(View.VISIBLE);
                isPlaying = false;
                if (currentRound == DEFAULT_NUM_ROUNDS && turn == 2) {
                    promptTV.setVisibility(View.INVISIBLE);
                    game = new Game();
                    game.setTeamOneScore(teamOneScore);
                    game.setTeamTwoScore(teamTwoScore);
                    Intent intent = new Intent(PlayGameActivity.this, LeaderBoardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(GAME_BUNDLE_KEY, game);
                    intent.putExtra(GAME_INTENT_KEY, bundle);
                    startActivity(intent);
                }
                if (turn == 2 && currentRound < DEFAULT_NUM_ROUNDS) {
                    currentTurn = 1;
                    currentTeam = TEAM_1;
                    currentRound += 1;
                    roundTV.setText("Round: " + currentRound);
                    promptTV.setText(currentTeam + " Go");
                }
                if (turn == 1) {
                    currentTurn = 2;
                    currentTeam = TEAM_2;
                    promptTV.setText(currentTeam + " Go");
                }

            }
        }.start();
    }

    private void handleSeenCard() {
        if (isPlaying) {
            int position = linearLayoutManager.findFirstVisibleItemPosition();
            TabooCard seenCard = playAdapter.getCardAtCurrentPosition(position);
            seenCards.add(seenCard);
            playAdapter.deleteCard(position);
        }
    }

}
