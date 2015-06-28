package sithlords.com.healingbeacon.patients;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.software.shell.fab.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import sithlords.com.healingbeacon.AddBloodPressureDialog;
import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.BloodMeasurement;
import sithlords.com.healingbeacon.model.PatientCard;

import static com.google.common.collect.Lists.newArrayList;

public class BloodPressureActivity extends ActionBarActivity {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd", Locale.US);
    private FloatingActionButton button;
    private int beaconID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_patient_temperature);

        PatientCard patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);
        beaconID = patientCard.getPatient().getBeaconID();

        LineChart chart = (LineChart) findViewById(R.id.chart);
        // Style action button
        button = (FloatingActionButton)findViewById(R.id.action_button);
        button.setImageResource(R.drawable.fab_plus_icon);
        button.setButtonColor(getResources().getColor(android.R.color.holo_red_dark));

        List<Entry> diastoleEntries = newArrayList();
        List<Entry> systoleEntries = newArrayList();
        List<BloodMeasurement> measurements = patientCard.getBloodPressureMeasurements();
        Collections.sort(measurements);
        List<String> xVals = newArrayList();
        int i = 0;
        for (BloodMeasurement temp : measurements) {
            Entry entry = new Entry(temp.getDiastole(), i++);
            diastoleEntries.add(entry);
            entry = new Entry(temp.getSystole(), i++);
            systoleEntries.add(entry);
            xVals.add(DATE_FORMAT.format(temp.getMeasurementTime()));
        }

        LineDataSet diastoleSet = new LineDataSet(diastoleEntries, "Diastole");
        LineDataSet systoleSet = new LineDataSet(systoleEntries, "Systole");
        diastoleSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        diastoleSet.setCircleColors(new int[]{R.color.red});
        systoleSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        systoleSet.setCircleColors(new int[]{R.color.red});

        List<LineDataSet> sets = newArrayList(diastoleSet, systoleSet);
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

    public void addData(View v) {
        AddBloodPressureDialog addBloodDialog = AddBloodPressureDialog.newInstance(this, beaconID);
        addBloodDialog.show(getFragmentManager(), "TAG");
    }
}
