package nyc.c4q.shannonalexander_navarro.digitaboo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.TabooViewModel;
import nyc.c4q.shannonalexander_navarro.digitaboo.rv.DeckAdapter;

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
        observeDB();

        //TODO: delete item from db if clicked
    }

    private void observeDB() {
        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, words -> adapter.setCards(words));

    }


    private void initViews() {
        actionButton = findViewById(R.id.launch_fragment_btn);
    }

    private void launchAddCardActivity() {
        actionButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManageDecksActivity.this, AddCardActivity.class);
            startActivity(intent);

        });
    }

    private void initRv() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new DeckAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new OverlapCards());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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
