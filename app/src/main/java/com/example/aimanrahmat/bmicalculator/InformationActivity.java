package com.example.aimanrahmat.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
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
                startActivity(new Intent(InformationActivity.this, CalculatorActivity.class));
                finish();
                break;
            case R.id.menu_info:
                startActivity(new Intent(InformationActivity.this, InformationActivity.class));
                finish();
                break;
            case R.id.menu_record:
                startActivity(new Intent(InformationActivity.this, RecordActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
