package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddCardFragment extends Fragment {

    private FloatingActionButton closeBtn;
    private Button submitBtn;
    private EditText taboo, one, two, three, four, five;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.add_card_fragment, container, false);
        initViews(root);
        handleClickActions();
        return root;
    }

    private void initViews(View view) {
        closeBtn = view.findViewById(R.id.close_frag_btn);
        submitBtn = view.findViewById(R.id.submit_btn);
        taboo = view.findViewById(R.id.add_word_edit);
        one = view.findViewById(R.id.word_one_edit);
        two = view.findViewById(R.id.word_two_edit);
        three = view.findViewById(R.id.word_three_edit);
        four = view.findViewById(R.id.word_four_edit);
        five = view.findViewById(R.id.word_five_edit);
    }

    private void handleClickActions() {
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCardDetails();
            }
        });
    }

    private TabooCard getCardDetails() {
        String tabooStr = taboo.getText().toString();
        String oneStr = one.getText().toString();
        String twoStr = two.getText().toString();
        String threeStr = three.getText().toString();
        String fourStr = four.getText().toString();
        String fiveStr = five.getText().toString();

        TabooCard card = new TabooCard(tabooStr, oneStr, twoStr, threeStr, fourStr, fiveStr, "default");
        return card;
    }

    private void closeFragment() {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("cardKey", getCardDetails());
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
