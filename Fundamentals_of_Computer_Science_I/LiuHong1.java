// 
// Class: CS210
// Written By:
// Date: 02192019
// Deficiency: if entering the string, the metnod singleTax() could not address this problem. 
//
// Program 1
// Complete the implementation of the following methods.
// Limited to using: 
// - java concepts covered in chapters 1~5
// - concepts from class demos
// - Character class methods (see text Appendix A, page 1157)
// Include any helper methods as part of this class file. (place these before the main)
// 

import java.util.*;
public class LiuHong1 { // rename class to your LastnameFirstname1.java
    // Use current (2018) year 1040 tax instructions (http://www.irs.gov/pub/irs-pdf/i1040.pdf).
    //       1040 form (http://www.irs.gov/pub/irs-pdf/f1040.pdf).
    // Returns: the proper tax for single filing status (Line 11a of 1040 form)
    // 
    // To determine tax:
    //   Use Tax Table
    //   or 
    //   Tax Computation as appropriate.
    //
    // - Round tax to nearest penny.
    // - Return 0 if taxable income is negative
    //
    //
   
   
    // singleTax 
    // Use current (2018) year 1040 tax instructions (http://www.irs.gov/pub/irs-pdf/i1040.pdf).
    //       1040 form (http://www.irs.gov/pub/irs-pdf/f1040.pdf).
    //This method would calculate the proper tax for single filing status and then print out the result. This method needs the taxable income (double type) as the parameter. 
    public static double singleTax(double income) {  
        int calculateTaxRange = 0;
        double taxAmount = 0;
        int a = 0;
        if (income < 5) {
            return 0;
        } else if (income >= 5 && income < 15) {
            return 1;
        } else if (income >= 15 && income < 25) {
            return 2;          
        } else if (income >= 25 && income < 3000) {
            calculateTaxRange = (int) income / 25;
            return Math.round(calculateTaxAmount((calculateTaxRange * 25 * 2 + 25) / 2));           
        } else if (income >= 3000 && income < 100000) {
            calculateTaxRange = (int) income / 50;
            return Math.round(calculateTaxAmount((calculateTaxRange * 50 * 2 + 50) / 2));           
        } else {
            taxAmount = calculateTaxAmount(income) * 100;
            a = (int)taxAmount;
            taxAmount = a/100.00;
            return taxAmount;
        }
    }
    
    //These methods define the fixed values involed in methoed calculateTaxAmount(double income).
    public static final double LEVEL1 = 9525;
    public static final double LEVEL2 = 38700;
    public static final double LEVEL3 = 82500;
    public static final double LEVEL4 = 157500;
    public static final double LEVEL5 = 200000;
    public static final double LEVEL6 = 500000;
    public static final double RATE0 = 0.10;
    public static final double RATE1 = 0.12;
    public static final double RATE2 = 0.22;
    public static final double RATE3 = 0.24;
    public static final double RATE4 = 0.32;
    public static final double RATE5 = 0.35;
    public static final double RATE6 = 0.37;
    
    //This method would calculate the tax amount and then return the result. This method needs the taxable income (double type) as the the parameters. 
    public static double calculateTaxAmount(double income) {
        double taxAmount = 0;
        double differentTax1 = LEVEL1 * RATE0;
        double differentTax2 = (LEVEL2 - LEVEL1) * RATE1 + differentTax1;
        double differentTax3 = (LEVEL3 - LEVEL2) * RATE2 + differentTax2;
        double differentTax4 = (LEVEL4 - LEVEL3) * RATE3 + differentTax3;
        double differentTax5 = (LEVEL5 - LEVEL4) * RATE4 + differentTax4;
        double differentTax6 = (LEVEL6 - LEVEL5) * RATE5 + differentTax5;
        
        if (income > LEVEL6) {
            taxAmount = (income - LEVEL6) * RATE6 + differentTax6;
        } else if (income > LEVEL5) {
            taxAmount = (income - LEVEL5) * RATE5 + differentTax5;
        } else if (income > LEVEL4) {
            taxAmount = (income - LEVEL4) * RATE4 + differentTax4;
        } else if (income > LEVEL3) {
            taxAmount = (income - LEVEL3) * RATE3 + differentTax3;
        } else if (income > LEVEL2) {
            taxAmount = (income - LEVEL2) * RATE2 + differentTax2;
        } else if (income > LEVEL1) {
            taxAmount = (income - LEVEL1) * RATE1 + differentTax1;
        } else {
            taxAmount = income * RATE0;
        }
        return taxAmount;
    }
   
