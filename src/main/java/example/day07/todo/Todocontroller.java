package example.day07.todo;

import example.day07.restcontroller.RestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/example")
public class Todocontroller {

//    private static Todocontroller todocontroller =new Todocontroller();
//    public static Todocontroller getInstance(){return todocontroller;}



    @PostMapping("/todo1")
    public boolean post( @RequestParam("key")String content){
        System.out.println("post");

        boolean result =TodoDao.getInstance().post(content);
        return result;
    }

    @GetMapping("/todo2")
    public ArrayList<TodoDto> get(){
        System.out.println("get");

        ArrayList<TodoDto> result=TodoDao.getInstance().get();
        return result;
    }

    @PutMapping("/todo3")
    public boolean put( @RequestParam("key")int memberCode){
        System.out.println("put");
        boolean result =TodoDao.getInstance().put(memberCode);
        return result;
    }

    @DeleteMapping("/todo4")
    public boolean delete(@RequestParam("key")int memberCode){
        System.out.println("delete");

        boolean result=TodoDao.getInstance().delete(memberCode);
        return result;
    }


}
