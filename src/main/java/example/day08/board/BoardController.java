package example.day08.board;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/example/board")
public class BoardController {


    @PostMapping("/post")
    public boolean post(String content,String title,String bpw){
        System.out.println("post");
        boolean result=BoardDao.getInstance().post(content,title,bpw);
        return result;
    }

    @PutMapping("/update")
    public boolean update(String bpw,String content,int bno){

        System.out.println("update");
        return BoardDao.getInstance().update(bpw,content,bno);
    }


    @GetMapping("/read")
    public ArrayList<BoardDto> read(){
        ArrayList<BoardDto> result = BoardDao.getInstance().read();
        return result;
    }



    @DeleteMapping("/delete")
    public boolean delete(int bno){
        boolean result = BoardDao.getInstance().delete(bno);
        return result;
    }







}
