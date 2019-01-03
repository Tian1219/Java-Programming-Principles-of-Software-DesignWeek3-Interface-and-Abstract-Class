import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{

    private int keyLength;

    public MarkovModel(int num) {
        myRandom = new Random();
        keyLength = num;
        order = num;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyLength);
        String key = myText.substring(index,index+keyLength);
        sb.append(key);


        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size()==0){break;}

            index = myRandom.nextInt(follows.size());
            String successor = follows.get(index);
            sb.append(successor);
            key = key.substring(1) + successor;
        }


        return sb.toString();
    }


}
