
/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;  //order of the Markov class

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram key = new WordGram(myText, index, myOrder);
    for (int i = 0; i< key.length(); i++) {
      sb.append(key.wordAt(i));
  		sb.append(" ");
    }
		for(int k=0; k < numWords-1; k++){
	    ArrayList<String> follows = getFollows(key);
	    if (follows.size() == 0) {
	        break;
	    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
      key.shiftAdd(next);
		}

		return sb.toString().trim();
	}

  // method that finds the position of a certain word "target" in a string array "words"; if no word found, method returns -1
  public int indexOf(String[] words, WordGram target, int start){
    int ans = -1;
    for (int i = start; i < words.length - target.length() - 1; i++) {
      boolean flag = true;
      for (int k = 0; k < target.length() ; k++) {
        if (!words[i+k].equals(target.wordAt(k))) {
          flag = false;
        }
      }
      if (flag) {
        ans = i;
        break;
      }
    }
    return ans;
  }

	private ArrayList<String> getFollows(WordGram key) {
	    ArrayList<String> follows = new ArrayList<String>();
      int idx = 0;
      while (indexOf(myText, key, idx) != -1) {
        idx = indexOf(myText, key, idx);
        if(idx >= myText.length - key.length()) break;
        follows.add(myText[idx+key.length()]);
        idx++;
      }
	    return follows;
    }

}
