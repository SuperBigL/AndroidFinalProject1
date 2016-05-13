package leban.nait.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalProjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    static final String TAG = "Watton";
    Chronometer chrono;
    TextClock clock;
    Button startButton, passButton, pauseButton, stopButton, timesButton, finishButton, setupButton, clearButton, resetButton;
    boolean running = false, finalLap = false, finished = false;
    TextView currentDate, timeElapsed;
    ArrayList laps = new ArrayList();
    ListView lap;
    Team team;
    int lapsCount = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_final_project);

        chrono = (Chronometer) findViewById(R.id.chrono);
        clock = (TextClock) findViewById(R.id.currenttime);
        startButton = (Button) findViewById(R.id.startbutton);
        passButton = (Button) findViewById(R.id.passoff);
        pauseButton = (Button) findViewById(R.id.pausebutton);
        stopButton = (Button) findViewById(R.id.stopbutton);
        setupButton = (Button) findViewById(R.id.teamsetup);
        resetButton = (Button) findViewById(R.id.resetbutton);
        clearButton = (Button) findViewById(R.id.clearallbutton);
        timesButton = (Button) findViewById(R.id.viewbytime);
        finishButton = (Button) findViewById(R.id.finishbutton);
        currentDate = (TextView) findViewById(R.id.dateAndTime);
        timeElapsed = (TextView) findViewById(R.id.chronotext);
        lap = (ListView) findViewById(R.id.laps);
        timesButton.setEnabled(false);
        finishButton.setEnabled(false);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);
        passButton.setEnabled(false);
        team = (Team) getIntent().getSerializableExtra("team");
        Log.d(TAG, "Done creating stopwatch");





    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        running = false;

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.startbutton: {

                if (!running) {

                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                }
                running = true;
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                passButton.setEnabled(true);
                resetButton.setEnabled(true);


                break;
            }

            case R.id.pausebutton: {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                passButton.setEnabled(false);
                pauseButton.setEnabled(false);
                chrono.stop();


            }

            case R.id.clearallbutton: {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.stop();
                laps.clear();
                ArrayAdapter<String> runnerLaps = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, laps);
                lap.setAdapter(runnerLaps);
                lapsCount = 1;
                if (running) {
                    running = false;
                }
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    pauseButton.setEnabled(false);
                    passButton.setEnabled(false);
                    finishButton.setEnabled(false);
                    timesButton.setEnabled(false);

                break;
            }

            case R.id.stopbutton: {
                running = false;
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                pauseButton.setEnabled(false);
                chrono.stop();

                break;
            }

            case R.id.passoff: {
                chrono.stop();
                double timeElapsed = SystemClock.elapsedRealtime() - chrono.getBase();
                team.setRunnerTime(lapsCount - 1, timeElapsed);
                team.getRunnerName(lapsCount - 1);
                String lapInfo = team.getRunnerName(lapsCount - 1) + ": " + String.valueOf(team.getRunnerTime(lapsCount - 1) / 1000);
                laps.add(lapInfo);
                ArrayAdapter<String> runnerLaps = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, laps);
                lap.setAdapter(runnerLaps);
                int runnerCount = team.getTeamSize();
                lapsCount++;
                if (lapsCount == runnerCount) {
                    finalLap = true;
                    finishButton.setEnabled(true);
                    passButton.setEnabled(false);
                }


                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);


                break;
            }

            case R.id.finishbutton: {
                chrono.stop();
                running = false;
                double timeElapsed = SystemClock.elapsedRealtime() - chrono.getBase();
                team.setRunnerTime(lapsCount - 1, timeElapsed);
                team.getRunnerName(lapsCount - 1);
                String lapInfo = team.getRunnerName(lapsCount - 1) + ": " + String.valueOf(team.getRunnerTime(lapsCount - 1) / 1000);
                laps.add(lapInfo);
                ArrayAdapter<String> runnerLaps = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, laps);
                lap.setAdapter(runnerLaps);

                finished = true;
                resetButton.setEnabled(false);
                startButton.setEnabled(false);
                stopButton.setEnabled(false);
                pauseButton.setEnabled(false);
                passButton.setEnabled(false);
                finishButton.setEnabled(false);
                timesButton.setEnabled(true);

                break;


            }

            case R.id.resetbutton: {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                lapsCount = 0;
                if (running) {
                    passButton.setEnabled(false);
                    finishButton.setEnabled(true);
                }
                break;
            }

            case R.id.viewbytime: {
                Intent intent = new Intent(this, TimesGraph.class);
                intent.putExtra("team", team);
                this.startActivity(intent);
                break;
            }


            case R.id.teamsetup: {
                Intent intent = new Intent(this, RegisterPlayersActivity.class);
                intent.putExtra("team", team);
                this.startActivity(intent);
                break;
            }


        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }


}