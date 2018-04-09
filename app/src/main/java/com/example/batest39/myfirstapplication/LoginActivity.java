package com.example.batest39.myfirstapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$";



    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private CheckBox mCheckBox;
    private View mProgressView;
    private View mLoginFormView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mCheckBox = (CheckBox) findViewById(R.id.registering_checkbox);
        context = getApplicationContext();

        getCheckboxListener(mSignInButton, mCheckBox);
        getLoginListener(mUsernameView, mPasswordView, mCheckBox, mSignInButton);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        int usernameLength = username.length();
        return usernameLength > 3;
    }

    private boolean isPasswordValid(String password) {
        boolean isValid = false;

        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = p.matcher(password);

        if (m.matches()){
/*            Toast toast = Toast.makeText(context,
                    "Valid password!", Toast.LENGTH_SHORT);
            toast.show();*/
            isValid = true;
        }

        return isValid;
    }

    public void getCheckboxListener(Button inButton, CheckBox inCheckBox){
        final Button loginButton = inButton;
        final CheckBox checkBox = inCheckBox;

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId() == checkBox.getId() && checkBox.isChecked()){
                    loginButton.setText("Register");
                } else {
                    loginButton.setText("Sign In");
                }
            }
        });
    }

    public void getLoginListener(AutoCompleteTextView inUsername, EditText inPassword,
                                 CheckBox inCheckBox, Button inButton){
        final AutoCompleteTextView username = inUsername;
        final EditText password = inPassword;
        final CheckBox checkBox = inCheckBox;
        final Button loginButton = inButton;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //logging in
                if(view.getId() == loginButton.getId() && !checkBox.isChecked()){
                    String usernameString = username.getText().toString();
                    String passwordString = password.getText().toString();
                    if(username.getText().toString().equals("Erik") &&
                       password.getText().toString().equals("Krohn1")){
                        //show MainActivity
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(context,
                                "Incorrect username/password", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                //registering
                } else {
                    if (!isUsernameValid(username.getText().toString())){
                        Toast toast = Toast.makeText(context,
                                "Username must be at least four characters long.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (!isPasswordValid(password.getText().toString())){
                        Toast toast = Toast.makeText(context,
                                "Password does not meet requirements.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        //show MainActivity
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

