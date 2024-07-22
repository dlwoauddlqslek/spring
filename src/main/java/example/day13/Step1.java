package example.day13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Step1 {
    public static void main(String[] args) {
        Stack<Integer> coinBox=new Stack<>();
        coinBox.push(100);
        coinBox.push(80);
        coinBox.push(60);
        coinBox.push(25);

        int topData=coinBox.peek();
        System.out.println("topData = " + topData);

        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);

        Queue<Integer> pointBox=new LinkedList<>();
        pointBox.offer(100);
        pointBox.offer(50);
        pointBox.offer(40);
        pointBox.offer(30);
        System.out.println("pointBox = " + pointBox);
        int frontData= pointBox.peek();
        System.out.println("frontData = " + frontData);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
    }
}
