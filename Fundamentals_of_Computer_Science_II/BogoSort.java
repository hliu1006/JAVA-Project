///CS211
//Name: Hong Liu
//Date: May 10th
//Assignment 5#

import java.util.*;
import java.lang.*;
public class BogoSort {
    public static final int NRange = 5;
	 public static void main(String[] args) {
		
	 	 //int[] myArray = {29, 17, -8, 3, 94, 46, 28, 16};
      
    	 Random generator = new Random();
    	 int[] myArray = new int[NRange];

    	 for (int i = 0; i < NRange; i++) {
       	 myArray[i] = generator.nextInt(NRange) + 1;//creat the Array with random value.
    	 }

    	 long startTime1 = System.currentTimeMillis();
    	 bogoSort(myArray);
    	 long endTime1 = System.currentTimeMillis();
    	 System.out.println(NRange +", " +(endTime1-startTime1));//calculate the run-time 
	 	 printArray(myArray);
   }


	// Places the elements of a into sorted order.
	public static void bogoSort(int[] a) {
		 while (!isSorted(a)) {
           shuffle(a);
       }
   }
   
	// Returns true if a's elements are in sorted order.
	public static boolean isSorted(int[] a) {
       for (int i = 0; i < a.length - 1; i++) {
           if (a[i] > a[i + 1]) {
               return false;
           }
       }
       return true;
   }
   
	// Shuffles an array of ints by randomly swapping each element with an element ahead of it in the array.
	public static void shuffle(int[] a) {
       for (int i = 0; i < a.length - 1; i++) {
           int range = a.length - 1 - (i + 1) + 1;
           int j = (int) (Math.random() * range + (i + 1));// pick a random index in [i+1, a.length-1]
           swap(a, i, j);
       }
   }

   
	// Swaps a[i] with a[j].
	public static void swap(int[] a, int i, int j) {
       if (i != j) {
           int temp = a[i];// use temp variables
           a[i] = a[j];
           a[j] = temp; 
       }
	}
	
	public static void printArray(int[] a)
	{
		for(int i=0; i < a.length; i++)
		{
			System.out.println(a[i]);
			
		}
	}
	
}//end of BogoSort class
