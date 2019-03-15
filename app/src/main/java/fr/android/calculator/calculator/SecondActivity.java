package fr.android.calculator.calculator;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    //initialisation variables
    private TextView Text_Resultat, Text_Operation;
    private EditText Text_URL;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//run code addition to the existing code
        setContentView(R.layout.secondactivity);//design xml

        Intent intent = getIntent();
        String result = intent.getStringExtra("EXTRA_RESULTAT");
        String op = intent.getStringExtra("EXTRA_OPERATION");

        //recup id
        Text_Resultat = (TextView) findViewById(R.id.TextViewRes);
        Text_Operation = (TextView) findViewById(R.id.TextViewOpe);
        //affiche les données recupérées de Calculator
        Text_Resultat.setText(result);
        Text_Operation.setText(op);


        //recup id
        Text_URL = (EditText) findViewById(R.id.EditTextUrl);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //répond à l'item du menu selectionné
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, Calculator.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Text URL
    public void buttonHandler (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Text_URL.getText().toString()));
        startActivity(browserIntent);
    }
}