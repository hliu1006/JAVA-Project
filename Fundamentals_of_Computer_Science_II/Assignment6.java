import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

///CS211
//Name: Hong Liu
//Date: May 17th
//Assignment 6#

public class Assignment6 {

	 public static void main(String[] args) {
		testSeeingThreeMethod();
		testTwoStacksAreEqualMethod();
		testIsMirrored();	
      
	 }
    
	 //This method would take a stack of integers as a parameter and replaces every value in the stack with two occurrences of that value. 
	  public static void seeingThree(Stack<Integer> s) {
		   Stack<Integer> temp = new Stack<Integer>();//use one stack as auxiliary storage.
         int length = s.size();
         for (int i = 0; i < length; i++) {
             int n = s.pop();
             temp.push(n);
         }
         while (!temp.isEmpty()) {
             int n = temp.pop();
             s.push(n);
             s.push(n);
             s.push(n);
         }
	  }
	 
    //This method would take as parameters two stacks of integers and returns true if the two stacks are equal.  
	 public static boolean twoStacksAreEqual(Stack<Integer> s1, Stack<Integer> s2) {
		 if (s1.size() != s2.size()) {
	 	 	  return false;
	 	 } else {
	 	 	  Stack<Integer> s3 = new Stack<Integer>();//use one stack as auxiliary storage.
	 	 	  boolean same = true;
	 	 	  while (same && !s1.isEmpty()) {
	 	 	 	   int num1 = s1.pop();
	 	 	 	   int num2 = s2.pop();
	 	 	 	   if (num1 != num2) {//compare the value in s1 and s2.
	 	 	 	 	    same = false;
	 	 	 	   }
	 	 	 	   s3.push(num1);
	 	 	 	   s3.push(num2);
	 	 	   }
	 	 	   while (!s3.isEmpty()) {
	 	 	 	    s2.push(s3.pop());//recover s2.
	 	 	 	    s1.push(s3.pop());//recover s1.
	 	 	   }
	 	  return same;
	 	 }	
	}

   //This method would take a queue of integers as a parameter and returns true if the numbers in the queue represent a palindrome
	public static boolean isMirrored(Queue<Integer> q) {
		 Stack<Integer> temp1 = new Stack<Integer>();//use one stack as auxiliary storage.
       int length = q.size();
       for (int i = 0; i < length; i++) {
           int n = q.remove();
           temp1.push(n);
           q.add(n);
       }
       boolean result = true;
       for (int i = 0; i < length; i++) {
           int n1 = q.remove();
           int n2 = temp1.pop();
           if (n1 != n2) {//compare the value in q and temp1.
               result = false;
           }
           q.add(n1);
       }
       return result;
	}


	private static void testIsMirrored() {

		Queue<Integer> myQueueP  = new LinkedList<Integer>();
	
		for (int i = 0; i < 5; i++)
		{
		
			System.out.print(i);
			myQueueP.add(i);
			
		}
		for (int i = 3; i >= 0 ; i--)
		{
			
			System.out.print(i);
			myQueueP.add(i);
				
		}
		
		System.out.println();
		
		System.out.println(isMirrored(myQueueP) + " isMirrord");
		
	}


	private static void testTwoStacksAreEqualMethod() {
		Stack<Integer> myStack1  = new Stack<Integer>();	
		Stack<Integer> myStack2  = new Stack<Integer>();
		Stack<Integer> myStack3  = new Stack<Integer>();
		Stack<Integer> myStack4  = new Stack<Integer>();
	
		for (int i = 0; i < 5; i++)
		{
			myStack1.push(i);
			myStack2.push(i);
			myStack4.push(i);
		
		}
		for (int i = 0; i < 6; i++)
		{
			myStack3.push(i);
		
		}
	
		System.out.println(twoStacksAreEqual(myStack1,myStack2) + " Same Stack ");
		
		System.out.println(twoStacksAreEqual(myStack3, myStack4) + " Not Same Stack");
		
	}


	private static void testSeeingThreeMethod() {
		Stack<Integer> myStack  = new Stack<Integer>();	
		
		for (int i = 0; i < 5; i++)
		{
			myStack.push(i);

		}
		
		
		System.out.println();
		print(myStack);
		
		seeingThree(myStack);
	
		print(myStack);
		
	}

	private static void print(Stack<Integer> s) {
		Enumeration<Integer> e = s.elements();

		 while ( e.hasMoreElements() )
		      System.out.print( e.nextElement() + " " );
		    System.out.println();

	
	}

} //end of Assignment6 
