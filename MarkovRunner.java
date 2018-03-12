
/**
 * Write a description of class MarkovRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        // String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        // MarkovWordOne markovWord = new MarkovWordOne();
        // MarkovWordTwo markovWord = new MarkovWordTwo();
        // MarkovWord markovWord = new MarkovWord(3);
        EfficientMarkovWord emarkovWord = new EfficientMarkovWord(2);

        // long t1 = System.nanoTime();
        // runModel(emarkovWord, st, 100, 42);
        // runModel(emarkovWord, st, 100, 42);
        // runModel(emarkovWord, st, 100, 42);
        // long t2 = System.nanoTime();
        // System.out.println("time used by Efficient Markov Model: " + (t2-t1) + "\n");

        runModel(emarkovWord, st, 100, 65);
        // runModel(markovWord, st, 100, 42);
        // runModel(markovWord, st, 100, 42);
        // long t3 = System.nanoTime();
        // System.out.println("time used by Markov Model: " + (t3-t2) + "\n");
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
