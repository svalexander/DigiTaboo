package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    private FloatingActionButton closeBtn;
    private Button submitBtn;
    private EditText taboo, one, two, three, four, five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        initViews();
        handleClickActions();
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
        closeBtn.setOnClickListener(v -> closeAndSendCard());
        submitBtn.setOnClickListener(v -> closeAndSendCard());
    }

    private void closeAndSendCard() {
        Intent intent = new Intent(AddCardActivity.this, ManageDecksActivity.class);
        if(getCardDetails() == null){
            setResult(RESULT_CANCELED, intent);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("cardKey", getCardDetails());
            intent.putExtra("card_bundle", bundle);
            setResult(RESULT_OK, intent);
        }
        finish();
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

    //TODO: Write logic to make sure card is added to db when nav to this act from main
}
