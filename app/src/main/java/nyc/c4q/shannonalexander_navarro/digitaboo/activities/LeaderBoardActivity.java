package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LeaderBoardActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
