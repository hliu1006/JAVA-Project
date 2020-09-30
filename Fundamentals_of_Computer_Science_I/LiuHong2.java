// 
// Class: CS210
// Written By: Hong Liu
// Date: 03122019
// Deficiency: The file that user enter contains 3 lines of data at least.
//
// Program 2

import java.io.*;
import java.util.*;
public class LiuHong2 {
   //This method would check wether the file exsists and reture the result.
    public static String chooseOption() {
        boolean checkOption = false;
        String optionString = "";
        while (!checkOption) {
            System.out.println("**** Please choose: **** \n\n     A. Load File.\n\n     B. Exit Program.\n");
            System.out.print("     Your selction:");
            Scanner option = new Scanner(System.in);
            optionString = option.nextLine();
            checkOption = optionString.equalsIgnoreCase("A") || optionString.equalsIgnoreCase("B");
            if (!checkOption) {
                System.out.println();
                System.out.println("Wrong option! Please choose A or B.");
                System.out.println();
            }
        }
        return optionString;   
    }

    //This method would check the format of file that user enter and reture the result. This method needs the String arraty, the size of the array and the first line data as the parameters.
    public static boolean checkFormat (String[][] input, int length, String firstLine, int column) {
        if (column != 36) {//Sample"2019-02-10.txt" has 36 colunms.
            return false;
        }
        if (!firstLine.equals("                  Temp     Hi    Low   Out    Dew  Wind  Wind   Wind    Hi    Hi   Wind   Heat    THW   THSW                Rain  Solar   Solar Hi Solar   UV    UV    Hi     Heat    Cool    In     In    In     In           Wind  Wind    ISS   Arc.")) {
            return false;
        }
        String[] timeData = new String[length];
        for (int i = 0; i < length; i++) {
            timeData[i] = input[i][1];//Time in the 2nd colunm in the file.
        }
        if (length > 288) {//The data file will have a maximum of 288 lines of data.
            return false;
        } else if (!minutesDifference(timeData, length)) {
            return false;
        }
        return true;
    }

    // This method would calculate the value of the minute and reture the result. This method needs the time array as the parameter.
    public static int[] getMinuteValue(String[] input, int length) {
        int[] calculateTime = new int[length];
        for (int i = 0; i < length; i++) {
            int lengthInput = input[i].length(); 
            calculateTime[i] = getTimeValue(input[i].substring(lengthInput - 3, lengthInput - 1));       
        }
        return calculateTime;
    }
   
    //This method would return the int result to calculate the inputed time value. This method need the substring of inputed time (string type) as the parameter.   
    public static int getTimeValue(String subString) {
        int timeValue = 0;
        int timeLength = subString.length();
        for (int i = 0; i < timeLength; i++) {
            char a = subString.charAt(i);
            timeValue *= 10;
            timeValue += Character.getNumericValue(a);
        }
        return timeValue;
   }  

    //This method would check the interval of time and reture the result. This method need the time arrasy as the parameter. 
    public static boolean minutesDifference(String[] input, int length) {
        int[] minuteAmount = new int[length];
        minuteAmount = getMinuteValue(input, length);
        for (int i = 0; i < length - 1; i++) {
            int minutesDifference = minuteAmount[i + 1] - minuteAmount[i];
            if (minutesDifference != 10 && minutesDifference != -50) {
                System.out.println("The time format in the file is not the 10 minute data format.");
                return false;
            }
        }
        return true;
    }
       
    //This method would display the maximum speed of wind. This method needs the wind array and the length of the array as the parameters.
    public static void maxWind(double[] input, int length) {
        Arrays.sort(input);
        System.out.println("The maximum speed of wind is " + input[length - 1] + " mph.");
        System.out.println();
    }   
    
    //This method would display the maximum temperature. This method needs the data array and the length of the array as the parameters.
    public static void maxTemp(String[][] input, int length) {
        double maxTemprature = 0;
        double[] temprature = new double[length];
        for (int i = 0; i < length; i++) {
            temprature[i] = Double.valueOf(input[i][3]); // exact the temperature data in the 4th colunm and input to a new array.
        }
        for (int i = 0; i < length; i++) {
            if (maxTemprature < temprature[i]) {
                maxTemprature = temprature[i];// find the maximum Temprature.
            }
        }
        for (int i = 0; i < length; i++) {
            if (maxTemprature == temprature[i]) {
                System.out.println("Date: " + input[i][0] + ", " + "Time: " + input[i][1] + ", " + "Maximum Temperature: " + temprature[i] + "°F");
            }
        }
        System.out.println();
    }
    
