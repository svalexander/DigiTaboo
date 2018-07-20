package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ManageDecksActivity extends AppCompatActivity {

    private TabooViewModel viewModel;
    private DeckAdapter adapter;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_decks);
        initRv();
        initViews();
        launchAddCardActivity();

        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> adapter.setCards(words));

        //TODO: delete item from db if clicked
    }



    private void initViews() {
        actionButton = findViewById(R.id.launch_fragment_btn);
    }

    private void launchAddCardActivity() {
        actionButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManageDecksActivity.this, AddCardActivity.class);
            startActivityForResult(intent, 1);

        });
    }

    private void initRv() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new DeckAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new OverlapCards());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra(AddCardActivity.BUNDLE_KEY);
            TabooCard card = (TabooCard) bundle.getSerializable(AddCardActivity.SERIALIZABLE_KEY);
            viewModel.insert(card);
        }
    }

    public class OverlapCards extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            //TODO: stack cards like a deck
            int position = parent.getChildAdapterPosition(view);

            if (position != 0) {
                outRect.offsetTo(position + 80, 80);
            }

        }
    }
}
