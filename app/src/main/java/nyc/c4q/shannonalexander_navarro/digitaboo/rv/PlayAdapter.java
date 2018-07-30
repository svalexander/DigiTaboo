package nyc.c4q.shannonalexander_navarro.digitaboo.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;

public class PlayAdapter extends RecyclerView.Adapter<PlayViewHolder> {
    private List<TabooCard> gameWordList;
    private LayoutInflater inflater;

    public PlayAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.playing_card_item_view, parent, false);
        return new PlayViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayViewHolder holder, int position) {
        if (gameWordList != null) {
            TabooCard currentCard = gameWordList.get(position);
            holder.bind(currentCard);
        }
    }

    public void setCards(List<TabooCard> cards) {
        gameWordList = cards;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (gameWordList != null) {
            return gameWordList.size();
        } else return 0;
    }
}
