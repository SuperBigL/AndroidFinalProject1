package leban.nait.ca.finalproject;

import android.app.Activity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by Owner on 2016-04-16.
 */
public class TimesGraph extends Activity {
    Team team = new Team();
    GraphView timesGraph;
    BarGraphSeries<DataPoint> series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.timegraph);

        timesGraph = (GraphView) findViewById(R.id.timegraph);
        timesGraph.setTitle("Lap Time");
        int lapCount = team.getTeamSize();
        DataPoint[] points = new DataPoint[lapCount];
        for (int i = 1; i < lapCount; i++) {
            points[i] = new DataPoint(i, team.getRunnerTime(i));

        }
        series = new BarGraphSeries<DataPoint>(points);

    }

}
