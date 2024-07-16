package example.day09.thread;

public class Step2 {
    public static void main(String[] args) {
        Calculator mycal=new Calculator();

        User1 user1=new User1();
            user1.setName("user1 thread");
            user1.calculator=mycal;
            user1.value=100;
            user1.start();

            User1 user2=new User1();
            user2.setName("user2 thread");
            user2.calculator=mycal;
            user2.value=200;
            user2.start();

    }
}
