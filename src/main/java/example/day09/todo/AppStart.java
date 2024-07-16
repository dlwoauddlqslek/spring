package example.day09.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
//    public static void main(String[] args) {
//
//        Todocontroller.getInstance().post("자바공부");
//        ArrayList<TodoDto> result=Todocontroller.getInstance().get();
//        System.out.println("result = " + result);
//        Todocontroller.getInstance().put(3);
//        Todocontroller.getInstance().delete(2);
//    }

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
