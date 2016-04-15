package leban.nait.ca.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Owner on 2016-04-14.
 */
public class GraphResults extends Activity
{
    private BarGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphresults);
        GraphView weatherGraph = (GraphView)findViewById(R.id.weathergraph);
        series = new BarGraphSeries<DataPoint>();
        Viewport viewport = weatherGraph.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(30);
        viewport.setMinY(0);
        viewport.setMaxY(1000);
        viewport.setScrollable(true);
    }
}
