package sithlords.com.healingbeacon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import sithlords.com.healingbeacon.adapter.SimpleListAdapter;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.patients.PatientTemperatureActivity;
import sithlords.com.healingbeacon.patients.PatientsInRange;

public class MedicalResearchActivity extends Activity {
    private static final List<String> menuItems = Arrays.asList("Temperature",
            "Blood Pressure", "Laboratory Tests");

    private PatientCard patientCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patients_in_range);

        setContentView(R.layout.activity_medical_research);

        patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);

        final SimpleListAdapter adapter = new SimpleListAdapter(this, menuItems);

        final ListView optionsList = (ListView) findViewById(R.id.medicalResearchList);
        optionsList.setAdapter(adapter);
        optionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startTemperatureActivity();
                        break;
                    case 1:
                        startBloodPressureActivity();
                        break;
                    case 2:
                        startLaboratoryTestsActivity();
                        break;
                }
            }
        });
    }

    private void startTemperatureActivity() {
        final Intent intent = new Intent(this, PatientTemperatureActivity.class);
        intent.putExtra(PatientsInRange.PATIENT, patientCard);
        MedicalResearchActivity.this.startActivity(intent);
    }

    private void startBloodPressureActivity() {
        final Intent intent = new Intent(this, PatientTemperatureActivity.class);
        intent.putExtra(PatientsInRange.PATIENT, patientCard);
        startActivity(intent);
    }

    private void startLaboratoryTestsActivity() {
        final Intent intent = new Intent(this, LaboratoryTestsActivity.class);
        intent.putExtra(PatientsInRange.PATIENT, patientCard);
        startActivity(intent);
    }
}
