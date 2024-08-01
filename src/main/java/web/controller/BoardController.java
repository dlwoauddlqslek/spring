package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.ArrayList;

@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    // 1. 글 전체 출력
    @GetMapping("/all")
    public BoardPageDto bAllPrint(BoardPageDto pageDto){
        // 1. page : 페이징 처리 에서 사용할 현재 페이지번호
        // 2. bcno : 현재 선택된 카테고리 번호
        // 3. searchKey : 검색 조회시 사용되는 필드명
        // 4. searchKeyword : 검색 조회시 사용되는 필드의 값

        return boardService.bAllPrint(pageDto);
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    @GetMapping("/getcategory")
    public ArrayList<BoardDto> getBoardCategory(){
        return boardService.getBoardCategory();
    }

    // 3. 글 쓰기
    @PostMapping("/write")
    public boolean bWrite( BoardDto boardDto){
        System.out.println("BoardController.bWrite");
        System.out.println("boardDto = " + boardDto);
        return boardService.bWrite(boardDto);
    }

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        return boardService.bRead(bno);
    }

    // 5. 글 수정
    @PutMapping("/edit")
    public boolean bEdit(BoardDto boardDto){return boardService.bEdit(boardDto);}

    // 6. 글 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){return boardService.bDelete(bno);}


}   // class end
