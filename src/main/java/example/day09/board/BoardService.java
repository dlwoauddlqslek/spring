package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
@Service
public class BoardService {
    @Autowired BoardDao boardDao;

    public boolean post(String content,String title,String bpw){
        System.out.println("post");
        boolean result= boardDao.post(content,title,bpw);
        return result;
    }


    public boolean update(String bpw,String content,int bno){

        System.out.println("update");
        return boardDao.update(bpw,content,bno);
    }



    public ArrayList<BoardDto> read(){
        ArrayList<BoardDto> result = boardDao.read();
        return result;
    }


    public BoardDto detail(int bno){
        return boardDao.detail(bno);
    }



    public boolean delete(int bno,String bpw){
        boolean result = boardDao.delete(bno,bpw);
        return result;
    }
}
