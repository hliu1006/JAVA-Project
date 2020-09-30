//CS211
//Name: Hong Liu
//Date: April 12th
//Assignment 2# Critter
//This class would define the behavior of HL.

import java.util.*;
import java.awt.*;
public class HL extends Critter { 
    int count;//the number of moves.
   
    public HL(){

    }

    // This method would decide the HL moves.
    public Action getMove(CritterInfo info) {
        count++;//record the number of moves.
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;//infect others in front
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;//move forward
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.LEFT;//turn left when other HL in front.
        }
        return Action.RIGHT;
    }

    // This method would decide the color.
    public Color getColor() {
        return Color.PINK;
    }

    // This method would display "-_-", "*^_^*" and "HL" alternately.
    public String toString() {
        if (count % 3 == 0) {
            return "-_-";
        } else if (count % 3 == 1) {
            return "*^_^*";
        } 
        return "HL";
    }
}
