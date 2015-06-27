package sithlords.com.healingbeacon;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import sithlords.com.healingbeacon.adapter.SimpleListAdapter;
import sithlords.com.healingbeacon.model.GroupedTests;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.patients.PatientsInRange;


public class LaboratoryTestsActivity extends ActionBarActivity {
    public static final String SELECTED_TEST_TYPE = "TestResultList";

    private PatientCard patientCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory_tests);

        patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);
        final GroupedTests groupedTests = new GroupedTests(patientCard.getTests());

        final List<String> testTypes = groupedTests.getTestTypes();
        final SimpleListAdapter adapter = new SimpleListAdapter(this, testTypes);

        final ListView optionsList = (ListView) findViewById(R.id.laboratoryTestsList);
        optionsList.setAdapter(adapter);
        optionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(LaboratoryTestsActivity.this, LaboratoryTestsResultsActivity.class);
                intent.putExtra(PatientsInRange.PATIENT, patientCard);
                intent.putExtra(SELECTED_TEST_TYPE, testTypes.get(position));
                LaboratoryTestsActivity.this.startActivity(intent);
            }
        });
    }
}
