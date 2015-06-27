package sithlords.com.healingbeacon;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import sithlords.com.healingbeacon.model.GroupedTests;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.Test;
import sithlords.com.healingbeacon.patients.PatientsInRange;


public class LaboratoryTestsResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory_tests);

        final PatientCard patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);
        final GroupedTests groupedTests = new GroupedTests(patientCard.getTests());
        final String selectedTestsType = (String) getIntent().getExtras().get(LaboratoryTestsActivity.SELECTED_TEST_TYPE);

        final List<Test> testResults = groupedTests.getTestsForType(selectedTestsType);

        findViewById(R.id.test)
    }
}
