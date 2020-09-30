///CS211
//Name: Hong Liu
//Date: April 26th
//Assignment 4#

// This program reads an input file of preferences and find a stable marriage
// scenario.  The algorithm gives preference to either men or women depending
// upon whether this call is made from main:
//      makeMatches(men, women);
// or whether this call is made:
//      makeMatches(women, men);

import java.io.*;
import java.util.*;

public class StableMarriage {
    public static final String LIST_END = "END";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("What is the input file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));
        System.out.println();

        List<Person> men = readHalf(input);
        List<Person> women = readHalf(input);
        makeMatches(men, women);
        writeList(men, women, "Matches for men");
        writeList(women, men, "Matches for women");
    }

    public static Person readPerson(String line) {
        int index = line.indexOf(":");
        Person result = new Person(line.substring(0, index));
        Scanner data = new Scanner(line.substring(index + 1));
        while (data.hasNextInt()) {
            result.addChoice(data.nextInt());
        }
        return result;
    }

    public static List<Person> readHalf(Scanner input) {
        List<Person> result = new ArrayList<Person>();
        String line = input.nextLine();
        while (!line.equals(LIST_END)) {
            result.add(readPerson(line));
            line = input.nextLine();
        }
        return result;
    }
    
    //These method would match the men and women.
    public static void makeMatches(List<Person> list1, List<Person> list2) {
        ArrayList<Person> manList = new ArrayList<Person> (list1);
        ArrayList<Person> womanList = new ArrayList<Person> (list2);
        for (Person manIndividual : manList) {
            manIndividual.erasePartner();//set each man to be free;
        }
        for (Person womanIndividual : womanList) {
            womanIndividual.erasePartner();//set each woman to be free;
        }
		  boolean shouldContinue = false;//set up continue to run;
   		   do {
            Iterator<Person> itr = manList.iterator();
            while (itr.hasNext()){
                Person manIndividual = itr.next();
                while (!(manIndividual.hasPartner()) && manIndividual.hasChoices()) {//some man m with a nonempty preference list is free
                    int firstWomanOnMan = manIndividual.getFirstChoice();//first woman on m's list;
                    Person engagedWoman = womanList.get(firstWomanOnMan);//first woman on m's list;
                    List<Integer> womanPerf = engagedWoman.getChoices();//first woman's perference list;
                    int manIndividualIndex = manList.indexOf(manIndividual);
                    for (Person otherManIndividual : manList) {
                        if ((!otherManIndividual.equals(manIndividual)) && otherManIndividual.getPartner() == firstWomanOnMan && womanPerf.indexOf(manList.indexOf(otherManIndividual)) > (womanPerf.indexOf(manIndividualIndex))) {//some man p is engaged to w
                            otherManIndividual.erasePartner();//set otherManIndividual to be free
                        }
                    }
                    manIndividual.setPartner(firstWomanOnMan);//set m and w to be engaged to each other
					     engagedWoman.setPartner(manIndividualIndex);//set m and w to be engaged to each other
			           for (int i = womanPerf.size() - 1; i > womanPerf.indexOf(manIndividualIndex); i--) {//each successor q of m on w's list
                        Person successorQOfM = manList.get(womanPerf.get(i));
				            List<Integer> successorQOfMPerferenceList = successorQOfM.getChoices();//successor's perference list;
                        if (successorQOfMPerferenceList.contains(firstWomanOnMan)) {
				               successorQOfMPerferenceList.remove(successorQOfMPerferenceList.indexOf(firstWomanOnMan));//delete w from q's preference list
                        }
                        womanPerf.remove(i);//delete q from w's preference list                   
		              }
                }
            }
            shouldContinue = false;
            for (Person manItem : manList) {
                if (!manItem.hasPartner() && manItem.hasChoices()) {//some man still free.
	                 shouldContinue = true;//continue to run;
                }
            }
        } while (shouldContinue);
    }

    public static void writeList(List<Person> list1,  List<Person> list2,
                                 String title) {
        System.out.println(title);
        System.out.println("Name           Choice  Partner");
        System.out.println("--------------------------------------");
        int sum = 0;
        int count = 0;
        for (Person p : list1) {
            System.out.printf("%-15s", p.getName());
            if (!p.hasPartner()) {
                System.out.println("  --    nobody");
            } else {
                int rank = p.getPartnerRank();
                sum += rank;
                count++;
                System.out.printf("%4d    %s\n", rank,
                                  list2.get(p.getPartner()).getName());
            }
        }
        System.out.println("Mean choice = " + (double) sum / count);
        System.out.println();
    }
}
