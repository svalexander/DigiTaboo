package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.database.TabooDatabase;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;

public class AddCardActivity extends AppCompatActivity {

    private FloatingActionButton closeBtn;
    private Button submitBtn;
    private EditText taboo, one, two, three, four, five;
    private TabooViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        initViews();
        handleClickActions();
        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
    }

    private void initViews() {
        closeBtn = findViewById(R.id.close_frag_btn);
        submitBtn = findViewById(R.id.submit_btn);
        taboo = findViewById(R.id.add_word_edit);
        one = findViewById(R.id.word_one_edit);
        two = findViewById(R.id.word_two_edit);
        three = findViewById(R.id.word_three_edit);
        four = findViewById(R.id.word_four_edit);
        five = findViewById(R.id.word_five_edit);
    }

    private void handleClickActions() {
        closeBtn.setOnClickListener(v -> closeAndAddCard());
        submitBtn.setOnClickListener(v -> closeAndAddCard());
    }

    private void closeAndAddCard() {
        if (getCardDetails() != null) {
            viewModel.insert(getCardDetails());
        }
        Intent intent = new Intent(AddCardActivity.this, ManageDecksActivity.class);
        startActivity(intent);
    }

    private TabooCard getCardDetails() {
        String tabooStr = taboo.getText().toString();
        String oneStr = one.getText().toString();
        String twoStr = two.getText().toString();
        String threeStr = three.getText().toString();
        String fourStr = four.getText().toString();
        String fiveStr = five.getText().toString();

        TabooCard card = new TabooCard(tabooStr, oneStr, twoStr, threeStr, fourStr, fiveStr, TabooDatabase.DEFAULT_DECK_ID);
        return card;
    }
}
