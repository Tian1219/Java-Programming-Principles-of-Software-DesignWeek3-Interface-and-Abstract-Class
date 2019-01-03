
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order = 999;


    public AbstractMarkovModel() {
        myRandom = new Random();
    }
   
    public void setTraining(String s) {
        myText = s.trim();
    }

     public abstract String getRandomText(int numChars);

    protected  ArrayList<String> getFollows (String key){

        ArrayList<String> follows = new ArrayList<String>();
        int pos =0;

        while(true){
            int index = myText.indexOf(key,pos);
            int indexOfSuccessor = index + key.length();

            if(index == -1 || indexOfSuccessor>= myText.length()){
                break;
            }

            String  successor = myText.substring(indexOfSuccessor,indexOfSuccessor+1);
            follows.add(successor);
            pos = index+1;
        }

        return follows;
    }

    @Override
    public String toString(){
        return "MarkovModel of order " + order;
    }

}
