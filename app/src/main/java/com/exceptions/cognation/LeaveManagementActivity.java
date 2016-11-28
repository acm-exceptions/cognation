package com.exceptions.cognation;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class LeaveManagementActivity extends AppCompatActivity {

    TextView tvFrom, tvTo;
    Spinner spinner;

    final String[] MONTH = {"January","February","March","April","May","June","July","August","September",
    "October", "November", "December"};

    final String[] REASONS = {"Maternity Leave", "Paternity Leave", "Sick Leave", "Vacation Leave"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvFrom = (TextView)findViewById(R.id.tvFrom);
        tvTo = (TextView)findViewById(R.id.tvTo);
        spinner =(Spinner)findViewById(R.id.spinner);

        Initialize();
    }

    public void OpenDatePicker(View view) {
        new DatePickerDialog(LeaveManagementActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvFrom.setText("FROM: " + MONTH[monthOfYear] + " " + dayOfMonth + ", " + year);
            }
        }, 2016, 10, 27).show();
    }

    public void OpenDatePicker2(View view) {
        new DatePickerDialog(LeaveManagementActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvTo.setText("TO: " + MONTH[monthOfYear] + " " + dayOfMonth + ", " + year);
            }
        }, 2016, 10, 27).show();
    }

    void Initialize() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(LeaveManagementActivity.this, android.R.layout.simple_spinner_item, REASONS);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
