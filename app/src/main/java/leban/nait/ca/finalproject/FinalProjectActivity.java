package leban.nait.ca.finalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FinalProjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, SensorEventListener {
    static final String TAG = "Watton";
    DateFormat formatter;
    Chronometer chrono;
    TextClock clock;
    Button startButton, passButton, pauseButton, stopButton, caloriesButton, timesButton, stepsButton, finishButton, distanceButton;
    boolean running = false, finalLap = false, finished = false;
    TextView currentDate, timeElapsed, stepsTaken;
    Calendar c = Calendar.getInstance();
    Sensor sensorCount, sensorDetector, motion;
    SensorManager sManager, stepManager;
    ArrayList laps = new ArrayList();
    ListView lap;
    double steps;
    Team team = new Team();
    int lapsCount, stepCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_final_project);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorCount = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorDetector = sManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        motion = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, motion, SensorManager.SENSOR_DELAY_FASTEST);
        clock = (TextClock) findViewById(R.id.currenttime);
        startButton = (Button) findViewById(R.id.startbutton);
        passButton = (Button) findViewById(R.id.passoff);
        pauseButton = (Button) findViewById(R.id.pausebutton);
        stopButton = (Button) findViewById(R.id.stopbutton);
        caloriesButton = (Button) findViewById(R.id.viewbycalories);
        timesButton = (Button) findViewById(R.id.viewbytime);
        stepsButton = (Button) findViewById(R.id.viewbysteps);
        finishButton = (Button) findViewById(R.id.finishbutton);
        currentDate = (TextView) findViewById(R.id.dateAndTime);
        timeElapsed = (TextView) findViewById(R.id.chronotext);
        distanceButton = (Button) findViewById(R.id.viewbydistance);
        stepsTaken = (TextView) findViewById(R.id.stepcount);
        lap = (ListView) findViewById(R.id.laps);
        caloriesButton.setEnabled(false);
        timesButton.setEnabled(false);
        stepsButton.setEnabled(false);
        distanceButton.setEnabled(false);
        finishButton.setEnabled(false);
        stepsTaken.setText("0");
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);
        passButton.setEnabled(false);




    }


    private void updateLabel() {
        currentDate.setText(formatter.format(c.getTime()));
    }


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };


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
        if (running) {
            sManager.registerListener(this, sensorCount, SensorManager.SENSOR_DELAY_FASTEST);
            sManager.registerListener(this, sensorDetector, SensorManager.SENSOR_DELAY_FASTEST);
        }



    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.startbutton: {
                running = true;
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                passButton.setEnabled(true);
                if (running) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                }
                chrono.start();
                break;
            }

            case R.id.pausebutton: {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                passButton.setEnabled(false);
                pauseButton.setEnabled(false);
                chrono.stop();


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
                team.getRunnerName(lapsCount);
                team.setRunnerTime(lapsCount, timeElapsed);
                String lapInfo = team.getRunnerName(lapsCount) + ": " + String.valueOf(team.getRunnerTime(lapsCount));
                laps.add(lapInfo);
                stepsTaken.setText("0");
                ArrayAdapter<String> runnerLaps = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, laps);
                lap.setAdapter(runnerLaps);
                lapsCount++;
                int runnerCount = team.getTeamSize();

                if (lapsCount == runnerCount) {
                    finalLap = true;
                    finishButton.setEnabled(true);
                    passButton.setEnabled(false);
                }


                chrono.setText("00:00");
                chrono.start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);


                break;
            }

            case R.id.finishbutton: {
                chrono.stop();
                long timeElapsed = SystemClock.elapsedRealtime() - chrono.getBase();
                team.getRunnerName(lapsCount);
                team.setRunnerTime(lapsCount, timeElapsed);
                chrono.setText("00:00");
                finished = true;
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                pauseButton.setEnabled(false);
                passButton.setEnabled(false);
                finishButton.setEnabled(false);
                stepsButton.setEnabled(true);
                caloriesButton.setEnabled(true);
                timesButton.setEnabled(true);
                distanceButton.setEnabled(true);
                break;


            }

            case R.id.resetbutton: {
                stepCount = 0;


                break;
            }

            case R.id.viewbytime: {
                Intent intent = new Intent(this, TimesGraph.class);
                this.startActivity(intent);
                break;
            }

            case R.id.viewbydistance: {
                Intent intent = new Intent(this, DistanceGraph.class);
                this.startActivity(intent);
                break;
            }

            case R.id.viewbysteps: {
                Intent intent = new Intent(this, StepsGraph.class);
                this.startActivity(intent);
                break;
            }

            case R.id.viewbycalories: {
                Intent intent = new Intent(this, CaloriesGraph.class);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            stepsTaken.setText(stepCount);
            stepCount++;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
