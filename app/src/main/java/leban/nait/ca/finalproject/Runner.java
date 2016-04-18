package leban.nait.ca.finalproject;

/**
 * Created by Owner on 2016-04-15.
 */
public class Runner {


    private int runnerNumber;
    private String runnerName;
    private double runnerSteps;

    private double runnerTime;



    public int getRunnerNumber() {
        return runnerNumber;
    }

    public void setRunnerNumber(int runnerNumber) {
        this.runnerNumber = runnerNumber;
    }


    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) throws Exception {
        this.runnerName = runnerName;
        if (runnerName == null) {
            runnerName = null;
            runnerNumber = 0;
            runnerTime = 00;
        }


    }

    public double getRunnerTime() {
        return runnerTime;
    }

    public void setRunnerTime(double runnerTime) {
        this.runnerTime = runnerTime;
    }

    public double getRunnerSteps() {
        return runnerSteps;
    }


    public void setRunnerSteps(double runnerSteps) {
        this.runnerSteps = runnerSteps;
    }


}
