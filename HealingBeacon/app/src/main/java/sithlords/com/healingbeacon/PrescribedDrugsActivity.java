package sithlords.com.healingbeacon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.List;

import sithlords.com.healingbeacon.adapter.PrescribedDrugListAdapter;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.PrescribedDrug;
import sithlords.com.healingbeacon.patients.PatientsInRange;


public class PrescribedDrugsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescribed_drugs);

        final PatientCard patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);
        final List<PrescribedDrug> prescribedDrugs = patientCard.getPrescribedDrugs();

        final PrescribedDrugListAdapter adapter = new PrescribedDrugListAdapter(this, prescribedDrugs);

        final ListView drugList = (ListView)findViewById(R.id.prescribedDrugsList);
        drugList.setAdapter(adapter);
    }
}
