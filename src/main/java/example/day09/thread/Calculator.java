package example.day09.thread;

public class Calculator {
    int memory;

    public  void setMemory(int memory){
        System.out.println("memory = " + memory);
        this.memory=memory;
        try {
            Thread.sleep(2000);
        }catch (Exception e){}
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.memory);
    }
}
