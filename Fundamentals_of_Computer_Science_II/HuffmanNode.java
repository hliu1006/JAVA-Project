//CS211
//Name: Hong Liu
//Date: Jun 7th
//Assignment 8#
import java.util.*;
import java.lang.*;
import java.io.*;

public class HuffmanNode implements Comparable<HuffmanNode> {   
    
    public char character;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;
    
    //This method would construct a new node.
    public HuffmanNode() {

    } 
    
    //This method would construct a new node.
    public HuffmanNode(char character, int frequency) {
        
        this.frequency = frequency;
        this.character = character;
        this.left = null;
        this.right = null;
               
    }     
    
    //This method would check the node is a leaf or not.
    public boolean isLeaf() {
        return this.left == null && this.right == null;     
    }
    
    /* This method would provide a count of the characters in an input file, 
    and place those counts into a Map, with character as the unique key mapped into 
    the integer counts of that character*/
    public static Map<Character, Integer> getCounts(InputStream input) {
        if (input == null) {
            return null;
        }

        Map<Character, Integer> result = new HashMap<Character, Integer>();
        Scanner inputWord = new Scanner(input);
        while (inputWord.hasNextLine()) {
            String words = inputWord.nextLine();
            for (int i = 0; i < words.length(); i++) {
	             char temp = words.charAt(i);
	             if (result.containsKey(temp)) {
                     // seen this character before; increase count by 1
                     int count = result.get(temp);
                     result.put(temp, count + 1);
                } else {
                     // never seen this character before
                     result.put(temp, 1);
	             }
            }
        }
        result.put((char)4, 1);
        return result;
     }
     
     //This method would compare the node in order to confirm the order of the node.   
     public int compareTo(HuffmanNode o) {
        if (this.frequency == o.frequency){
             return (int)o.character - (int)this.character;
          } else {
             return this.frequency - o.frequency;
          }
     }

}
