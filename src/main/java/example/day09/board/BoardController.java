package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/example/board")
public class BoardController {
@Autowired BoardService boardService;

    @PostMapping("/post")
    public boolean post(String content,String title,String bpw){
        System.out.println("post");
        boolean result= boardService.post(content,title,bpw);
        return result;
    }

    @PutMapping("/update")
    public boolean update(String bpw,String content,int bno){

        System.out.println("update");
        return boardService.update(bpw,content,bno);
    }


    @GetMapping("/read")
    public ArrayList<BoardDto> read(){
        ArrayList<BoardDto> result = boardService.read();
        return result;
    }

    @GetMapping("/detail")
    public BoardDto detail(int bno){
        return boardService.detail(bno);
    }


    @DeleteMapping("/delete")
    public boolean delete(int bno,String bpw){
        boolean result = boardService.delete(bno,bpw);
        return result;
    }







}
