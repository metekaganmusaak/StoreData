package com.mkmsoft.storedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // We need constraint layout for showing snackbar
    ConstraintLayout constraintLayout;
    // We will store our data in sharedpreferences
    SharedPreferences sharedPreferences;
    TextView usersTextField;
    // We want to close keyboard when we clicked the button to see snackbars properly
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.main_layout);
        usersTextField = findViewById(R.id.usersAge);
        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        sharedPreferences = this.getSharedPreferences("com.mkmsoft.storedata", Context.MODE_PRIVATE);
    }



    public void saveAge(View view){
        String usersAge = usersTextField.getText().toString();

        if(usersAge.isEmpty())
        {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide keyboard
            showSnackBar(view, "Age can not be empty!"); // show snackbar
            usersTextField.getEditableText().clear(); // clear textfield

        }
        else if(Integer.parseInt(usersAge) < 0){
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            showSnackBar(view, "Age can not be negative number!");
            usersTextField.getEditableText().clear();
        }
        else{
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            sharedPreferences.edit().putInt("age", Integer.parseInt(usersAge)).apply(); // save age to sharedpreferences
            showSnackBar(view, "Your age saved successfully.");
            usersTextField.getEditableText().clear();


        }
    }

    public void showAge(View view){
        int savedAge = sharedPreferences.getInt("age", 0); // if we dont have value, assign a default number (0)
        showSnackBar(view, Integer.toString(savedAge));
    }

    public void showSnackBar(View v, String message){
        Snackbar snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}