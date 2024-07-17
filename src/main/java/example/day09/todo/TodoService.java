package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@Service
public class TodoService {
    @Autowired TodoDao todoDao;
    public boolean post( @RequestParam("key")String content){
        System.out.println("post");

        boolean result = todoDao.post(content);
        return result;
    }


    public ArrayList<TodoDto> get(){
        System.out.println("get");

        ArrayList<TodoDto> result= todoDao.get();
        return result;
    }


    public boolean put( @RequestParam("key")int memberCode){
        System.out.println("put");
        boolean result = todoDao.put(memberCode);
        return result;
    }


    public boolean delete(@RequestParam("key")int memberCode){
        System.out.println("delete");

        boolean result= todoDao.delete(memberCode);
        return result;
    }
}
