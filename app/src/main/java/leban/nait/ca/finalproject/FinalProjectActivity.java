package leban.nait.ca.finalproject;

import android.os.Bundle;
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
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;

import java.util.Date;
import java.util.zip.CRC32;

public class FinalProjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Chronometer chrono;
    TextClock clock;
    Button startButton, passButton, pauseButton, stopButton, caloriesButton, timesButton, stepsButton, finishButton;
    boolean resume = false;

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

        caloriesButton.setEnabled(false);
        timesButton.setEnabled(false);
        stepsButton.setEnabled(false);
        finishButton.setEnabled(false);




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
    public void onClick(View v)
    {

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
