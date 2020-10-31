package com.example.oneortwoanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

// enum is used for we can set own data type.
    enum Player{

        ONE,TWO,No

    }

    Player currenPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowsCols = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private boolean gameOver = false;

    Button button;
    GridLayout gridLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices[0] = Player.No;
        playerChoices[1] = Player.No;
        playerChoices[2] = Player.No;
        playerChoices[3] = Player.No;
        playerChoices[4] = Player.No;
        playerChoices[5] = Player.No;
        playerChoices[6] = Player.No;
        playerChoices[7] = Player.No;
        playerChoices[8] = Player.No;

        button = findViewById(R.id.button);
        gridLayout = findViewById(R.id.gridLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtonData();
            }
        });

    }

    public void setClicked(View view) {

        ImageView img = (ImageView) view;
        int tagData = Integer.parseInt(img.getTag().toString());

        if (playerChoices[tagData] == Player.No && gameOver == false)
        {

            img.setTranslationX(-2000);


            playerChoices[tagData]=currenPlayer;


            if (currenPlayer == Player.ONE) {
                img.setImageResource(R.drawable.img1);

                currenPlayer=Player.TWO;   //one time img1 selected then after turns of img2
            } else if (currenPlayer == Player.TWO) {
                img.setImageResource(R.drawable.img2);

                currenPlayer=Player.ONE;
            }

            img.animate().translationXBy(2000).rotation(3600).setDuration(1000).alpha(1);

            Toast.makeText(this, img.getTag().toString(), Toast.LENGTH_SHORT).show();


            String toastForWin="";

            for (int[] winnerData : winnerRowsCols) {
                if (playerChoices[winnerData[0]] == playerChoices[winnerData[1]]
                        && playerChoices[winnerData[1]] == playerChoices[winnerData[2]]
                        && playerChoices[winnerData[0]] != Player.No) {

                    gameOver=true;
                    button.setVisibility(View.VISIBLE);

                    if (currenPlayer == Player.ONE) {
                        toastForWin="TWO";
//                    Toast.makeText(this, "Player Two Win...", Toast.LENGTH_SHORT).show();
                    } else if (currenPlayer == Player.TWO) {
                        toastForWin="ONE";

//
//                    st.makeText(this, "Player Onw Win...", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(this, "Player " + toastForWin + " win", Toast.LENGTH_SHORT).show();
                }
            }


        }

    }

    private void resetButtonData()
    {
        for (int index=0; index<gridLayout.getChildCount(); index++)
        {
            ImageView imageView =(ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);


            currenPlayer = Player.ONE;

            playerChoices[0] = Player.No;
            playerChoices[1] = Player.No;
            playerChoices[2] = Player.No;
            playerChoices[3] = Player.No;
            playerChoices[4] = Player.No;
            playerChoices[5] = Player.No;
            playerChoices[6] = Player.No;
            playerChoices[7] = Player.No;
            playerChoices[8] = Player.No;

            gameOver = false;

            button.setVisibility(View.INVISIBLE);
        }
    }
}
