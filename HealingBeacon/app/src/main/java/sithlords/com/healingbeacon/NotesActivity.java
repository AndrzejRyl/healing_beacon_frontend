package sithlords.com.healingbeacon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class NotesActivity extends ActionBarActivity {

    private ListView listView;
    private List<NotesPair> notes;
    private int beaconID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_notes);

        listView = (ListView)findViewById(R.id.notes_list);

        beaconID = getIntent().getExtras().getInt("BEACON ID");

        notes = getNotes(beaconID);
        NotesAdapter adapter = new NotesAdapter(this, R.layout.notes_item, notes);
        listView.setAdapter(adapter);
    }

    // Read notes from files (names of files are IDs of notes
    // AND those names start with the beaconID of patient we're making those notes about
    private List<NotesPair> getNotes(int beaconID) {
        // TODO: Return list from file!!
        List<NotesPair> result = newArrayList();
        result.add(new NotesPair(1,2,"This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?",
                "This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?"));
        result.add(new NotesPair(3,4,"This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?",
                        "This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?"));
        result.add(new NotesPair(5,6,"This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?",
                        "This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?"));
        result.add(new NotesPair(7,8,"This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?",
                        "This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?"));
        result.add(new NotesPair(9,10,"This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?",
                        "This is a test content. I'm trying to make it a little long. Maybe I succeed. Don't you think?"));
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes, menu);
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
