package com.example.nasir.tic_tac_toe_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // 0=yellow; 1=red;
    int activePlayer = 0;
    String winner;

    int f, g, h, no, i, nos;

    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 0;


            }


            counter.animate().translationYBy(1000f).setDuration(300);
            nos = winChecker(winningPositions);

           // Toast.makeText(getApplicationContext(), "" + winChecker(winningPositions), Toast.LENGTH_SHORT).show();
                        /* for(int[] winningPosition : winningPositions){

                             if(gameState[winningPosition[0]]== gameState[winningPosition[1]]&&
                                     gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                             gameState[winningPosition[0]]!=2)*/
            if (nos == 1) {
                gameIsActive = false;
                String winner = "Red";
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                winnerMessage.setText(winner + " has Won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(view.VISIBLE);

            } else if (nos == 2) {
                winner = "Blue";
                gameIsActive = false;
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                winnerMessage.setText(winner + " has Won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(view.VISIBLE);

            } else {


                boolean gameIsOver = true;
                for (int counterState : gameState) {

                    if (counterState == 2) gameIsOver = false;
                }
                if (gameIsOver) {

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText("Its a Draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);


                }
            }

        }
    }


    void playAgain(View view) {

        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);
        no = 0;


//        gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        for (i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);

        }
    }


   public int winChecker(int[][] a){

      //  int f,g,h;

//      int c=a[0][0];
//        int d=a[0][1];
//         int e=a[0][2];
        for(int q=0;q<8;q++){
          f=gameState[a[q][0]];
          g=gameState[a[q][1]];
          h=gameState[a[q][2]];


            ArrayList<Integer> redTap=new ArrayList();
            redTap.add(f);
            redTap.add(g);
            redTap.add(h);
            String blue="000";
            DFA ID=DFAs(blue);
       //     Toast.makeText(getApplicationContext(), "Array list has  created", Toast.LENGTH_SHORT).show();
            String red="111";

            DFA ID2=DFAs(red);
            //String v="";
            String v= concate(redTap);
            if(ID.valid(v)){
             //   winner="blue";
                no=1;
               // System.out.println("blue has won");
             //   Log.i("blue","blue has won");
            //    Toast.makeText(getApplicationContext(), "blue has won", Toast.LENGTH_SHORT).show();


            }
            else if(ID2.valid(v))
            {
                no=2;
               // winner="Red";
                //System.out.println("Red has won");
              //  Log.i("Red","red  has won");
                //Toast.makeText(getApplicationContext(), "Red Has Won", Toast.LENGTH_SHORT).show();
            }

        }

          return no;
    }
    String concate(ArrayList<Integer> l) {
        String ddfa = "";
        //System.out.println("please input a no ");
        for (int i = 0; i < l.size(); i++) {
            ddfa = ddfa + l.get(i);
        }
        return ddfa;
    }
    DFA DFAs(String ddfa) {
        String input[] = ddfa.split("(?!^)");
        int a = input.length - 1;
        // int value = rand.nextInt(a);
        //System.out.println("hint value is "+value);
        int NSnd = input.length + 2;
        int NSnid = 0;
        int CS = 0;
        int FSnid[] = {input.length};
        //int[][]TT={{1,6,6,6,6},{6,2,6,6,6},{6,6,3,6,6},{6,6,6,4,6},{6,6,6,6,5},{6,6,6,6,6},{6,6,6,6,6}};
        int TT[][] = new int[input.length + 2][input.length];
        for (int i = 0; i < input.length + 2; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i < input.length) {
                    if (i == j) {
                        if (i == 2 && j == 2) {
                            TT[i][j] = 4;
                        }
                        CS++;
                        TT[i][j] = CS;
                    } else {
                        TT[i][j] = input.length + 1;
                    }
                } else {
                    TT[i][j] = input.length + 1;
                }
            }
        }
        //DFA(int NS,int IS,String[] AC,int[][] TT,int[] FS){
        DFA ID = new DFA(NSnid, FSnid, input, TT, NSnd);
        return ID;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

