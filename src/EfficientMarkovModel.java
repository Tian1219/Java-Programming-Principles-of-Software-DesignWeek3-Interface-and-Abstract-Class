import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> map;


    /**
     * Takes integer specifying the number of characters to use to predict the next character.
     */

    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        order = num;
        map = new HashMap<String, ArrayList<String>>();
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);


        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            String successor = follows.get(index);
            sb.append(successor);
            key = key.substring(1) + successor;
        }


        return sb.toString();
    }


    private void buildMap() {
        // Build HashMap of the characters that follow each substring of order length

        for (int pos = 0; pos <= myText.length() - order; pos++) {
            int subEnd = pos + order;
            // Identify the current substring
            String sub = myText.substring(pos, subEnd);
            // If HashMap doesn't yet contain substring as key
            if (!map.containsKey(sub)) {
                map.put(sub, new ArrayList<String>());
            }
            // Add to HashMap the character that follows current substring, if there is one
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                ArrayList<String> followers = map.get(sub);
                followers.add(follower);
                map.put(sub, followers);

            }
        }


    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);

    }


    public void printHashMapInfo() {
        // Print the number of keys in the HashMap

        System.out.println("Number of kys: " + map.size());
        // Print the size of the largest largest ArrayList of characters in the HashMap
        int largestSize = 0;
        for (String key : map.keySet()) {
            int keySize = map.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
            System.out.println("The size of the largest ArrayList of characters: " + largestSize);

            // Print the keys that have the maximum size value
            for(String keys : map.keySet()){
                if(map.get(keys).size() == largestSize){
                    System.out.println(keys);
                }

            }
            System.out.println("\n");
        }


    }
}
