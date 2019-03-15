package fr.android.calculator.calculator;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Calculator extends AppCompatActivity {

    //initialisation variables
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonP;
    Button buttonM;
    Button buttonF;
    Button buttonD;
    Button buttonE;

    TextView Text1;
    TextView Text2;
    String operation=" ";
    String result;
    private double op1;
    private double op2;
    private char operator1;
    //SecondActivity
    String operation1;
    public String resultat_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Text1 = (TextView) findViewById(R.id.Text1);
        Text2 = (TextView) findViewById(R.id.Text2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //affichage boutton java
        Button myButton= new Button(this);
        myButton.setText("=");
        LinearLayout ll = (LinearLayout) findViewById(R.id.LayoutTouch);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.addView(myButton, lp);


        View.OnClickListener buttonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //tp server
                Runnable runnable = () -> {
                    try{
                        Socket s = new Socket("10.0.2.2", 9879);
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                        //op2 prend la valeur de operation
                        op2 = Double.parseDouble(operation);
                        //envoie les valeurs au server
                        dos.writeDouble(op1);
                        dos.writeChar(operator1);
                        dos.writeDouble(op2);

                        //pour SecondActivity utilisé plus tard
                        operation1=String.valueOf(op1) + String.valueOf(operator1) + String.valueOf(op2);

                        //pour SecondActivity utilisé plus tard
                        Double res = dis.readDouble();
                        resultat_menu= String.valueOf(res);


                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Affichage resultat
                                Text2.setText(Double.toString(res));
                            }
                        });

                        //si exception dans le try
                    } catch (IOException e){
                        finish();
                        e.printStackTrace();
                    }
                };
                new Thread(runnable).start();
            }
        };
        myButton.setOnClickListener(buttonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }



    //methode d'affichage
    public void Display(){
        Text1.setText(operation);
        //Text2.setText(result);
        //result=" ";
    }

    public void ClickButton(View view) {
        //switch qui s'occupe des buttons
        switch (view.getId()) {
            case R.id.button0:
                //id button
                button0 = (Button) findViewById(R.id.button0);
                //operation prend la valeur du button en plus
                operation += ((Button)view).getText();
                break;
            case R.id.button1:
                button1 = (Button) findViewById(R.id.button1);
                operation += button1.getText();
                break;
            case R.id.button2:
                button2 = (Button) findViewById(R.id.button2);
                operation += button2.getText();
                break;
            case R.id.button3:
                button3 = (Button) findViewById(R.id.button3);
                operation += button3.getText();
                break;
            case R.id.button4:
                button4 = (Button) findViewById(R.id.button4);
                operation += button4.getText();
                break;
            case R.id.button5:
                button5 = (Button) findViewById(R.id.button5);
                operation += button5.getText();
                break;
            case R.id.button6:
                button6 = (Button) findViewById(R.id.button6);
                operation += button6.getText();
                break;
            case R.id.button7:
                button7 = (Button) findViewById(R.id.button7);
                operation += button7.getText();
                break;
            case R.id.button8:
                button8 = (Button) findViewById(R.id.button8);
                operation += button8.getText();
                break;
            case R.id.button9:
                button9 = (Button) findViewById(R.id.button9);
                operation += button9.getText();
                break;

            case R.id.buttonP:
                buttonP = (Button) findViewById(R.id.buttonP);
                operator1='+' ;
                op1 = Double.parseDouble(operation);
                //prend la valeur 0 pour que op2
                operation = String.valueOf(0);
                break;

            case R.id.buttonM:
                buttonM = (Button) findViewById(R.id.buttonM);
                operator1='-' ;
                op1 = Double.parseDouble(operation);
                operation = String.valueOf(0);
                break;

            case R.id.buttonF:
                buttonF = (Button) findViewById(R.id.buttonF);
                operator1= '*';
                op1 = Double.parseDouble(operation);
                operation = String.valueOf(0);
                break;

            case R.id.buttonD:
                buttonD = (Button) findViewById(R.id.buttonD);
                operator1 = '/';
                op1 = Double.parseDouble(operation);
                operation = String.valueOf(0);
                break;

            /*case R.id.buttonE:
                buttonE= (Button) findViewById(R.id.buttonE);
                op2 = Double.parseDouble(operation);
                //compute();


            Runnable runnable = () -> {
                try{
                    Socket s = new Socket("10.0.2.2", 9879);
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                    dos.writeDouble(op1);
                    dos.writeChar(operator1);
                    dos.writeDouble(op2);

                    operation1=String.valueOf(op1) + String.valueOf(operator1) + String.valueOf(op2);

                    Double res = dis.readDouble();
                    resultat_menu= String.valueOf(res);


                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            // Stuff that updates the UI
                            Text2.setText(Double.toString(res));
                        }
                    });

                } catch (IOException e){
                    finish();
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();

                break;*/




        }
        Display();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.action_settings == item.getItemId()){

            //intent = flux qui permet de passer une info d'une act à une autre
            Intent intent = new Intent(this, SecondActivity.class);
            //envoie les valeurs du resultat et de l'operation
            intent.putExtra("EXTRA_RESULTAT", resultat_menu);
            intent.putExtra("EXTRA_OPERATION", operation1);

            startActivity(intent);

            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /*public void compute() {
        switch(operator1) {
            case '+' : op1 = op1 + op2; break;
            case '-' : op1 = op1 - op2; break;
            case '*': op1 = op1 * op2; break;
            case '/':  op1 = op1 / op2; break;
            default : return; // do nothing if no operator
        }
        result = Double.toString(op1);
        op1 = 0;
        op2 = 0;
        operator1=0;

    }*/



}