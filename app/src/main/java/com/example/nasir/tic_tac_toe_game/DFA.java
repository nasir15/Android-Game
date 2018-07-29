package com.example.nasir.tic_tac_toe_game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nasir on 7/4/2018.
 */

public class DFA {

    int NoOfStates;
    int initialState;
    int[] finalStates;
    String[] allowedchars;
    int[][] TransitionTable;
    public DFA(int is,int[] fs,String[] ac ,int[][]tt,int ns)
    {
        this.NoOfStates=ns;
        this.initialState=is;
        this.finalStates=fs;
        this.allowedchars=ac;
        this.TransitionTable=tt;
    }
    public DFA(DFA f)
    {
        this.NoOfStates=f.NoOfStates;
        this.initialState=f.initialState;
        this.finalStates=f.finalStates;
        this.allowedchars=f.allowedchars;
        this.TransitionTable=f.TransitionTable;
    }
    boolean valid(String input)
    {
        if(charsAllowed(input))
        {
            int cs=0;
            for(int i=0;i<input.length();i++)
            {
                cs=transition(cs,""+input.charAt(i));
            }
            return onFinal(cs);
        }
        return false;
    }
    boolean charsAllowed(String input)
    {
        boolean allClear=true;
        boolean valid=false;
        for(int i=0;i<input.length();i++)
        {
            valid=false;
            for(int j=0 ;j<allowedchars.length;j++)
            {
                Pattern p=Pattern.compile(allowedchars[j]);
                Matcher m=p.matcher(""+input.charAt(i));
                if(m.matches())
                    valid=true;
            }
            allClear = allClear && valid;
        }

        return allClear;
    }
    int transition(int cs,String character)
    {
        int index=0;
        for(int i=0;i<this.allowedchars.length;i++)
        {
            int  r=this.NoOfStates-1;
            Pattern p=Pattern.compile(allowedchars[i]);
            Matcher m=p.matcher(""+character);
            if(m.matches()&&this.TransitionTable[cs][i]!=r)
            {
                index=this.TransitionTable[cs][i];
                break;
            }
        }
        return index;

        // System.out.print(this.TransitionTable[cs][index]+"  ");

    }
    boolean onFinal(int cs)
    {
        for(int i=0;i<this.finalStates.length;i++)
        {
            if(cs==finalStates[i])
                return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        String s="Transition Table\n";
        for(int i=0;i<this.NoOfStates;i++)
        {
            s=s+i;
            for(int j=0;j<this.allowedchars.length;j++)
            {
                s=s+"\t"+TransitionTable[i][j];
            }
            s=s+"\n";
        }
        s=s+"Final States\n";
        for(int i=0;i<this.finalStates.length;i++)
            s=s+this.finalStates[i]+"\n";
        s=s+"initial state\n";
        s=s+this.initialState;
        return s;
    }
}
