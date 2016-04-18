package leban.nait.ca.finalproject;

import android.app.Activity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by Owner on 2016-04-16.
 */
public class StepsGraph extends Activity {

    Team team = new Team();
    GraphView stepGraph;
    BarGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.stepsgraph);
        stepGraph = (GraphView) findViewById(R.id.stepgraph);
        int lapCount = team.getTeamSize();
        DataPoint[] points = new DataPoint[lapCount];

    }
}
