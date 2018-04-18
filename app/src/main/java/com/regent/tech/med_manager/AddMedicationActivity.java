package com.regent.tech.med_manager;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.regent.tech.med_manager.Data.MedicineContract;
import com.regent.tech.med_manager.Data.MedicineHelperClass;
import com.regent.tech.med_manager.Utils.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddMedicationActivity extends AppCompatActivity implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener{

    public static final String TAG = AddMedicationActivity.class.getSimpleName();

    EditText nameInput;
    EditText descriptionInput;
    EditText intervalInput;
    Button startDateInput;
    Button endDateInput;
    Button saveMedicine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        nameInput = findViewById(R.id.input_name_of_medicine);
        descriptionInput = findViewById(R.id.input_description);
        intervalInput = findViewById(R.id.input_frequency);
        startDateInput = findViewById(R.id.input_start_date);
        endDateInput = findViewById(R.id.input_end_date);
        saveMedicine = findViewById(R.id.add);

        startDateInput.setOnClickListener(this);
        endDateInput.setOnClickListener(this);
        saveMedicine.setOnClickListener(this);

    }

    public void setDate(){
        DatePicker datePicker = new DatePicker();
        datePicker.show(getFragmentManager(), TAG);
    }

    public void insertMedicine(){
        String medicineName = nameInput.getText().toString().trim();
        String medicineDescription = descriptionInput.getText().toString().trim();
        String medInterval = intervalInput.getText().toString().trim();
        int medicineInterval = Integer.parseInt(medInterval);
        String startDate = startDateInput.getText().toString().trim();
        String endDate = endDateInput.getText().toString().trim();

        // Create database helper
        MedicineHelperClass medicineHelperClass = new MedicineHelperClass(this);

        // Get the database in writing mode
        SQLiteDatabase database = medicineHelperClass.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and medicine attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME, medicineName);
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_DECRIPTION, medicineDescription);
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_INTERVAL, medicineInterval);
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_START_DATE, startDate);
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_END_DATE, endDate);

        // Insert a new row
        long newRowId = database.insert(MedicineContract.MedicineEntry.TABLE_NAME, null,
                values);
        if (newRowId == -1){
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Successfully inserted row
            Toast.makeText(this, "Medication added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.input_start_date:
                setDate();
                break;
            case R.id.input_end_date:
                setDate();
                break;
            case R.id.add:
                insertMedicine();
                break;
        }

    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day_of_month) {
        Calendar calendar = new GregorianCalendar(year, month, day_of_month);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.YEAR_FIELD);
        String cal = dateFormat.format(calendar.getTime());
    }

    private String setDate(Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.YEAR_FIELD);
        return dateFormat.format(calendar.getTime());
    }
}
