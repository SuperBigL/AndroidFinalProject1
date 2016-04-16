package leban.nait.ca.finalproject;

/**
 * Created by Owner on 2016-04-15.
 */
public class Team {


    int teamSize = 2;
    static final int MAXTEAM = 5;

    public Runner[] runners = new Runner[teamSize];


    public int getTeamSize() {
        return teamSize;
    }

    public void addRunner(Runner runner) throws Exception {
        if (teamSize < MAXTEAM) {
            teamSize++;
            runners[teamSize - 1] = runner;
        } else {
            throw new Exception("No more runners! This team is full!");
        }

    }

    public String getRunnerName(int number) {
        String name;
        name = runners[number].getRunnerName();
        return name;
    }

    public void setRunnerTime(int number, long time) {
        runners[number].setRunnerTime(time);

    }


}
