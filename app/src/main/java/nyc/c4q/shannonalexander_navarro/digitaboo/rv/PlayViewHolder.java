package nyc.c4q.shannonalexander_navarro.digitaboo.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.shannonalexander_navarro.digitaboo.R;
import nyc.c4q.shannonalexander_navarro.digitaboo.models.TabooCard;

public class PlayViewHolder extends RecyclerView.ViewHolder {



    private TextView wordToGuessTV, word1TV, word2TV, word3TV, word4TV, word5TV;
    private String one, two, three, four, five, guess;

    public PlayViewHolder(View itemView) {
        super(itemView);
        wordToGuessTV = itemView.findViewById(R.id.play_guess_word);
        word1TV = itemView.findViewById(R.id.play_word_one_tv);
        word2TV = itemView.findViewById(R.id.play_word_two_tv);
        word3TV = itemView.findViewById(R.id.play_word_three_tv);
        word4TV = itemView.findViewById(R.id.play_word_four_tv);
        word5TV = itemView.findViewById(R.id.play_word_five_tv);
    }

    public void bind(TabooCard currentCard) {
        one = currentCard.getTabooWord1();
        two = currentCard.getTabooWord2();
        three = currentCard.getTabooWord3();
        four = currentCard.getTabooWord4();
        five = currentCard.getTabooWord5();
        guess = currentCard.getWordToGuess();

        wordToGuessTV.setText(guess);
        word5TV.setText(five);
        word4TV.setText(four);
        word3TV.setText(three);
        word2TV.setText(two);
        word1TV.setText(one);

    }
}
