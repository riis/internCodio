package com.riis.cropcompare.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.riis.cropcompare.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends Activity {
    private Spinner stateSpinner;
    private EditText acreage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acreage = (EditText) findViewById(R.id.acreage);
        addStatesToSpinner();
    }

    public void onFabPressed(View view) {
        if (!Objects.equals(String.valueOf(stateSpinner.getSelectedItem()), "Select a state") &&
                acreage.getText().length() != 0 && Integer.parseInt(String.valueOf(acreage.getText())) != 0) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            intent.putExtra("state", String.valueOf(stateSpinner.getSelectedItem()));
            intent.putExtra("acreage", String.valueOf(acreage.getText()));

            startActivity(intent);
        } else {
            makeToast();
        }
    }

    private void makeToast() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter the required information!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void addStatesToSpinner() {
        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
        List<String> statesList = new ArrayList<>();
        statesList.add("Select a state");
        statesList.addAll(Arrays.asList(getResources().getStringArray(R.array.state_list)));

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(dataAdapter);
    }
}