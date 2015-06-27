package sithlords.com.healingbeacon.patients;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;

import static com.google.common.collect.Lists.newArrayList;

public class PatientTemperatureActivity extends ActionBarActivity {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_patient_temperature);

        PatientCard patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        List<Entry> entries = newArrayList();
        List<TemperatureMeasurement> measurements = patientCard.getTemperatureMeasurements();
        Collections.sort(measurements);
        List<String> xVals = newArrayList();
        int i = 0;
        for (TemperatureMeasurement temp : measurements) {
            Entry entry = new Entry((float) temp.getDegreeCelcius(), i++);
            entries.add(entry);
            xVals.add(DATE_FORMAT.format(temp.getMeasurementTime()));
        }

        LineDataSet set = new LineDataSet(entries, "Temperatures");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setCircleColors(new int[]{R.color.red});

        List<LineDataSet> sets = newArrayList(set);
        LineData data = new LineData(xVals, sets);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_temperature, menu);
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
