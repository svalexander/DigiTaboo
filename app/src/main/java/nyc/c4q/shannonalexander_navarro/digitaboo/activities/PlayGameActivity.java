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
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TabooViewModel viewModel;

    private TextView countDownTV, startTV, roundTV, teamTV, promptTV, teamOneScoreTV, teamTwoScoreTV;
    private Button correctButton, skipButton, tabooButton;
    private ArrayList<TabooCard> seenCards = new ArrayList<>();
    private static final String TEAM_1 = "Team 1";
    private static final String TEAM_2 = "Team 2";
    private String currentTeam;
    int currentTurn = 1;
    boolean isPlaying;
    Game game = new Game();
    final int NUM_ROUNDS = game.getMaxRounds();
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
        createStartingGameInfo();
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
            displayStartingScore();
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

    private void createStartingGameInfo() {
        Team teamOne = new Team();
        teamOne.setName("Team 1");
        teamOne.setScore(0);
        Team teamTwo = new Team();
        teamTwo.setName("Team 2");
        teamTwo.setScore(0);
        List<Team> teamsPlaying = new ArrayList<>();
        teamsPlaying.add(teamOne);
        teamsPlaying.add(teamTwo);
        game.setTeams(teamsPlaying);
        game.setMaxTurns(teamsPlaying.size());
        game.setCurrentRound(1);
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
        int prevScore = game.getTeams().get(index).getScore();
        game.getTeams().get(index).setScore(prevScore + addend);

        if (currentTurn == 1 && isPlaying) {
            teamOneScoreTV.setText("Team One Score: " + game.getTeams().get(index).getScore());
        }
        if (currentTurn == 2 && isPlaying) {
            teamTwoScoreTV.setText("Team Two Score: " + game.getTeams().get(index).getScore());
        }
    }

    private void startCountDown(int turn) {
        showViews();
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                promptTV.setVisibility(View.INVISIBLE);
                if (currentTurn == 1 && game.getCurrentRound() == 1) {
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
                if (game.getCurrentRound() == NUM_ROUNDS && turn == 2) {
                    promptTV.setVisibility(View.INVISIBLE);
//                    game.setTeamOneScore(teamOneScore);
//                    game.setTeamTwoScore(teamTwoScore);
                    startLeaderboardActivity();
                }
                if (turn == 2 && game.getCurrentRound() < NUM_ROUNDS) {
                    currentTurn = 1;
                    currentTeam = TEAM_1;
                    game.setCurrentRound(game.getCurrentRound()+1);
                    roundTV.setText("Round: " + game.getCurrentRound());
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

    private void startLeaderboardActivity() {
        Intent intent = new Intent(PlayGameActivity.this, LeaderBoardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_BUNDLE_KEY, game);
        intent.putExtra(GAME_INTENT_KEY, bundle);
        startActivity(intent);
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
