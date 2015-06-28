package sithlords.com.healingbeacon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;


public class HistoryActivity extends ActionBarActivity {

    private ListView listView;
    private int beaconID;

    private Map<Integer, List<History>> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_history);

        // Find list view displaying patients in range
        listView = (ListView) findViewById(R.id.history_list_view);

        // Initialize static collection with all data 'cause ERYK SUCKS !!! xD
        histories = newHashMap();
        List<History> list = new ArrayList<>();
        list.add(new History("2015-02-11", "Broke a leg"));
        list.add(new History("2015-04-18", "Caught a flu"));
        list.add(new History("2015-04-19", "Leg totally recovered"));
        list.add(new History("2015-04-25", "Broke a left arm"));
        list.add(new History("2015-05-11", "Arm totally recovered"));
        list.add(new History("2015-05-12", "Lost a tooth"));
        list.add(new History("2015-05-14", "Tooth has been replaced"));
        list.add(new History("2015-06-01", "Caught a flu"));
        list.add(new History("2015-06-04", "Flu turned into pneumonia"));
        list.add(new History("2015-06-10", "State of patient got worse"));
        list.add(new History("2015-06-11", "Patient died"));
        histories.put(1, list);
        histories.put(2,list);
        histories.put(3,list);
        histories.put(4,list);
        histories.put(5,list);
        histories.put(6,list);

        // Set adapter
        beaconID = getIntent().getExtras().getInt("BEACON ID");
        HistoryAdapter adapter = new HistoryAdapter(this, R.layout.history_list_item, histories.get(beaconID));
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
}
