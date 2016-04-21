package leban.nait.ca.finalproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2016-04-16.
 */
public class StepsGraph extends Activity {

    static final String TAG = "Watton";
    Team team = new Team();
    GraphView stepGraph;
    BarGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.stepsgraph);
        stepGraph = (GraphView) findViewById(R.id.stepgraph);
        stepGraph.setTitle("Steps");
        int lapCount = team.getTeamSize();
        List<BarGraphSeries<DataPoint>> teamList = new ArrayList<BarGraphSeries<DataPoint>>();

        DataPoint[] points = new DataPoint[lapCount];
        for (int i = 0; i < lapCount; i++) {
            Log.d(TAG, "Entering the player's stats....");
            teamList.add(new BarGraphSeries<DataPoint>(new DataPoint[]{new DataPoint(i + 1, team.getRunnerSteps(i))}));
            (teamList.get(i)).setTitle(team.getRunnerName(i));
            stepGraph.addSeries(teamList.get(i));
            teamList.get(i).setSpacing(35);
            teamList.get(i).setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                }
            });

            teamList.get(i).setDrawValuesOnTop(true);


        }
        stepGraph.getViewport().setMinX(-1);
        stepGraph.getViewport().setMaxX(8);
        stepGraph.getViewport().setXAxisBoundsManual(true);



    }
}
