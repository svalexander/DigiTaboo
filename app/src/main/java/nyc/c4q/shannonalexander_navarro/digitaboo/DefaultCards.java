package nyc.c4q.shannonalexander_navarro.digitaboo;

import java.util.ArrayList;
import java.util.List;

public class DefaultCards {

    public static List<TabooCard> createDefaultCards() {
        List<TabooCard> defaultList = new ArrayList<>();
//        defaultList.add(new TabooCard("Meme", "gif", "popular",
//                "picture", "facebook", "viral", TabooDatabase.DEFAULT_DECK_ID));
//        defaultList.add(new TabooCard("Chicken Salad", "lunch", "dressing",
//                "eat", "lettuce", "tomato", TabooDatabase.DEFAULT_DECK_ID));
//        defaultList.add(new TabooCard("Birthday Party", "balloons", "candles",
//                "cake", "gifts", "song", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Android", "green", "google",
                "programmer", "java", "man", TabooDatabase.DEFAULT_DECK_ID));
        return defaultList;
    }
}