    //
    // secondsAfterMidnight
    // Input:   String that represents time of day
    // Returns: integer number of seconds after midnight (return -1 if String is not valid time of day)
    // 
    // General time of day format HH:MM:SS(AM/PM)
    //
    // Examples:
    // Input String   Return Value
    // "12:34:09AM"   2049
    // "12:00:00PM"   43200 (common noon)
    // "12:00:02am"   2 (AM/PM case insensitive)
    // "3:03:03Pm"   54183 (two digit MM and SS required)
    // "7:11:03A"   -1
    // "7:11:3AM"   -1
    // "7:91:73PM"   -1
    // "23:45:12"   -1 (do not allow 24 hour clock format)
    //
    //This method would return the integer number of seconds after midnight, according to the inputed time (string type). 
    public static int secondsAfterMidnight(String time) {
        int timeLength = time.length();
   
        if (!(timeLength == 9 || timeLength == 10)) {
            return -1;
        }
   
        boolean isAMPMCorrect = isLastTwoCharsAMOrPM(time.substring(timeLength - 2));
        if (isAMPMCorrect == false) {
            return -1;
        }
   
        boolean isColonCorrect = checkColonCorrectness(timeLength, time);
        if (isColonCorrect == false) {
            return -1;
        }
   
        int hourValue = getTimeValue(time.substring(0, timeLength - 8), 1, 12);
        if (hourValue == -1) {
            return -1;
        }
        hourValue = calculateHourValue(hourValue, time.substring(timeLength - 2));
   
        int minuteValue = getTimeValue(time.substring(timeLength - 7, timeLength - 5), 0, 59);
        if (minuteValue == -1) {
            return -1;
        }
   
        int sencondValue = getTimeValue(time.substring(timeLength - 4, timeLength - 2), 0, 59);
        if (sencondValue == -1) {
            return -1;
        }
        
        return hourValue * 3600 + minuteValue * 60 + sencondValue;
    }
   
   
    //This method would return the boolean result to check the last two charactor are "am" or "pm". This method need the inputed time (string type) and the length of time (int type) as the parameters.   
    public static boolean isLastTwoCharsAMOrPM(String last2Chars) {
        return (last2Chars.equalsIgnoreCase("AM") || last2Chars.equalsIgnoreCase("PM"));  
    }
   
    //This method would return the boolean result to check the inputed time contains ":" at the appropriate lacation. This method need the inputed time (string type) and the length of time (int type) as the parameters.   
    public static boolean checkColonCorrectness(int length, String time) {
        return time.charAt(length - 5) == ':' && time.charAt(length - 8) == ':';
    }
   
    //This method would return the boolean result to calculate the inputed time value. This method need the substring of inputed time (string type) and the range of time (int type) as the parameters.   
    public static int getTimeValue(String subString, int rangeStart, int rangeEnd) {
        int timeValue = 0;
        int timeLength = subString.length();
        for (int i = 0; i < timeLength; i++) {
            char a = subString.charAt(i);
            if (Character.isDigit(a) == false) {
                return -1;
            } else {
                timeValue *= 10;
                timeValue += Character.getNumericValue(a);
            }
        }
        if (timeValue < rangeStart || timeValue > rangeEnd) {
            return -1;
        }
        return timeValue;
   }
   
    //This method would translate the inputed time to 24 hour clock format and then return the int result. This method need the hour value (integer type) and the substring of inputed time (string type) as the parameters.  
    public static int calculateHourValue(int hourValue, String timeAMPM) {
        if (hourValue == 12) {
            if (timeAMPM.equalsIgnoreCase("AM")) {
                return 0;
            } else {
                return 12;
            }
        } else {
            if (timeAMPM.equalsIgnoreCase("AM")) {
                return hourValue;
            } else {
                return hourValue + 12;
            }
        }
    }
   
    //
    // secondsDifference
    // Input:   two time of day Strings
    // Returns: integer number of seconds difference between time of day inputs
    //   (Returns -99999 if either time of day inputs invalid)
    //
    // General time of day format HH:MM:SS
    //
    // Examples:
    // start    end     Return Value
    // "12:34:09AM"   "12:00:00PM"   41151
    // "3:03:03PM"   "12:00:02am"   -54181
    // "6:34:52PM"   "6:34:52PM"   0
    // "3:03:03PM"   "7:91:73PM"   -99999
    // "Nice"    "Day"    -99999
    //
   
    //This method would calculate the seconds difference between time of day inputs and then return the calculated result. This method need the inputed two time (string tpye) as the parameters.  
    public static int secondsDifference(String start, String end) {
        int startTimeSecond = secondsAfterMidnight(start);
        int endTimeSecond = secondsAfterMidnight(end);
        int numberSeconds = 0;
        if (startTimeSecond == -1 || endTimeSecond == -1) {
            numberSeconds = -99999;
        } else {
            numberSeconds = endTimeSecond - startTimeSecond;
        }
        return numberSeconds;
    } 
      
    //main
    public static void main(String args[]) {
    //Test Your Methods
        Scanner console = new Scanner(System.in);
        System.out.print("Please enter your taxable income: $");
        double texableIncome = console.nextDouble();
        double myTaxAmount = singleTax(texableIncome);
        System.out.println("Your tax amount is $" + myTaxAmount + ".");
           
        System.out.print("Please enter the time: ");
        String time = console.next();
        int secondsAfterMidnight = secondsAfterMidnight(time);
        System.out.println(secondsAfterMidnight);
        System.out.println();
   
        System.out.print("Please enter the start time: ");
        String startTime = console.next();
        System.out.print("Please enter the end time: ");
        Scanner text = new Scanner(System.in);
        String endTime = text.next();
        int secondsDifference = secondsDifference(startTime, endTime);
        System.out.print(secondsDifference);
    }//End Main
}//End Class
