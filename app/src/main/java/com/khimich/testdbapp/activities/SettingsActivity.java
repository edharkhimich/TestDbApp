package com.khimich.testdbapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khimich.testdbapp.R;
import com.khimich.testdbapp.utils.Constants;


public class SettingsActivity extends AppCompatActivity {

    private EditText userLimit;
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        userLimit = (EditText) findViewById(R.id.limitEt);
        saveBtn = (Button) findViewById(R.id.savingBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int parseLimit = Integer.parseInt(userLimit.getText().toString());
                if (userLimit.getText().length() > 4) {
                    Toast.makeText(getApplicationContext(), getString(R.string.too_much_symbols), Toast.LENGTH_LONG).show();
                    userLimit.setText("");
                } else if (parseLimit < 20 || parseLimit > 1000) {
                    Toast.makeText(getApplicationContext(), R.string.limit_cant_be, Toast.LENGTH_SHORT).show();
                    userLimit.setText("");
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(Constants.KEY, parseLimit);
                    intent.setAction(Constants.SETTINGS_ACTION);
                    startActivity(intent);
                }
            }
        });
    }
}
