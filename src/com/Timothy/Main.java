package com.Timothy;
import java.util.Stack;
public class Main {
    //Method to convert expression from inFix to PostFix notation.
    static int Precedence(Character operand) {
        //Please excuse my dear aunt sally (PENDAS)
        //Parenthesis -> exponents -> division/multiplication -> addition/subtration
        //Higher number gets higher precedence
        switch(operand) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        //Whatever was passed in is not an operand.
        return -1;
    }

    /*
        Algorithm
        1. Scan the infix expression from left to right.
        2. If the scanned character is an operand, output it.
           recedence of the operator residing on the top of the stack. Push the scanned operator to the stack.
        3. If the scanned character is an ‘(‘, push it to the stack.
        4. else if stk.peep == ')',
               4.1 while stack is not empty and an ‘(‘ is not encountered
                pop and append top of stack to result String.  (Pop returns something in Java)
        5. If we stk.peek == '(' and stk is not empty, the expression is invalid.
           else
                5.1 pop the stack
        6. Else,
        …..6.1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty), push it.
        …..6.2 Else, Pop the operator from the stack until the precedence of the scanned operator is less-equal to the p
        7. Pop and output from the stack until it is not empty.
    */
    static String inFixToPostFix(String expression) {
        //Result string that will hold the PostFix String.
        String res = new String();

        //Stack used to evaluate
        Stack<Character> stk = new Stack<>();

        //Scan infix expression from left to right.
        for(int i = 0; i < expression.length(); i++) {
            //save current char at index i.
            char currChar = expression.charAt(i);
            //If the scanned character is an operand, append to result String.
            if(Character.isLetterOrDigit(currChar)) {
                //Append to result String.
                res+=currChar;
            }
            else if(currChar == '(') {
                stk.push(currChar);
            }
            else if(currChar == ')') {
                while(!stk.empty() && stk.peek() != '(') {
                    res+=stk.pop();
                }
                if(!stk.empty() && stk.peek() != '(') {
                   return ("Invalid expression");
                }
                else
                    stk.pop();
            }
            //Check to see which operand to process, based on its precedence
            else {
                    while(!stk.empty() && Precedence(currChar) <= Precedence(stk.peek())) {
                       res+= stk.pop();
                    }
                    stk.push(currChar);
                }
        }
        while(!stk.empty()) {
            res +=stk.pop();
        }
        return res;
    }
    public static void main(String[] args) {
        String s =  "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(inFixToPostFix(s));
    }
}
