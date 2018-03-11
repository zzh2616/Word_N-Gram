
/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}

		return sb.toString().trim();
	}

  // method that finds the position of a certain word "target" in a string array "words"; if no word found, method returns -1
  public int indexOf(String[] words, String target, int start){
    int ans = -1;
    for (int i = start; i < words.length; i++) {
      if (words[i].equals(target)) {
        ans = i;
        break;
      }
    }
    return ans;
  }

	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
      int idx = 0;
      while (indexOf(myText, key, idx) != -1) {
        idx = indexOf(myText, key, idx);
        if(idx >= myText.length - 1) break;
        follows.add(myText[idx+1]);
        idx++;
      }
	    return follows;
    }

}
