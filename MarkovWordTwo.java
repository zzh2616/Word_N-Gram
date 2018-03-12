
/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
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
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key[] = {myText[index], myText[index+1]};
    for (int i = 0; i< key.length; i++) {
      sb.append(key[i]);
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
      for (int i = 0; i< key.length-1; i++) {
        key[i] = key[i+1];
      }
      key[key.length-1] = next;
		}

		return sb.toString().trim();
	}

  // method that finds the position of a certain word "target" in a string array "words"; if no word found, method returns -1
  public int indexOf(String[] words, String[] target, int start){
    int ans = -1;
    for (int i = start; i <= words.length - target.length; i++) {
      boolean flag = true;
      for (int k = 0; k < target.length ; k++) {
        if (!words[i+k].equals(target[k])) {
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

	private ArrayList<String> getFollows(String[] key) {
	    ArrayList<String> follows = new ArrayList<String>();
      int idx = 0;
      while (indexOf(myText, key, idx) != -1) {
        idx = indexOf(myText, key, idx);
        if(idx >= myText.length - 2) break;
        follows.add(myText[idx+2]);
        idx++;
      }
	    return follows;
    }

}
