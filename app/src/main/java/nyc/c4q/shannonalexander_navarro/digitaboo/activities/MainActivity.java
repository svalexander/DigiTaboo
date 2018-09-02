package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;

public class MainActivity extends AppCompatActivity {

    private Button mngButton, addButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        handleButtons();
    }

    private void handleButtons() {
        mngButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageDecksActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
            startActivity(intent);
        });
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        startButton = findViewById(R.id.startBtn);
        mngButton = findViewById(R.id.manageBtn);
        addButton = findViewById(R.id.add_wordBtn);
    }
}
