package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mngButton;
    private Button addButton;

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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mngButton = findViewById(R.id.manageBtn);
        addButton = findViewById(R.id.add_wordBtn);
    }
}
