package nyc.c4q.shannonalexander_navarro.digitaboo.models;

import java.io.Serializable;

public class Game implements Serializable {

    private boolean isPlaying;
    private int teamOneScore;
    private int teamTwoScore;
    private int currentRound;
    private int maxRounds;
    private int currentTurn;

//    public Game(boolean isPlaying, int teamOneScore, int teamTwoScore, int currentRound, int maxRounds, int currentTurn) {
//        this.isPlaying = isPlaying;
//        this.teamOneScore = teamOneScore;
//        this.teamTwoScore = teamTwoScore;
//        this.currentRound = currentRound;
//        this.maxRounds = maxRounds;
//        this.currentTurn = currentTurn;
//    }

//    public Game(int teamOneScore, int teamTwoScore) {
//        this.teamOneScore = teamOneScore;
//        this.teamTwoScore = teamTwoScore;
//    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
