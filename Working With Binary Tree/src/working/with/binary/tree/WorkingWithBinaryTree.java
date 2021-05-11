/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package working.with.binary.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author milan
 */
public class WorkingWithBinaryTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Node<Integer> eight = new Node<>(8);
        
        insert(eight, 3);
        insert(eight, 10);
        
        inOrder(eight);
        System.out.println("\n\n");
        
        insert(eight, 4);
         inOrder(eight);
         insert(eight, 15);
         insert(eight, 12);
         insert(eight, 5);
         insert(eight, 6);
         
         inOrder(eight);
         System.out.println();
         lookup(eight, 12);
         
         System.out.println("\n\nMinimum value: "+ minimumValue(eight));
         System.out.println("\n\nMaximum value: "+ maxminumValue(eight));
         
         printInRange(eight, 3, 10); 
         
         System.out.println("\n\n Tree is BST :" + isBinarySearchTree(eight));

        
    }
    
    public static Node<Integer> insert(Node<Integer> root, int data){
    
        if(root==null)
            return new Node<Integer>(data);
        
        if(data<=root.getData()){
            root.setLeftChild(insert(root.getLeftChild(), data));
        }else{
            root.setRightChild(insert(root.getRightChild(),data));
        }
        return root;
    }
    
    public static <T> void bredthFirst(Node<T> root){
        if(root==null)
            return;
        
        Queue<Pair<Node<T>, Integer>> queue =  new LinkedList<>();
        
        int level =0;
        queue.add(new Pair<>(root,level));
        
        while(!queue.isEmpty()){
        
            Pair<Node<T>,Integer> pair = queue.remove();
            System.out.println(pair +" -->");
            
            level = pair.getValue()+1;
            
            Node<T> leftChild = pair.getKey().getLeftChild();
            if(leftChild!=null)
                queue.add(new Pair<>(leftChild,level));
            
            Node<T> rightChild =  pair.getKey().getRightChild();
            if(rightChild!=null)
                queue.add(new Pair<>(rightChild, level));
        }
    
    }    
    public static <T> void inOrder(Node<T> root){
        if(root==null)
            return;
        
        inOrder(root.getLeftChild());
        System.out.print(root +" --> ");
        inOrder(root.getRightChild());
    
    }    
    public static Node<Integer> lookup(Node<Integer> root, int data){
        if(root==null)
            return null;
        
        if(root.getData()==data)
            return root;
        if(data < root.getData()){
            System.out.println(data + " <=" + root.getData() + " looking in the left child");
            return lookup(root.getLeftChild(), data);
        }else{
                    System.out.println(data + " >" + root.getData() + " looking in the right child");
           return  lookup(root.getRightChild(), data);
        }        
    }    
    public static Integer minimumValue(Node<Integer> root){
        int  minValue = Integer.MAX_VALUE;
        
        while(root!=null){
            minValue = root.getData();
            root= root.getLeftChild();
        }        
        return minValue;
    }
    public static Integer maxminumValue(Node<Integer> root){
        int maxValue = Integer.MIN_VALUE;
        
        while(root!=null){
            maxValue =  root.getData();
            root= root.getRightChild();        
        }
        return maxValue;
    
    }
    public static  <T> void printInRange(Node<Integer> root, int low, int high){
        if(root==null)
            return;
        if(low>=high){
            return;
        }
        
        if(low<=root.getData()){
            printInRange(root.getLeftChild(), low, high);
        }
        
        if(low<= root.getData() && root.getData()<=high){
            System.out.println(root.getData());
        }
        
        if(high> root.getData()){
            printInRange(root.getRightChild(), low, high);
        }
        
    }
    public static boolean isBinarySearchTree(Node<Integer> root){
        if(root==null)
            return true;
        if(root.getLeftChild()!=null){
            if(root.getLeftChild().getData()> root.getData()){
                return false;
            }
        }
        
        if(root.getRightChild()!=null){
            if(root.getRightChild().getData()<= root.getData()){
                return false;
            }
        }
        return isBinarySearchTree(root.getLeftChild()) && isBinarySearchTree(root.getRightChild());
    }
    
}