    //This method would compute the average temperature based on the huminity that the user enter. This method needs the data array and the length of the array as the parameters.
    public static void averageTemp(String[][] input, int length) {
        boolean checkHunimity = false;
        int userHunimity = 0;
        while (!checkHunimity) {
             Scanner huminityInput = new Scanner(System.in);
             System.out.print("Please enter the huminity: ");
             boolean checkInputFormat = huminityInput.hasNextInt();
             if (!checkInputFormat) {// check what user enter is integer number.
                 System.out.println("Please enter the integer number of huminity!");
             } else {
                 userHunimity = huminityInput.nextInt();
                 if (userHunimity <= 100 && userHunimity >= 1) {//check huminity range between 1 to 100, inlusive.
                      checkHunimity = true;
                  }else {
                      System.out.println("Please enter the number between 1 to 100.");
                  }
             }
         }

        double[] doubleTemperature = new double[length];
        int[] huminity = new int[length];
        for (int i = 0; i < length; i++) {
            doubleTemperature[i] = Double.valueOf(input[i][2]);//Temperature at 3rd column.
            huminity[i] = Integer.valueOf(input[i][5]);//Huminity at 6th column.
        }
        int count = 0;
        double totalTemperature = 0;
        double averageTemperature = 0;
        for (int i = 0; i < length; i++) {
            if (huminity[i] >= userHunimity) {//find the temperature at or above huminity that user entered.
                totalTemperature += doubleTemperature[i];
                count++;
            } 
        }
        
        if (count == 0) {
            System.out.println("The huminity that you entered is too high. No temperature found at or above this huminity.");
        } else {
            averageTemperature = totalTemperature / count;
            System.out.printf("The average temperature is %.1f °F.", averageTemperature);
            System.out.println();
        }
        System.out.println();
    }
       
    //This method would compute and display the total Solar Energy. This method needs the solar radiation array and the length of the array as parameters.
    public static void totalSolarEnergy(int[] input, int length) {
        double totalSolarEnergy = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] > 0) {
                totalSolarEnergy += input[i] / 6.0;//Solar Energy in 10 minites interval and then calculate the Solar Energy in one hour.
            }
        }
        System.out.printf("The total Solar Energy is %.1f Wh/m^2.", totalSolarEnergy);
        System.out.println();
    }
    
    //main    
    public static void main(String args[]) throws FileNotFoundException {
        boolean dataFormat = false;
        boolean fileExist = false;
        int row = 0;
        int column = 0;
        String[][] weather = null;
        String firstLine = "";
        
        while (!dataFormat) {
            String option = chooseOption();
            if (option.equalsIgnoreCase("B")) {
                return;
            }
            do {
                System.out.println();
                Scanner console = new Scanner(System.in);
                System.out.print("Please enter the file name: ");
                String filename = console.nextLine();
                File datafile = new File(filename);
                fileExist = datafile.exists();
                if (!fileExist) {
                    System.out.println("This file does not exist!");
                } else {
                    Scanner input = new Scanner(datafile);
                    firstLine = input.nextLine();
                    String secondLine = input.nextLine();
                    while (input.hasNextLine()) {
                        String Line = input.nextLine();
                        row++;
                    }
                    row = row - 1;//minus the 3rd line.
                    Scanner secondLineScan = new Scanner(secondLine);
                    while (secondLineScan.hasNext()) {
                        String token = secondLineScan.next();
                        column++;
                    } 
                    weather = new String[row][column];
                    int i = 0;
                    Scanner inputNew = new Scanner(datafile);  
                    while (inputNew.hasNextLine()) {
                        String line = inputNew.nextLine();
                        if (i >= 3) {//input weather data from the 4th line.
                            Scanner lineScanner = new Scanner(line);
                            int j = 0;
                            while (lineScanner.hasNext()) {
                                weather[i-3][j] = lineScanner.next();//input weather data from the 4th line.
                                j++;
                            }
                        }
                        i++;
                    }    
                }
             } while(!fileExist);                 
                                                
             dataFormat = checkFormat(weather, row, firstLine, column);
             if (!dataFormat) {
                 row = 0;
                 column = 0;
                 System.out.println("Your File Contains Wrong Format!");
                 System.out.println();               
             }
        }
        double[] wind = new double[row];    
        int[] solarEnergy = new int[row];
        for (int i = 0; i < row; i++) {
            wind[i] = Double.valueOf(weather[i][10]);//Wind speed at 11th column.
            solarEnergy[i] = Integer.valueOf(weather[i][19]);//Solar radiation at 20th column.
        }
        maxWind(wind, row);
        maxTemp(weather, row);
        averageTemp(weather, row);
        totalSolarEnergy(solarEnergy, row);
                   
    }//End main.
    
}//End Class.