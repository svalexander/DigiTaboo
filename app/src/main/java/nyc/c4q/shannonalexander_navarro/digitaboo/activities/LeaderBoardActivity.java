package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.Game;

public class LeaderBoardActivity extends AppCompatActivity {

    private TextView teamOneFinalScoreTV, teamTwoFinalScoreTV, winnerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Game game = getGame();
        initViews();
        determineWinner(game);
    }

    private Game getGame() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(PlayGameActivity.GAME_INTENT_KEY);
        return (Game) bundle.getSerializable(PlayGameActivity.GAME_BUNDLE_KEY);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LeaderBoardActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void initViews() {
        teamOneFinalScoreTV = findViewById(R.id.team_one_final_score);
        teamTwoFinalScoreTV = findViewById(R.id.team_two_final_score);
        winnerTV = findViewById(R.id.winner_tv);
    }

    private void determineWinner(Game game) {
        int teamOne = game.getTeamOneScore();
        int teamTwo = game.getTeamTwoScore();

        teamTwoFinalScoreTV.setText("Team Two Score: " + teamTwo);
        teamOneFinalScoreTV.setText("Team One Score: " + teamOne);

        if (teamOne > teamTwo) {
            winnerTV.setText("Team One Wins!");
        } else if (teamOne == teamTwo) {
            winnerTV.setText("Tie");
        } else {
            winnerTV.setText("Team Two Wins!");
        }


    }
}
