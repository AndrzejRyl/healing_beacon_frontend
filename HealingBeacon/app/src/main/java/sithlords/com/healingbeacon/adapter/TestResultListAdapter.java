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
import sithlords.com.healingbeacon.model.Test;

public class TestResultListAdapter extends ArrayAdapter<Test> {
    public TestResultListAdapter(Context context, List<Test> objects) {
        super(context, R.layout.test_result_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Test testResult = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prescribed_drug_item, parent, false);
        }

        final TextView testNameField = (TextView) convertView.findViewById(R.id.testType);
        final TextView testTimeTakenField = (TextView) convertView.findViewById(R.id.testTimeTaken);
        final TextView testResultField = (TextView) convertView.findViewById(R.id.testResult);

        testNameField.setText(testResult.getTestType());
        testTimeTakenField.setText(testTimeString(testResult));
        testResultField.setText(testResult.getResult());

        return convertView;
    }

    private String testTimeString(Test testResult) {
        return testResult.getTakenTime();
    }
}
