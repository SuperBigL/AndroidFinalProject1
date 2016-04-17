package leban.nait.ca.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Leban Abduallahi Mohamed on 2016-04-15.
 */


public class RegisterPlayersActivity extends Activity implements View.OnClickListener {
    Team team = new Team();
    EditText runner1Name, runner2Name, runner3Name, runner4Name, runner5Name;
    View v;
    public static String runner1, runner2, runner3, runner4, runner5;
    int runnerCount = team.getTeamSize();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runner1Name = (EditText) findViewById(R.id.runner1);
        runner2Name = (EditText) findViewById(R.id.runner2);
        runner3Name = (EditText) findViewById(R.id.runner3);
        runner4Name = (EditText) findViewById(R.id.runner4);
        runner5Name = (EditText) findViewById(R.id.runner5);
        Button reg = (Button) findViewById(R.id.submitrunners);
        reg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        runner1 = runner1Name.getText().toString();
        runner2 = runner2Name.getText().toString();
        runner3 = runner3Name.getText().toString();
        runner4 = runner4Name.getText().toString();
        runner5 = runner5Name.getText().toString();

        Runner realRunner1 = new Runner();
        try {
            realRunner1.setRunnerName(runner1);
        } catch (Exception e) {
            e.getMessage();
        }

        Runner realRunner2 = new Runner();
        try {
            realRunner2.setRunnerName(runner2);
        } catch (Exception e) {
            e.getMessage();
        }

        Runner realRunner3 = new Runner();
        try {
            realRunner3.setRunnerName(runner3);
        } catch (Exception e) {
            e.getMessage();
        }


        Runner realRunner4 = new Runner();
        try {
            realRunner4.setRunnerName(runner4);
        } catch (Exception e) {
            e.getMessage();
        }

        Runner realRunner5 = new Runner();
        try {
            realRunner5.setRunnerName(runner5);
        } catch (Exception e) {
            e.getMessage();
        }


        Intent intent = new Intent(this, FinalProjectActivity.class);
        this.startActivity(intent);

    }
}
