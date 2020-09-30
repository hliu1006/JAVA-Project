//CS211
//Name: Hong Liu
//Date: Jun 7th
//Assignment 8#

import java.util.*;
import java.lang.*;
import java.io.*;

public class HuffmanTree {
    
    private HuffmanNode root;
    
    //storage the leaves from the map.
    public PriorityQueue<HuffmanNode> trees = new PriorityQueue<>();
    
    //storage the pairs of charactor and code.
    public TreeMap<Character, String> codes = new TreeMap<>();
    
    //This method would construct a new HuffmanTree. 
    public HuffmanTree(Map<Character, Integer> counts) {

        Set<Character> set1 = new HashSet<Character>(counts.keySet());
        for (char word : set1) {
            HuffmanNode node = new HuffmanNode();
            node.character = word;
            node.frequency = counts.get(word);          
            trees.add(node);

        } 
        
        while (trees.size()>1){
            HuffmanNode left = trees.poll();
            HuffmanNode right = trees.poll();

            HuffmanNode temp = new HuffmanNode();
            temp.frequency = left.frequency + right.frequency;
            temp.left = left;
            temp.right = right;
            root = temp;
            trees.add(temp);
       }
    }  	
    
    //According to the inputFile, a text file, this method would transform the charactor to the code (like 010...).   
    public StringBuilder compress(InputStream inputFile) {
        String result = "";
        StringBuilder resultOfCompress = new StringBuilder();
        
        compress(this.root, result);

        Scanner inputWord = new Scanner(inputFile);
        while (inputWord.hasNextLine()) {
            String words = inputWord.nextLine();
            for (int i = 0; i < words.length(); i++) {
	             char temp = words.charAt(i);
                resultOfCompress.append(codes.get(temp));
            }
        }
        resultOfCompress.append(codes.get((char)4));       
        return resultOfCompress;
    }
    
    //This method would help to transform the charactor to the code with the recusive method.
    private void compress(HuffmanNode temp, String s) {         
        if (temp != null) {
            if(temp.left != null) {
                compress(temp.left, s + "0");
            } 
            if (temp.right != null) {
                compress(temp.right, s + "1");
            } 
            if (temp.left == null && temp.right == null) { 
                //Storage the pairs of charactor and the number(String tpye). 
                codes.put(temp.character, s);
            } 
        }
             
    }
    
    //According to the inputString, a line of numbers like 1 or 0, this method would transform the number to the string.
    public StringBuilder decompress(StringBuilder inputString) {
        StringBuilder result = new StringBuilder();
        String target = inputString.toString();
        
        //found the code of EOF.
        String codeOfEOF = codes.get((char)4);
        //delet the code of EOF from the target string.
        target = target.substring(0,target.length() - codeOfEOF.length());
        // storage the pairs of number(String type) and character (Char type).
        TreeMap<String, Character> decodes = new TreeMap<>();
        Set<Character> codesChar = codes.keySet();
        for (Character k :codesChar) {
            //do not add the EOF to decode.
            if (k != (char)4) {
            decodes.put(codes.get(k),k);
            }
        }
        Set<String> targetDeCodes = decodes.keySet();
        String temp = "";
        int i = 1;
        while (target.length() > 0) {
            temp = target.substring(0, i);
            if (targetDeCodes.contains(temp)) {  
                Character decode = decodes.get(temp);  
                result.append(decode);  
                target = target.substring(i);  
                i = 1;  
            } else {  
                i++;  
            }
        }
        return result;
    } 
    
    //This method would print the HuffmanTree.
    public String printSideways() {
        StringBuilder result = new StringBuilder();
        result = printSideways(this.root,0,result);
        return result.toString();       
    }
    
    //This method would help to print the HuffmanTree.
    private StringBuilder printSideways(HuffmanNode temp, int level, StringBuilder result) {
        String text;       
        if (temp != null) {
            printSideways(temp.right, level + 1, result);
            for (int i = 0; i < level; i++) {
                result.append("    ");
            }
            if (temp.isLeaf()) {
                text = "char(" + (byte)temp.character +")";
            } else {
                text = "count";
            }
            result.append(temp.frequency + " = " + text + "\n\n");            
            printSideways(temp.left, level + 1, result);
        }
        return result;
    }      

}
