/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Ashish Patel 
 * CS 1181-section05 
 * Instructor: R.Volkers 
 * TA: Ryan Brant
 */
public class ExpTree {

    private String postFix;
    private TreeNode root;

    /**
     * This class contains a TreeNode constructor to create a node with left and
     * right reference. 
     */
    private class TreeNode {

        String operator;
        TreeNode leftNode;
        TreeNode rightNode;

        /**
         * This is a Constructor which creates a node with left and right refernce.
         * @param operator is a parent node 
         * @param left is a left child of a parent 
         * @param right is a right child of a parent
         */
        public TreeNode(String operator, TreeNode left, TreeNode right) {

            this.operator = operator;
            this.leftNode = left;
            this.rightNode = right;
        }

    }

    /**
     * This is a constructor which creates a string. 
     * @param postFixExp contains string
     */
    public ExpTree(String postFixExp) {

        if (postFixExp == null) {
            throw new NullPointerException("Expression is null");
        } else {
            this.postFix = postFixExp;
        }

        //nodes to create an expression tree. 
        TreeNode newNode;
        TreeNode leftnode;
        TreeNode rightnode;
        String token;
        
        //scanner to read each character of a string.
        Scanner input = new Scanner(postFixExp);
        
        //Creates a stack to store a values.
        Stack<TreeNode> pointer = new Stack<TreeNode>();

        //loop to go through each character
        while (input.hasNext()) {

            //store read value in a token named variable.
            token = input.next();
            
            //Checks if a value is operand or a operator
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {

                //Remove right child
                rightnode = pointer.pop();
                
                //remove left child
                leftnode = pointer.pop();
                
                //Creates a node with left and right child
                newNode = new TreeNode(token, leftnode, rightnode);
                
                //push a node into pointer named stack
                pointer.push(newNode);  
            } 
            //creates a node without children
            else {
                
                pointer.push(new TreeNode(token, null, null));
            }
        }
        
        //remove the last element and store it in a root
        root = pointer.pop();
    }

    /**
     * This is a getter method which invokes a another method and return it.
     * @return ExpTreeHeight method
     */
    public int getExpTreeHeight(){
        return ExpTreeHeight(root);
    }
    /**
     * This method determine the height of a binary expression tree.
     * @param node contains root node
     * @return maximum height of a binary expression tree
     */
    public int ExpTreeHeight(TreeNode node) {

        int maxHeight = 0;

        if (node == null) {
            return 0;
        } else {
            
            //recursively checks maximum height between left and right child
            maxHeight = Math.max(ExpTreeHeight(node.leftNode), (ExpTreeHeight(node.rightNode)));
        }
        return maxHeight + 1;
    }

    /**
     * This method called a postFixTraversal and passed a root node
     * @return String of Expression
     */
    public String getPostFixTraversal(){

        //Creates a string builder object to store string
        StringBuilder sb = new StringBuilder();
        
        //called a method to create post fix traversal
        postFixTraversal(root, sb);
        
        //return string contains with expression
        return sb.toString();
    }
    /**
     * This method Creates a post fix expression
     * @param node contains root node
     */
    public void postFixTraversal(TreeNode node, StringBuilder sb) {
        if (node != null) {

            //recursively passes left and right node
            postFixTraversal(node.leftNode,sb);
            postFixTraversal(node.rightNode,sb);
            sb.append(node.operator + " ");
        }

    }

    /**
     * This method calls a recursivelyEvaluate method to find the answer.
     * @return answer of a postfix expression.
     */
    public double arithmeticEvaluation() {

        if (root == null) {
            return 0;
        } else {

            //called a recursivelyEvaluate method and store it in a value.
            double value = recursivelyEvaluate(root);
            return value;
        }
        

    }

    /**
     * This method finds the answer of a postfix expression 
     * @param node contains root node
     * @return answer of a postfix expression
     */
    private double recursivelyEvaluate(TreeNode node) {

        if (node.leftNode == null && node.rightNode == null) {
            return Double.parseDouble(node.operator);
        } else {
            
            //recursilvery called method for left child and store it in a left 
            //named variable
            double left = recursivelyEvaluate(node.leftNode);
            
            //recursilvery called method for left child and store it in a left 
            //named variable
            double right = recursivelyEvaluate(node.rightNode);
            
            //checks  the operator is a divison 
            if (node.operator.equals("/")) {
                return left / right;  
            } 
            //checks the operator is a multiplication operator or not
            else if (node.operator.equals("*")) {
                return left * right;
            } 
            //checks the operator is a add operator or not
            else if (node.operator.equals("+")) {
                return left + right;
            } 
            else  {
                return left - right;
            } 

        }
    }

    /**
     * This method simply prints a binary expression tree level by level.
     */
    public void displayTree() {

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        TreeNode temp = root;
        int level = 1;
        int nodesInCurrentLevel = 1;
        int nodesInNextLevel = 0;
        queue.add(temp);
        sb.append("Level "+level+": ");
        
        while (!queue.isEmpty()){
            TreeNode currentNode = queue.poll();
            nodesInCurrentLevel--;
          
            sb.append(currentNode.operator + " ");
           
            if (currentNode.leftNode != null) {
                queue.add(currentNode.leftNode);
                nodesInNextLevel++;
            }
           
           
            
            if (currentNode.rightNode != null) {
                queue.add(currentNode.rightNode);
                nodesInNextLevel++;
            }
            
            
            
           		//-1.11 2.22 + 4.44 -
            
            if (nodesInCurrentLevel == 0 && !queue.isEmpty()) {
                level++;
                nodesInCurrentLevel = nodesInNextLevel;
                sb.append("\nLevel "+level+": ");
                nodesInNextLevel = 0;
            }
        }
       
        
        System.out.print(sb.toString());
        

    }

}
