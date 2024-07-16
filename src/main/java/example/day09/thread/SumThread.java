package example.day09.thread;

public class SumThread extends Thread{

    public int sum;

    @Override
    public void run(){
        for (int i=1; i<=100;i++){
            sum+=i;
        }
    }
}
