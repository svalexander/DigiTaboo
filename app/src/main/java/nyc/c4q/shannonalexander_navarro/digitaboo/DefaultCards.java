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
//        defaultList.add(new TabooCard("Android", "green", "google",
//                "programmer", "java", "man", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Laptop", "computer", "mac",
                "pc", "work", "wifi", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("iPhone", "apple", "millenial",
                "popular", "phone", "screen", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Recession", "2008", "millenial",
                "housing", "crisis", "money", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Heart Surgeon", "doctor", "surgery",
                "medical", "operation", "health", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Gold", "metal", "jewelry",
                "expensive", "shiny", "hard", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Starbucks", "coffee", "Seattle",
                "expensive", "frappuccino","latte", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Summer Camp", "school", "vacation",
                "swimming", "woods", "games", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("College", "university", "higher education",
                "debt", "harvard", "yale", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Global Warming", "denial", "science",
                "hoax", "pollution", "climate", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Ice cream", "cold", "chocolate",
                "vanilla", "sprinkles", "sundae", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("balance", "beam", "fall",
                "ear", "stand", "steady", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Kale", "food", "green",
                "spinach", "salad", "eat", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Espresso", "coffee", "beans",
                "Starbucks", "caffeine", "latte", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("ambulance", "emergency", "hospital",
                "expensive", "medical", "accident", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Tattoo", "permanent", "ink",
                "artist", "alternative", "dagger", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Hip hop", "music", "rap",
                "beats", "new york", "track", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Vegan", "vegetarian", "milk",
                "eggs", "eat", "cheese", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Intern", "school", "college",
                "credit", "free","work", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("Whisky", "bourbon", "Tennessee",
                "alcohol", "ginger ale", "drink", TabooDatabase.DEFAULT_DECK_ID));
        defaultList.add(new TabooCard("No Doubt", "spider web", "gwen stefani",
                "don't speak", "ska", "solo", TabooDatabase.DEFAULT_DECK_ID));
        return defaultList;
    }
}
