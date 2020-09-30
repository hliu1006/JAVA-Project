///CS211
//Name: Hong Liu
//Date: May 24th
//Assignment 7#

import java.util.*;
public class Assignment7 {
    
    public static void main(String[] args) {
        
        int[] myArray1 = {9, 5, 4, 6, 1, 9, 8, 7, 3};
        ArrayIntList test1 = new ArrayIntList();		// my data structure
        for (int i: myArray1) test1.add(i);				// use for each 
        
        int[] myArray2 = {3, 8, 2, 5, 19, 24, -3, 0};
        ArrayIntList test2 = new ArrayIntList();		// my data structure
        for (int i: myArray2) test2.add(i);				// use for each 
        
        int[] myArray3 = {5, 9, 4, 6, 1, 9, 7, 8, 3};
        ArrayIntList test3 = new ArrayIntList();
        for (int i: myArray3) test3.add(i);
        
        ArrayIntList test4 = new ArrayIntList();
    
        testupToNowTotal(test1);
        testupToNowTotal(test2);
        testupToNowTotal(test4);
        
	     testisPairSorted(test1);
        testisPairSorted(test2);
        testisPairSorted(test3);
        test3.clear();
                
        testisPairSorted(test3);
        test3.add(1);
        testisPairSorted(test3);
        test3.add(-1);
        testisPairSorted(test3);
        test3.clear();
        test3.add(1);
        test3.add(2);
        testisPairSorted(test3);
        
	     testremoveLast(test1, 4);
        testremoveLast(test2, 0);
        testremoveLast(test1, test1.size());
    }
    
    //This method would test method "upToNowTotal()".
    private static void testupToNowTotal (ArrayIntList test) {	
        ArrayIntList result = new ArrayIntList();
        result = test.upToNowTotal();
        System.out.println(test.toString());
        System.out.println();
        System.out.println(result.toString());
        System.out.println();
    }
    
    //This method would test method "isPairSorted()".
    private static void testisPairSorted (ArrayIntList test) {
        System.out.println(test.toString());
        System.out.print("isPairwiseSorted? ");
        System.out.println(test.isPairwiseSorted());
        System.out.println();
   }
   
   //This method would test method "removeLast()".
   private static void  testremoveLast(ArrayIntList test, int n) {
        System.out.println(test.toString());
        System.out.println("remove last " + n + " elements");
        test.removeLast(n);
        System.out.println(test.toString());
        System.out.println();
   }
}
