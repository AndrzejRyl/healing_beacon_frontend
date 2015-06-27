package sithlords.com.healingbeacon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.PrescribedDrug;

public class TestResultListAdapter extends ArrayAdapter<PrescribedDrug> {
    public TestResultListAdapter(Context context, List<PrescribedDrug> objects) {
        super(context, R.layout.prescribed_drug_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PrescribedDrug drug = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prescribed_drug_item, parent, false);
        }

        final TextView drugNameField = (TextView) convertView.findViewById(R.id.prescribedDrugName);
        final TextView intervalHoursField = (TextView) convertView.findViewById(R.id.intervalHours);
        final TextView doseMilligramsField = (TextView) convertView.findViewById(R.id.doseMilligrams);

        drugNameField.setText(drug.getDrugName());
        intervalHoursField.setText(intervalString(drug));
        doseMilligramsField.setText(doseString(drug));

        return convertView;
    }


    private String intervalString(PrescribedDrug drug) {
        return "Every " + drug.getIntervalHours() + " hours";
    }

    private String doseString(PrescribedDrug drug) {
        return "" + drug.getDoseMilligrams() + "mg";
    }
}
