package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;

public class StartActivity extends AppCompatActivity {

    private TextView startTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        clickStart();
    }

    private void initView(){
        startTV = findViewById(R.id.start_tv);
    }

    private void clickStart(){

        startTV.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, PlayGameActivity.class);
            startActivity(intent);
        });
    }
}
