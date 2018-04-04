package com.example.aimanrahmat.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etWeight, etHeight;
    private TextView tvDisplayResult,tvResultStatus,textDate;
    Button bSave,bRead,bDate;
    String data,date;
    String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etWeight = (EditText) findViewById(R.id.weight);
        etHeight = (EditText) findViewById(R.id.height);
        tvDisplayResult = (TextView) findViewById(R.id.display_result);
        tvResultStatus = (TextView) findViewById(R.id.result_status);
        textDate = (TextView) findViewById(R.id.displayDate);
        bSave =(Button)findViewById(R.id.savebutton);
        bRead =(Button)findViewById(R.id.readbutton);
        bDate =(Button)findViewById(R.id.dateButton);

        final DBHandler db = new DBHandler(this);

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=tvDisplayResult.getText().toString();  //+ "\n" if using file
                date=textDate.getText().toString();
                try {
                    //FileOutputStream fOut = openFileOutput(file, Context.MODE_APPEND);
                    //fOut.write(data.getBytes());
                    //fOut.close();

                    db.addBmi(new Record(1, data, date));

                    Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        //Read
        bRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, RecordActivity.class));
                finish();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_calculate:
                calculate();
                Log.d("check","Button Clicked!");
                break;
            case R.id.button_reset:
                reset();
                break;
        }
    }

    private void calculate() {
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();
        double bmiResult = Double.parseDouble(weight)/ (Double.parseDouble(height) * Double.parseDouble(height));

        DecimalFormat df = new DecimalFormat("####0.00");
        tvDisplayResult.setText(String.valueOf(df.format(bmiResult)));

        if (bmiResult < 18.5) {
            tvResultStatus.setText(R.string.under);
            tvResultStatus.setBackgroundColor(Color.parseColor("#F1C40F"));
            tvResultStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }else if (bmiResult < 25){
            tvResultStatus.setText(R.string.normal);
            tvResultStatus.setBackgroundColor(Color.parseColor("#2ECC71"));
            tvResultStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }else if(bmiResult < 30){
            tvResultStatus.setText(R.string.over);
            tvResultStatus.setBackgroundColor(Color.parseColor("#E57E22"));
            tvResultStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }else {
            tvResultStatus.setText(R.string.obes);
            tvResultStatus.setBackgroundColor(Color.parseColor("#C0392B"));
            tvResultStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    private void reset(){
        etWeight.setText("");
        etHeight.setText("");
        tvDisplayResult.setText(R.string.default_result);
        tvResultStatus.setText(R.string.na);
        tvResultStatus.setBackgroundColor(0);
        tvResultStatus.setTextColor(0);
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
                startActivity(new Intent(CalculatorActivity.this, CalculatorActivity.class));
                finish();
                break;
            case R.id.menu_info:
                startActivity(new Intent(CalculatorActivity.this, InformationActivity.class));
                break;
            case R.id.menu_record:
                startActivity(new Intent(CalculatorActivity.this, RecordActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
