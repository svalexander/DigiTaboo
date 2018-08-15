package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    private int emptyEditTexts = 6;

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
        submitBtn.setOnClickListener(v -> {
            checkEdittexts();
            if (emptyEditTexts == 0) {
                closeAndAddCard();
            }
            emptyEditTexts = 6;
        });
    }

    private void closeAndAddCard() {
        viewModel.insert(getCardDetails());
        Intent intent = new Intent(AddCardActivity.this, ManageDecksActivity.class);
        startActivity(intent);
    }

    private void checkEdittexts() {
        EditText[] editTexts = new EditText[6];
        editTexts[0] = taboo;
        editTexts[1] = one;
        editTexts[2] = two;
        editTexts[3] = three;
        editTexts[4] = four;
        editTexts[5] = five;

        for (int i = 0; i < editTexts.length; i++) {
            if (TextUtils.isEmpty(editTexts[i].getText())) {
                editTexts[i].setError("Please enter a word");
            } else {
                emptyEditTexts -= 1;
            }
        }
    }

    private TabooCard getCardDetails() {

        String tabooStr = taboo.getText().toString();
        String oneStr = one.getText().toString();
        String twoStr = two.getText().toString();
        String threeStr = three.getText().toString();
        String fourStr = four.getText().toString();
        String fiveStr = five.getText().toString();

        return new TabooCard(tabooStr, oneStr, twoStr, threeStr, fourStr, fiveStr, TabooDatabase.DEFAULT_DECK_ID);
    }
}
