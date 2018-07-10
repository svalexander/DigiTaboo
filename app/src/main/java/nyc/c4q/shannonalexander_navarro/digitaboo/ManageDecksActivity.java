package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

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
        viewModel.getAllCards().observe(this, new Observer<List<TabooCard>>() {
            @Override
            public void onChanged(@Nullable final List<TabooCard> words) {
                adapter.setCards(words);
            }
        });
    }

    private void initViews() {
        actionButton = findViewById(R.id.launch_fragment_btn);
    }

    private void launchAddCardActivity() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageDecksActivity.this, AddCardActivity.class);
                startActivityForResult(intent, 1);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle bundle = data.getBundleExtra("card_bundle");
            TabooCard card = (TabooCard) bundle.getSerializable("cardKey");
            viewModel.insert(card);
            Log.d("result", card.getTabooWord1());
        }
    }

    public class OverlapCards extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);

            if (position != 0) {
                outRect.offsetTo(-80, 80);
            }

        }
    }
}
