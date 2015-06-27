package sithlords.com.healingbeacon.patients;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;

public class DashboardActivity extends ActionBarActivity {
    private PatientCard patientCard;

    private ImageView image;
    private TextView name;
    private TextView surname;
    private TextView age;
    private TextView weight;
    private TextView height;
    private TextView sex;

    private static final SimpleDateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        // Get patient's card with all data about his sicness
        patientCard = (PatientCard) getIntent().getExtras().get(PatientsInRange.PATIENT);

        // Find views
        image = (ImageView)findViewById(R.id.dashboard_patient_pic);
        name = (TextView)findViewById(R.id.dashboard_name);
        surname = (TextView)findViewById(R.id.dashboard_surname);
        age = (TextView)findViewById(R.id.dashboard_age);
        weight = (TextView)findViewById(R.id.dashboard_weight);
        height = (TextView)findViewById(R.id.dashboard_height);
        sex = (TextView)findViewById(R.id.dashboard_sex);

        // Set all textviews data
        setData();
    }

    private void setData() {
        Patient patient = patientCard.getPatient();
        // Set image
        new DownloadImageTask(image)
                .execute(patient.getPhotoUrl());

        // Set TVs
        name.setText(patient.getFirstName());
        surname.setText(patient.getLastName());

        // Convert dates from strings and calculate age
        Date currentDate = new Date();
        Date birthDate = null;
        try {
            birthDate = BIRTH_DATE_FORMAT.parse(patient.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        age.setText(currentDate.getYear() - birthDate.getYear() + "");
        weight.setText(Math.round(patient.getWeight()) + " kg");
        height.setText(Math.round(patient.getHeight()) + " cm");

        // Set full sex names
        if (patient.getGender().equals("f"))
            sex.setText("female");
        else {
            sex.setText("male");
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void history(View v) {
        Intent i = new Intent(this, PatientTemperatureActivity.class);
        i.putExtra(PatientsInRange.PATIENT, patientCard);
        startActivity(i);
    }
}
