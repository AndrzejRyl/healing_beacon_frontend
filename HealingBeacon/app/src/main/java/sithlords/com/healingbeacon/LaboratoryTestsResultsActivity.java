package sithlords.com.healingbeacon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import sithlords.com.healingbeacon.adapter.TestResultListAdapter;
import sithlords.com.healingbeacon.model.GroupedTests;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.Test;
import sithlords.com.healingbeacon.patients.PatientsInRange;


public class LaboratoryTestsResultsActivity extends ActionBarActivity {

    TextView titleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_laboratory_tests);

        final PatientCard patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);
        final GroupedTests groupedTests = new GroupedTests(patientCard.getTests());
        final String selectedTestsType = (String) getIntent().getExtras().get(LaboratoryTestsActivity.SELECTED_TEST_TYPE);

        // Set specific title
        titleTV = (TextView)findViewById(R.id.laboratoryTestsListTextView);
        titleTV.setText(selectedTestsType.toUpperCase() + " TESTS");

        final List<Test> testResults = groupedTests.getTestsForType(selectedTestsType);

        final ListView listView = (ListView) findViewById(R.id.laboratoryTestsList);
        final TestResultListAdapter adapter = new TestResultListAdapter(this, testResults);
        listView.setAdapter(adapter);
    }
}
