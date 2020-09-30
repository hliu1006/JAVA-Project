// 
// Class: Function
// Written By: Hong Liu
// Date: 03-12-2019
//
// Complete the implementation of the following class.
//
// This class will model a class variable to hold a mathematical function of up to 10 terms.
// The function will have one independent variable.
// Terms that the function will model are:
//     Monomials of the form: 5.3x^0.5
//     Trigonometric terms sin(x), cos(x), tan(x) of the form: 1.2cos(3.0x) 
//

import java.util.*;
public class Function {    
    //f(x)=Cx^P, store C and P to array factorExpint, factorExpint[][0] = C, factorExpint[][1] = P.
    double[][] factorExpint = new double[20][2];
    //f(x)=coeff*trigFunction(px), store C and P to array trigFactor, trigFactort[][0] = coeff, factorExpint[][1] = p, trigText[] = trigFunction.
    double[][] trigFactor = new double[20][2];
    String[] trigText = new String[20];
    
    //This method would intialize array trigText.
    public void initialTrigText(String[] trigText) {
        for (int i = 0; i < 20; i++) {
            trigText[i] = "";
        }
    }
   
    //This method would intialize Function to f(x)=0.
    public Function() {
        clear();  
    } 

    //This method would set Function to f(x)=c. This method needs double c as the parameters.
    public Function(double c) {
        clear(); 
        factorExpint[0][0] = c;
        factorExpint[0][1] = 0;
    }
      
    //This method would set Function to f(x)=bx+c. This method needs double b and double c as the parameters.
    public Function(double b, double c) {
        clear(); 
        factorExpint[0][0] = c;
        factorExpint[0][1] = 0;
        factorExpint[1][0] = b;
        factorExpint[1][1] = 1;
    } 

    //This method would set Function to f(x)=ax^2+bx+c. This method needs double a, double b and double c as the parameters.
    public Function(double a, double b, double c) {
        clear(); 
        factorExpint[0][0] = c;
        factorExpint[0][1] = 0;
        factorExpint[1][0] = b;
        factorExpint[1][1] = 1;
        factorExpint[2][0] = a;
        factorExpint[2][1] = 2;
    } 

    //This method would set Function to f(x)=coeff*trigFunction(px). This method needs double coeff, string trigFunction, double p as the parameters.
    public Function(double coeff, String trigFunction, double p) {
        clear();
        boolean checkingText = trigFunction.equalsIgnoreCase("sin") || trigFunction.equalsIgnoreCase("cos") || trigFunction.equalsIgnoreCase("tan");
        if (!checkingText) {
            return;
        }
        trigFactor[0][0] = coeff;
        trigFactor[0][1] = p;
        trigText[0] = trigFunction;
    } 
   
    //This method would add a polynomial term of the form: Cx^P. to the Function. This method needs double C and double P as the parameters.
    public void addTerm(double C, double P) {
        if (C == 0) {
            return;
        }
        boolean sameExpintExist = false;
        for (int i = 0; i < 20; i++) {
            if (factorExpint[i][1] == P) {
                factorExpint[i][0] += C;
                sameExpintExist = true;
                break;
            }
        }
        if (sameExpintExist == false) {
            for (int i = 0; i < 20; i++) {
                if (factorExpint[i][0] == 0) {
                    factorExpint[i][1] = P;
                    factorExpint[i][0] = C;
                    break;
                }
            }
        } 
    } 
    
    //This method would add a trigonometric term of the form c*trigFunction(px) and make no additions to function for unknown trigFunctions and return false. This method needs double c, string trigFunction, double p as the parameters.
    public boolean addTerm(double c, String trigFunction, double p) {
        boolean result = false;
        boolean sameExpintExist = false;
        if (trigFunction.equalsIgnoreCase("sin") || trigFunction.equalsIgnoreCase("cos") || trigFunction.equalsIgnoreCase("tan")) {      
            result = true;
            if (c == 0) {
                return result;
            }
            for (int i = 0; i < 20; i++) {
                if (trigText[i].equalsIgnoreCase(trigFunction) && trigFactor[i][1] == p) {
                    trigFactor[i][0] += c;
                    sameExpintExist = true;
                    break;
                }
            }
            if (sameExpintExist == false) {
                for (int i = 0; i < 20; i++) {
                    if (trigFactor[i][0] == 0) {
                        trigText[i] = trigFunction;
                        trigFactor[i][1] = p;
                        trigFactor[i][0] = c;
                        break;
                    }
                }
            }
        }
        return result;
    }

    //This method would set Function to f(x)=0
    public void clear() {
        for (int i = 0; i < 20; i++) {
            factorExpint[i][0] = 0;
            factorExpint[i][1] = 0;
            trigFactor[i][0] = 0;
            trigFactor[i][1] = 0;
        }
        initialTrigText(trigText);
    } 
    
