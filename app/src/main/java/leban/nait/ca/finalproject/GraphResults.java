package leban.nait.ca.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;

/**
 * Created by Owner on 2016-04-14.
 */
public class GraphResults extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphresults.xml);
        GraphView wethaerGraph = (GraphView)findViewById(R.id.weathergraph);
    }
}
