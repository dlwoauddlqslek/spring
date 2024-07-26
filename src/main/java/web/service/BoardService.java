package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;
    @Autowired FileService fileService;



    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        return boardDao.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        return boardDao.getBoardCategory();
    }

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto){

        MemberDto loginDto=memberService.mLoginCheck();

        if (loginDto == null){
            return false;
        } else {
            int loginMno=loginDto.getNo();
            boardDto.setNo(loginMno);
            if (!boardDto.getUploadFile().isEmpty()) {
                String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
                if (uploadFileName == null) return false;
                boardDto.setBfile(uploadFileName);
            }
            return boardDao.bWrite(boardDto);}
    }

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        return boardDao.bRead(bno);
    }

    // 5. 글 수정
    public boolean bEdit(BoardDto boardDto) {
        MemberDto loginDto = memberService.mLoginCheck();
        if (loginDto == null) {
            return false;
        } else {
            int loginMno = loginDto.getNo();
            boardDto.setNo(loginMno);
            return boardDao.bEdit(boardDto);
        }
    }
    // 6. 글 삭제
    public boolean bDelete(int bno){
        MemberDto loginDto=memberService.mLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto==null){
            return false;
        }else{
            int loginMno= loginDto.getNo();
            return boardDao.bDelete(bno,loginMno);
        }
    }
}   // class end
