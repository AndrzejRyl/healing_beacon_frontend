package sithlords.com.healingbeacon.patients;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.rest.PatientResponseListener;
import sithlords.com.healingbeacon.service.ExternalServiceImpl;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author FleenMobile at 2015-06-27
 */
public class PatientListAdapter extends ArrayAdapter implements PatientResponseListener {

    private List<Patient> data;
    private Context context;
    private int layoutResourceId;

    public PatientListAdapter(Context context, int resource, List<Patient> patients) {
        super(context, resource, patients);

        this.data = newArrayList();
        this.data.addAll(patients);
        this.context = context;
        this.layoutResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PatientHolder holder = null;

        // Get a holder (existing or a new one)
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PatientHolder();
            holder.patientName = (TextView) row.findViewById(R.id.patient_list_item_name);
            holder.patientSurname = (TextView) row
                    .findViewById(R.id.patient_list_item_surname);
            row.setTag(holder);
        } else {
            holder = (PatientHolder) row.getTag();
        }

        // Set holder according to patient data
        final Patient patient = data.get(position);

        // Set data
        holder.patientName.setText(patient.getFirst_name());
        holder.patientSurname.setText(patient.getLast_name());

        // Allow user to click on whole row so as to open specific data about the patient
        final View finalRow = row;
        row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startPatientDashboard(patient.getId());
            }
        });

        return row;
    }

    private void startPatientDashboard(long id) {
        ExternalServiceImpl API = new ExternalServiceImpl(this);

        // Subscribe for patient
        API.getPatientCard(id);
    }

    @Override
    public void onPatientResponse(PatientCard patientCard) {
        Intent i  = new Intent(context, DashboardActivity.class);
        i.putExtra(PatientsInRange.PATIENT, patientCard);
        context.startActivity(i);
    }

    static class PatientHolder {
        TextView patientName;
        TextView patientSurname;
    }

    @Override
    public void clear() {
        super.clear();
        this.data.clear();
    }

    @Override
    public void add(Object object) {
        super.add(object);
        this.data.add((Patient) object);
    }

    @Override
    public int getCount() {
        return data != null? data.size() : 0;
    }



}
