package com.example.aimanrahmat.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tvStored;
    private Spinner spinner;
    Button btnDelete;

    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tvStored= findViewById(R.id.displaysaveditem);
        spinner = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.delete);

        spinner.setOnItemSelectedListener(this);

        List<String> month = new ArrayList<String>();
        month.add("All");
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteAllBmi();
                    Toast.makeText(getBaseContext(),"All data deleted",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RecordActivity.this, RecordActivity.class));
                    finish();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(RecordActivity.this, CalculatorActivity.class));
                finish();
                break;
            case R.id.menu_info:
                startActivity(new Intent(RecordActivity.this, InformationActivity.class));
                finish();
                break;
            case R.id.menu_record:
                startActivity(new Intent(RecordActivity.this, RecordActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        } return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        List<Record> records = db.getAllRecords();

        StringBuilder builder = new StringBuilder();
        for (Record record : records) {
            switch (position){
                case 0:
                    builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    break;
                case 1:
                    if(record.getDate().contains("/1/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 2:
                    if(record.getDate().contains("/2/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 3:
                    if(record.getDate().contains("/3/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 4:
                    if(record.getDate().contains("/4/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 5:
                    if(record.getDate().contains("/5/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 6:
                    if(record.getDate().contains("/6/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 7:
                    if(record.getDate().contains("/7/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 8:
                    if(record.getDate().contains("/8/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 9:
                    if(record.getDate().contains("/9/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 10:
                    if(record.getDate().contains("/10/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 11:
                    if(record.getDate().contains("/11/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
                case 12:
                    if(record.getDate().contains("/12/")){
                        builder.append("Id: " + record.getId() + " , BMI: " + record.getBmi()+ " , Date: " + record.getDate()+"\n");
                    }
                    break;
            }
        }
        tvStored.setText(builder);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        tvStored.setText("");
    }
}