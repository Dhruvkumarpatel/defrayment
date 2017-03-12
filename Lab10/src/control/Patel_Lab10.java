/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Scanner;

/**
 * @author Ashish Patel 
 * CS 1181-section05 
 * Instructor: R.Volkers 
 * TA: Ryan Brant
 */
public class Patel_Lab10 {

    /**
     * The main method creates a object of ExpTree class and test all methods 
     * thoroughly.
     */
    public static void main(String[] args) {

        //creates a scanner object to get exression from user.
        Scanner input = new Scanner(System.in);
        
        //get input from user and store it in a answer named variable
        System.out.println("Please enter post fix expression to get answer :");
        String answer = input.nextLine(); 
        System.out.println("The original Expression is: " +answer);

        ExpTree ExpressionTree = new ExpTree(answer);
       
        double value = ExpressionTree.arithmeticEvaluation();
        System.out.println("The value of this exptression tree is " + value);
        System.out.println("The height of the tree is " + ExpressionTree.getExpTreeHeight());
        System.out.println("The Postfix method returned: " + ExpressionTree.getPostFixTraversal());
        System.out.println("Here is the expression tree:" );
        
        ExpressionTree.displayTree();
      
    }

}
