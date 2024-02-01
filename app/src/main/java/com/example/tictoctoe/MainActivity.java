package com.example.tictoctoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn=true;
    private int roundcount;

    private int player1points;
    private int player2points;

    private TextView textViewplayer1;
    private  TextView textViewplayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewplayer1=(TextView) findViewById(R.id.text_p_1);
        textViewplayer2=(TextView) findViewById(R.id.text_p_2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){

                String buttonID="button_"+i+j;
                int resId= getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);

            }
        }
    }

    @Override
    public void onClick(View v) {
         if(!((Button)v).getText().toString().equals("")){

             if(player1Turn){
                 ((Button)v).setText("X");
             }else {
                 ((Button)v).setText("O");
             }

             roundcount++;

             if (checkforwin()){
                 if(player1Turn){
                     player1Win();
                 }else {
                     player2Win();
                 }
             }
             else if (roundcount == 9){
                 draw();
             }else {
                 player1Turn=!player1Turn;
             }
         }
    }

    private boolean checkforwin() {
        String[][] field= new String[3][3];

        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        //row
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        //coulam
        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        //Diagonal
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1Win(){
        player1points++;
        Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show();
        updatePointstext();
        rebord();
    }
    private void player2Win(){
        player2points++;
        Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show();
        updatePointstext();
        rebord();
    }
    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        rebord();
    }

    private void updatePointstext(){
        textViewplayer1.setText("player1: "+ player1points);
        textViewplayer2.setText("player2: "+ player2points);
    }
    private void rebord(){
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                buttons[i][j].setText("");
            }
        }

        roundcount=0;
        player1Turn=true;

    }
}