    //This method would return the Function value at x. This method needs double x as the parameters.
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < 20; i++) {
            result += factorExpint[i][0] * Math.pow (x, factorExpint[i][1]);
        }
        for (int i = 0; i < 20; i++) {
            if (trigText[i].equalsIgnoreCase("sin")) {
                result += trigFactor[i][0] * Math.sin (trigFactor[i][1] * x);
            } else if (trigText[i].equalsIgnoreCase("cos")) {
                result +=  trigFactor[i][0] * Math.cos (trigFactor[i][1] * x);
            } else if (trigText[i].equalsIgnoreCase("tan")) {
                result += trigFactor[i][0] * Math.tan (trigFactor[i][1] * x);
            } 
        }
        return result;
    }
      
    //This method would return a Function that is the addition of this Function with f. This method needs Function f as the parameters.
    public Function add(Function f) {
        Function result = new Function();
        result.clear();
        for (int i = 0; i < 20; i++) {
            result.addTerm(this.factorExpint[i][0], this.factorExpint[i][1]);
            result.addTerm(this.trigFactor[i][0], this.trigText[i], this.trigFactor[i][1]);
            result.addTerm(f.factorExpint[i][0], f.factorExpint[i][1]);
            result.addTerm(f.trigFactor[i][0], f.trigText[i], f.trigFactor[i][1]);
        } 
        return result;
    }
    
    //This method would return a Function that is the subtraction of this Function with f. This method needs Function f as the parameters.
    public Function subtract(Function f) {
        Function result = new Function();
        result.clear();
        for (int i = 0; i < 20; i++) {
            result.addTerm(this.factorExpint[i][0], this.factorExpint[i][1]);
            result.addTerm(this.trigFactor[i][0], this.trigText[i], this.trigFactor[i][1]);
            result.addTerm(f.factorExpint[i][0] * (- 1), f.factorExpint[i][1]);
            result.addTerm(f.trigFactor[i][0] * (- 1), f.trigText[i], f.trigFactor[i][1]);
        } 
        return result;
    }

    //This method would return a String to represent the function
       public String toString() {
        String result = "";    
        for (int i = 0; i < 20; i++) {
            if (factorExpint[i][0] != 0) {
                if (factorExpint[i][0] > 0 && i > 0 && factorExpint[0][0] != 0) {
                    result += "+";
                }
                if (factorExpint[i][0] == -1 && factorExpint[i][1] != 0) {
                    result += "-";
                } else if (factorExpint[i][0] == -1 && factorExpint[i][1] == 0) {
                    result += factorExpint[i][0];
                } else if (factorExpint[i][0] != 1) {
                    result += factorExpint[i][0];
                }
                if (factorExpint[i][0] == 1 && factorExpint[i][1] == 0) {
                    result += factorExpint[i][0];
                }
                if (factorExpint[i][1] != 0) {
                    result += "x";
                    if (factorExpint[i][1] != 1) {
                        result += "^";
                        if (factorExpint[i][1] < 0) {
                            result += "(";
                        }
                        result += factorExpint[i][1];
                        if (factorExpint[i][1] < 0) {
                            result += ")";
                        }
                    }
                }  
            }
        } 
        for (int i = 0; i < 20; i++) {
            if (trigFactor[i][0] != 0) {
                if (trigFactor[i][0] > 0 && result.length() > 0) {
                    result += "+";
                }
                if (trigFactor[i][0] == -1) {
                    result += "-";
                } else if (trigFactor[i][0] != 1) {
                    result += trigFactor[i][0];
                }
                if (trigText[i].equalsIgnoreCase("sin")) {
                    result += "sin(";
                } else if (trigText[i].equalsIgnoreCase("cos")) {
                    result += "cos(";
                } else if (trigText[i].equalsIgnoreCase("tan")) {
                    result += "tan(";
                }
                if (trigFactor[i][1] == 0) {
                    result += "0)";
                } else if (trigFactor[i][1] == 1) {
                    result += "x)";
                } else if (trigFactor[i][1] == -1) {
                    result += "-x)";
                } else {
                    result += trigFactor[i][1] + "x)";
                }
            }
        } 
        if (result.length() == 0) {
            result = "0";
        }
        return result;
    } 

    //This method would return function slope=rise/run using a deltaX of  0.0000000001 symmetrically about X. This method needs double X as the parameter.
    // (i.e. let run be defined by: X - 0.00000000005 to X + 0.00000000005)
    public double slope(double X) {
        return (evaluate(X + 0.00000000005) - evaluate(X - 0.00000000005)) / 0.0000000001;    
    } 

    //This method would return the integral value of the function between x value interval, start to end. This method needs double start and double end as the parameter.
    // Use 10 million vertical, trapezoidal slices to determine integral value.
    // If start is greater than end, return the negative value of the integration end to start.
    public double integral(double start, double end) {
        double h = Math.abs((end - start) / 10000000);
        double sum = 0;
        double result = 0;
        if (start <= end) {
            while (start <= end) {
                sum += evaluate(start + h);
                start += h;
            }
            result = (evaluate(start) + 2 * sum + evaluate(end)) * h / 2;
        } else {
            while (start > end) {
                sum += evaluate(end + h);
                end += h;
            }
            result = (evaluate(end) + 2 * sum + evaluate(start)) * h * (-1) / 2;     
        }
        return result;
    }
}