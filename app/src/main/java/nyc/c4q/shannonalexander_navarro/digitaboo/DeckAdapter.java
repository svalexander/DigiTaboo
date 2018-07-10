package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DeckAdapter extends RecyclerView.Adapter<DeckViewHolder> {

    private List<TabooCard> tabooWordList;
    private LayoutInflater inflater;

    DeckAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.card_item_view, parent, false);
        return new DeckViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        if (tabooWordList != null) {
            TabooCard currentCard = tabooWordList.get(position);
            holder.bind(currentCard);
        }
    }

    void setCards(List<TabooCard> cards) {
        tabooWordList = cards;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (tabooWordList != null) {
            return tabooWordList.size();
        } else return 0;
    }
}
