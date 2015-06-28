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

import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;
import sithlords.com.healingbeacon.patients.OnDialogFinished;
import sithlords.com.healingbeacon.patients.PatientTemperatureActivity;
import sithlords.com.healingbeacon.rest.PatientCardResponseListener;
import sithlords.com.healingbeacon.service.ExternalServiceImpl;

/**
 * @author FleenMobile at 2015-06-28
 */
public class AddTemperatureDialog extends DialogFragment implements PatientCardResponseListener {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private static PatientTemperatureActivity mActivity;
    private static int mBeaconID;
    private TemperatureMeasurement result;

    private View view;
    private EditText tempTV;
    private TextView errorTV;

    private OnDialogFinished callback;

    public static AddTemperatureDialog newInstance(PatientTemperatureActivity instance,
                                                   int beaconID, OnDialogFinished callback) {

        // Get a holder to host activity
        mActivity = instance;
        mBeaconID = beaconID;

        // Start a dialog
        AddTemperatureDialog dialog = new AddTemperatureDialog();
        dialog.setCallback(callback);

        return dialog;
    }

    public void setCallback(OnDialogFinished callback) {
        this.callback = callback;
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
                R.layout.add_temperature_dialog, null));

        // Find views
        tempTV = (EditText) view
                .findViewById(R.id.dialog_temp);
        errorTV = (TextView)view.findViewById(R.id.dialog_error);

        return builder.create();
    }

    private void displayError() {
        // User has fucked up with date so we have to tell him
        errorTV.setText("Date is in the wrong format. Should be yyyy-MM-dd'T'HH:mm:ss.SSS'Z'. Good luck ;)");
    }

    private boolean acceptChanges() {
        String temp = tempTV.getText().toString();
        Date date = new Date();


        // Save it in DB
        ExternalServiceImpl API = new ExternalServiceImpl(this);
        TemperatureMeasurement measurement = new TemperatureMeasurement();
        measurement.setDegreeCelcius(Double.parseDouble(temp));
        measurement.setMeasurementTime(date);

        callback.onFinished(measurement);

        return true;
    }

    @Override
    public void onPatientResponse(PatientCard patient) {
        Log.e("Zonk", "successfull");
    }
}
