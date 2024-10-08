package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/example")
public class Todocontroller {

//    private static Todocontroller todocontroller =new Todocontroller();
//    public static Todocontroller getInstance(){return todocontroller;}
    @Autowired TodoService todoService;


    @PostMapping("/todo1")
    public boolean post( @RequestParam("key")String content){
        System.out.println("post");

        boolean result = todoService.post(content);
        return result;
    }

    @GetMapping("/todo2")
    public ArrayList<TodoDto> get(){
        System.out.println("get");

        ArrayList<TodoDto> result= todoService.get();
        return result;
    }

    @PutMapping("/todo3")
    public boolean put( @RequestParam("key")int memberCode){
        System.out.println("put");
        boolean result = todoService.put(memberCode);
        return result;
    }

    @DeleteMapping("/todo4")
    public boolean delete(@RequestParam("key")int memberCode){
        System.out.println("delete");

        boolean result= todoService.delete(memberCode);
        return result;
    }


}
