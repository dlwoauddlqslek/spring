package example.day09.thread;

public class WorkThread extends Thread {

    public boolean work=true;

    @Override
    public void run() {
        while (true){

            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
            if (work){
                System.out.println(getName());
            }else{Thread.yield();}
        }
    }
}
