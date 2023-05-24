package com.example.simplegame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private int oportunity = 0;
    private TextView tvPlayerOne;
    private TextView tvPlayerTwo;
    private TextView tvResult;
    private Button btnRan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate components
        tvPlayerOne = findViewById(R.id.tvPOne);
        tvPlayerTwo = findViewById(R.id.tvPTwo);
        tvResult = findViewById(R.id.tvResult);
        btnRan = findViewById(R.id.btnRandom);

        // Adding functionality
        this.setButtonsTask();

    }

    private void setButtonsTask(){
        findViewById(R.id.btnRandom).setOnClickListener(view -> {
            Random rnd = new Random();
            int random = rnd.nextInt(10);

            if (this.setRandom(random)){ // Set Result
                int rndOne = Integer.parseInt((String) this.tvPlayerOne.getText());
                int rndTwo = Integer.parseInt((String) this.tvPlayerTwo.getText());

                if (rndOne == rndTwo){
                    this.tvResult.setText("No winner");
                }

                else if (rndOne > rndTwo) this.tvResult.setText("Winner Player one");

                else tvResult.setText("Winner Player Two");
            }


        });


        findViewById(R.id.btnReset).setOnClickListener(view -> {

            this.btnRan.setText("Player One");
            this.tvPlayerOne.setText("Player One");
            this.tvPlayerTwo.setText("Player Two");
            this.tvResult.setText("Result:");

        });
    }


    private boolean setRandom(int random){

        if (oportunity == 0) {// Set to player one
            this.btnRan.setText("Player Two");
            this.tvPlayerOne.setText(String.valueOf(random));
            this.oportunity++;
            return false;
        }

        else{
            this.tvPlayerTwo.setText(String.valueOf(random));
            this.oportunity = 0;
            return true;
        }

    }



}