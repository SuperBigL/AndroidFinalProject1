package leban.nait.ca.finalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;

public class FinalProjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    DateFormat formatter;
    Chronometer chrono;
    TextClock clock;
    Button startButton, passButton, pauseButton, stopButton, caloriesButton, timesButton, stepsButton, finishButton;
    boolean resume = false, finalLap = false, finished = false;
    TextView currentDate, timeElapsed;
    Calendar c = Calendar.getInstance();
    ListView laps;
    String runner1 = RegisterPlayersActivity.runner1;
    String runner2 = RegisterPlayersActivity.runner2;
    String runner3 = RegisterPlayersActivity.runner3;
    String runner4 = RegisterPlayersActivity.runner4;
    String runner5 = RegisterPlayersActivity.runner5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_final_project);
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
        laps = (ListView) findViewById(R.id.laps);
        caloriesButton.setEnabled(false);
        timesButton.setEnabled(false);
        stepsButton.setEnabled(false);
        finishButton.setEnabled(false);




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
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.startbutton: {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                if (!resume) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                }
                chrono.start();
                break;
            }

            case R.id.stopbutton: {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                chrono.stop();
                resume = true;
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
