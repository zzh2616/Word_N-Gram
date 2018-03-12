
/**
 * Write a description of class EfficientMarkovWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;  //order of the Markov class
    private HashMap<WordGram, ArrayList<String>> keyMap;

    public HashMap<WordGram, ArrayList<String>> buildMap(WordGram key){
      if (!keyMap.containsKey(key)) {
        keyMap.put(key, new ArrayList<String>());
      }
      return keyMap;
    }

    public void printMapInfo(){
      System.out.println("Map size is " + keyMap.size());
      int maxSize = 0;
      for (ArrayList<String> textSet : keyMap.values()) {
        if (textSet.size() > maxSize) {
          maxSize = textSet.size();
        }
      }
      System.out.println("Largest textSet size is " + maxSize);
      ArrayList<WordGram> bigKeys = new ArrayList<WordGram>();
      for (WordGram key : keyMap.keySet()) {
        if (keyMap.get(key).size() == maxSize) {
          bigKeys.add(key);
        }
      }
      System.out.print("keys with max size of values are \n" + bigKeys + "\n");
    }

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

  public void setTraining(String text){
		myText = text.split("\\s+");
    keyMap = new HashMap<WordGram, ArrayList<String>>();
    for(int k = 0; k <= myText.length - myOrder; k++){
      WordGram key = new WordGram(myText, k, myOrder);
      buildMap(key);
      if (k == myText.length - myOrder) {
        printMapInfo();
        return ;
      } else{
        keyMap.get(key).add(myText[k+myOrder]);
      }
    }
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
      if (keyMap.containsKey(key)) {
        ArrayList<String> follows = getFollows(key);
        if (follows.size() == 0) {
            break;
        }
        index = myRandom.nextInt(follows.size());
        String next = follows.get(index);
        sb.append(next);
        sb.append(" ");
        key = key.shiftAdd(next);
      } else {
        break;
      }
		}

		return sb.toString().trim();
	}

  // method that finds the position of a certain word "target" in a string array "words"; if no word found, method returns -1
  public int indexOf(String[] words, WordGram target, int start){
    int ans = -1;
    for (int i = start; i <= words.length - target.length(); i++) {
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
      return keyMap.get(key);
  }

}
