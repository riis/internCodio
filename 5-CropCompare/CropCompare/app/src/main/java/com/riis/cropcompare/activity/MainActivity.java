package com.riis.cropcompare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.riis.cropcompare.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends Activity
{
    private EditText mAcreage;
    private Spinner mStateSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fabButton = (Button) findViewById(R.id.fab_button);
        mAcreage = (EditText) findViewById(R.id.acreage);
        addStatesToSpinner();

        View.OnClickListener cropAndStateSelectedListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!Objects.equals(String.valueOf(mStateSpinner.getSelectedItem()), "Select a state")
                        && mAcreage.getText().length() != 0
                        && Integer.parseInt(String.valueOf(mAcreage.getText())) != 0)
                {
                    Intent intent = new Intent(MainActivity.this, CropSelectActivity.class);
                    intent.putExtra("state", String.valueOf(mStateSpinner.getSelectedItem()));
                    intent.putExtra("acreage", String.valueOf(mAcreage.getText()));

                    startActivity(intent);
                }
                else
                {
                    makeToast();
                }
            }
        };

        fabButton.setOnClickListener(cropAndStateSelectedListener);
    }

    private void makeToast()
    {
        CharSequence text = "Please enter the required information!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    private void addStatesToSpinner()
    {
        mStateSpinner = (Spinner) findViewById(R.id.stateSpinner);
        List<String> statesList = new ArrayList<>();
        statesList.add("Select a state");
        statesList.addAll(Arrays.asList(getResources().getStringArray(R.array.state_list)));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStateSpinner.setAdapter(dataAdapter);
    }
}