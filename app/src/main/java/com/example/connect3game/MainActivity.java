package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    int [] gameState = {2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 };

    int[][] winningPosition = { {0 ,1 ,2} ,{3 ,4 ,5} ,{6 ,7 ,8} ,{0 ,3 ,6} ,{1 ,4 ,7} ,{2 ,5 ,8} ,{0 ,4 ,8} ,{2 ,4 ,6}};

    boolean gameActive = true;
    int count = 0;
    boolean draw = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedposition = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedposition] == 2 && gameActive) {
            gameState[tappedposition] = activePlayer;
            counter.setTranslationY(-1500);
            count++;
            Log.i("count Value", Integer.toString(count));
//            for(int i = 0; i < gameState.length  ; i++) {
//                if (gameState[i] == 2) {
//                    count = 1;
//                    break;
//                }
//                    count = 0;
//            }
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }

                    TextView textView = (TextView) findViewById(R.id.textView);
                    Button button = (Button) findViewById(R.id.button);
                    textView.setText(winner + " has Won");
                    button.setVisibility(view.VISIBLE);
                    textView.setVisibility(view.VISIBLE);
                    count = 0;
                }
            }
                if(count == 9){
                   TextView textView = (TextView) findViewById(R.id.textView);
                    Button button = (Button) findViewById(R.id.button);
                    textView.setText("  Match Draw  ");
                    button.setVisibility(view.VISIBLE);
                    textView.setVisibility(view.VISIBLE);
                    count = 0;
                }
        }
    }

    public void playAgain(View view){
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(view.INVISIBLE);
        textView.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2 ;

        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
