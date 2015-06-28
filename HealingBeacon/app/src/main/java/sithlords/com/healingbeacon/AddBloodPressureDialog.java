package sithlords.com.healingbeacon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sithlords.com.healingbeacon.model.BloodMeasurement;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.patients.BloodPressureActivity;
import sithlords.com.healingbeacon.patients.OnDialogFinished;
import sithlords.com.healingbeacon.rest.PatientCardResponseListener;
import sithlords.com.healingbeacon.service.ExternalServiceImpl;

/**
 * @author FleenMobile at 2015-06-28
 */
public class AddBloodPressureDialog extends DialogFragment implements PatientCardResponseListener {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private static BloodPressureActivity mActivity;
    private static int mBeaconID;

    private View view;
    private EditText diastoleTV;
    private EditText systoleTV;
    private TextView errorTV;

    private OnDialogFinished callback;

    public void setCallback(OnDialogFinished callback) {
        this.callback = callback;
    }

    public static AddBloodPressureDialog newInstance(BloodPressureActivity instance,
                                                       int beaconID, OnDialogFinished callback) {

        // Get a holder to host activity
        mActivity = instance;
        mBeaconID = beaconID;

        // Start a dialog
        AddBloodPressureDialog dialog = new AddBloodPressureDialog();
        dialog.setCallback(callback);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // Save new shit in DB
                                if (acceptChanges())
                                    dismiss();
                                else {
                                    displayError();
                                }
                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dismiss();
                            }
                        });

        builder.setView(view = getActivity().getLayoutInflater().inflate(
                R.layout.add_blood_dialog, null));

        // Find views
        diastoleTV = (EditText) view
                .findViewById(R.id.dialog_diastole);
        systoleTV = (EditText) view
                .findViewById(R.id.dialog_systole);
        errorTV = (TextView)view.findViewById(R.id.dialog_error);

        return builder.create();
    }

    private void displayError() {
        // User has fucked up with date so we have to tell him
        errorTV.setText("Date is in the wrong format. Should be yyyy-MM-dd");
    }

    private boolean acceptChanges() {
        String diastole = diastoleTV.getText().toString();
        String systole = systoleTV.getText().toString();
        Date date = new Date();


        // Save it in DB
        ExternalServiceImpl API = new ExternalServiceImpl(this);
        BloodMeasurement measurement = new BloodMeasurement();
        measurement.setDiastole(Integer.parseInt(diastole));
        measurement.setSystole(Integer.parseInt(systole));
        measurement.setMeasurementTime(date);
        callback.onFinished(measurement);
        return true;
    }

    @Override
    public void onPatientResponse(PatientCard patient) {
        Log.e("Zonk", "successfull");
    }
}
