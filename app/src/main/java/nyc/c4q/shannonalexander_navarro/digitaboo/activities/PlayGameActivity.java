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
import java.util.List;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.Game;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.Team;
import nyc.c4q.shannonalexander_navarro.digitaboo.rv.PlayAdapter;

public class PlayGameActivity extends AppCompatActivity {

    private PlayAdapter playAdapter;
    private LinearLayoutManager linearLayoutManager;

    private TextView countDownTV, roundTV, teamTV, promptTV, teamOneScoreTV, teamTwoScoreTV;
    private Button correctButton, skipButton, tabooButton;
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private static final String TEAM_1 = "Team 1";
    private static final String TEAM_2 = "Team 2";
    private String currentTeam;
    int currentTurn = 1;
    Game game = new Game();
    final int NUM_ROUNDS = game.getMaxRounds();
    public static final String GAME_BUNDLE_KEY = "Game bundle";
    public static final String GAME_INTENT_KEY = "Game extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        initViews();
        createStartingGameInfo();
        startRound();
        handleButtons();
    }

    private void initRv() {
        RecyclerView recyclerView = findViewById(R.id.play_rv);
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
        countDownTV = findViewById(R.id.countdown_tv);
        correctButton = findViewById(R.id.correct_btn);
        skipButton = findViewById(R.id.skip_btn);
        tabooButton = findViewById(R.id.buzz_btn);
    }

    private void observeDB() {
        TabooViewModel viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> {
            Collections.shuffle(words);
            playAdapter.setCards(words);
        });

    }

    private void startRound() {
        displayStartingScore();
        initRv();
        observeDB();
        teamTV.setText(currentTeam);
        promptTV.setVisibility(View.INVISIBLE);
        startCountDown(currentTurn);
        gamePlay();

    }

    private void createStartingGameInfo() {
        Team teamOne = new Team();
        teamOne.setName(TEAM_1);
        teamOne.setScore(0);
        Team teamTwo = new Team();
        teamTwo.setName(TEAM_2);
        teamTwo.setScore(0);
        List<Team> teamsPlaying = new ArrayList<>();
        teamsPlaying.add(teamOne);
        teamsPlaying.add(teamTwo);
        game.setTeams(teamsPlaying);
        game.setMaxTurns(teamsPlaying.size());
    }

    private void displayStartingScore() {
        teamOneScoreTV.setText("Team One Score: " + game.getTeams().get(0).getScore());
        teamTwoScoreTV.setText("Team Two Score: " + game.getTeams().get(1).getScore());
    }

    private void gamePlay() {
        roundTV.setText("Round: " + game.getCurrentRound());
        promptTV.setOnClickListener(v -> {
            if (game.getCurrentRound() <= NUM_ROUNDS) {
                startCountDown(currentTurn);
            }
        });
    }

    private void handleButtons() {
        correctButton.setOnClickListener(v -> {
            changeScore(1);
            handleSeenCard();
        });
        tabooButton.setOnClickListener(v -> {
            changeScore(-1);
            handleSeenCard();
        });
        skipButton.setOnClickListener(v -> handleSeenCard());
    }

    private void changeScore(int addend) {
        int index = currentTurn - 1;

        if (game.isPlaying()) {
            int prevScore = game.getTeams().get(index).getScore();
            game.getTeams().get(index).setScore(prevScore + addend);
        }

        if (currentTurn == 1 && game.isPlaying()) {
            teamOneScoreTV.setText("Team One Score: " + game.getTeams().get(index).getScore());
        }
        if (currentTurn == 2 && game.isPlaying()) {
            teamTwoScoreTV.setText("Team Two Score: " + game.getTeams().get(index).getScore());
        }
    }

    private void startCountDown(int turn) {
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                promptTV.setVisibility(View.INVISIBLE);
                String teamName = (currentTurn == 1 && game.getCurrentRound() == 1) ? TEAM_1 : currentTeam;
                teamTV.setText(teamName);
                long time = millisUntilFinished / 1000;
                countDownTV.setText(String.valueOf(time));
                game.setPlaying(true);
            }

            @Override
            public void onFinish() {
                promptTV.setVisibility(View.VISIBLE);
                game.setPlaying(false);
                if (game.getCurrentRound() == NUM_ROUNDS && turn == 2) {
                    promptTV.setVisibility(View.INVISIBLE);
                    startLeaderboardActivity();
                }
                if (turn == 2 && game.getCurrentRound() < NUM_ROUNDS) {
                    currentTurn = 1;
                    currentTeam = TEAM_1;
                    changeRound();
                    setTeamPrompt();
                }
                if (turn == 1) {
                    currentTurn = 2;
                    currentTeam = TEAM_2;
                    setTeamPrompt();
                }

            }
        }.start();
    }

    private void changeRound() {
        game.setCurrentRound(game.getCurrentRound() + 1);
        roundTV.setText("Round: " + game.getCurrentRound());
    }

    private void setTeamPrompt() {
        promptTV.setText(currentTeam + " Go");
    }

    private void startLeaderboardActivity() {
        Intent intent = new Intent(PlayGameActivity.this, LeaderBoardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_BUNDLE_KEY, game);
        intent.putExtra(GAME_INTENT_KEY, bundle);
        startActivity(intent);
    }

    private void handleSeenCard() {
        if (game.isPlaying()) {
            int position = linearLayoutManager.findFirstVisibleItemPosition();
            TabooCard seenCard = playAdapter.getCardAtCurrentPosition(position);
            seenCards.add(seenCard);
            playAdapter.deleteCard(position);
        }
    }
}
