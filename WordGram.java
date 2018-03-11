
public class WordGram {
    private String[] myWords;
    private int myHash;   //hash code of the WordGram

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;// TODO: Complete this method
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (int i = 0; i < myWords.length; i++) {
          ret = ret + myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (myWords.length != other.length()) {
          return false;
        }
        for (int i = 0; i < myWords.length; i++) {
          if (!myWords[i].equals(other.wordAt(i))) {
            return false;
          }
        }
        return true;

    }

    //This method shift truncate the first word in the wordGram and apped the new word in the parameter
    public WordGram shiftAdd(String word) {
        String[] newWords = new String[myWords.length];
        for (int i = 0; i< myWords.length-1; i++) {
          newWords[i] = myWords[i+1];
        }
        newWords[myWords.length-1] = myWords[myWords.length-1];
        WordGram out = new WordGram(newWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end.
        // you lose the first word
        // TODO: Complete this method
        return out;
    }

}
