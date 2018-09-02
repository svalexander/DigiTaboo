package nyc.c4q.shannonalexander_navarro.digitaboo.models;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    private boolean isPlaying;
    private int teamOneScore;
    private int teamTwoScore;
    private int currentRound;
    private int maxRounds;
    private int currentTurn;
    private int maxTurns;
    private List<Team> teams;

    public int getMaxTurns() {
        maxTurns = teams.size();
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

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
        if (maxRounds == 0) {
            maxRounds = 10;
        }
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
