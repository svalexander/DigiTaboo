package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class ManageDecksActivity extends AppCompatActivity {

    private TabooViewModel viewModel;
    private DeckAdapter adapter;
    private FloatingActionButton actionButton;
    private AddCardFragment addCardFragment = new AddCardFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_decks);
        initRv();
        initViews();
        launchFragment();

        viewModel = ViewModelProviders.of(this).get(TabooViewModel.class);
        viewModel.getAllCards().observe(this, new Observer<List<TabooCard>>() {
            @Override
            public void onChanged(@Nullable final List<TabooCard> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setCards(words);
            }
        });
    }

    private void initViews() {
        actionButton = findViewById(R.id.launch_fragment_btn);
    }

    private void launchFragment() {

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager addCardFragTransaction = getSupportFragmentManager();
                addCardFragTransaction.beginTransaction().replace(R.id.frame_layout, addCardFragment).commit();

            }
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

            int position = parent.getChildAdapterPosition(view);
            //this works but i need to bring them closer together

            if (position != 0) {

                outRect.offsetTo(-80, 80);
            }

        }
    }
}
