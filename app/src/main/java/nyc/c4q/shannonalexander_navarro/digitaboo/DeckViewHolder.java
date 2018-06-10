package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shannonalexander-navarro on 6/9/18.
 */

public class DeckViewHolder extends RecyclerView.ViewHolder {

    private TextView wordToGuessTV, word1TV, word2TV, word3TV, word4TV, word5TV;
    private String one, two, three, four, five, guess;

    public DeckViewHolder(View itemView) {
        super(itemView);
        wordToGuessTV = itemView.findViewById(R.id.word_to_guess);
        word1TV = itemView.findViewById(R.id.word_one_tv);
        word2TV = itemView.findViewById(R.id.word_two_tv);
        word3TV = itemView.findViewById(R.id.word_three_tv);
        word4TV = itemView.findViewById(R.id.word_four_tv);
        word5TV = itemView.findViewById(R.id.word_five_tv);
    }

    public void bind(TabooCard currentCard) {

        one = currentCard.getTabooWord1();
        two = currentCard.getTabooWord2();
        three = currentCard.getTabooWord3();
        four = currentCard.getTabooWord4();
        five = currentCard.getTabooWord5();
        guess = currentCard.getWordToGuess();

        Log.d("word?", guess+"");
        //ok log shows me i am getting the word...buuuuut i dont see it in my ui, why?
        //^the answer is that i was setting the text b4 getting the data, so there wasn't anything to set
        wordToGuessTV.setText(guess);
        word5TV.setText(five);
        word4TV.setText(four);
        word3TV.setText(three);
        word2TV.setText(two);
        word1TV.setText(one);
    }
}
