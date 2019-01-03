import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {

    public void testGetFollow(){

        MarkovOne markov = new MarkovOne();

        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
    }

    public void testGetFollowsWithFile(){
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String ftStr = fr.asString().replace('\n', ' ');
        markov.setTraining(ftStr);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows.size());
    }
}
