package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired BoardDao boardDao;
    @Autowired MemberService memberService;
    @Autowired FileService fileService;



    // 1. 글 전체 출력
    public BoardPageDto bAllPrint(BoardPageDto pageDto){
        System.out.println("pageDto = " + pageDto);
        // 하나의 페이지당 표시할 게시물 수
        int pageBoardSize=2; // 하나의 페이지당 5개씩 표시
        if (pageDto.getPage()==0){pageDto.setPage(1);}
        int startRow=(pageDto.getPage()-1)*pageBoardSize;

        // 전체 게시물 수: 카테고리번호 별
        int totalBoardSize=boardDao.getTotalBoardSize(pageDto.getBcno());
        // totalPage: 전체 페이지수 구하기
            // 총 페이지수 계산식: 전체게시물수/페이지당게시물수
                // 총 게시물수: 23개, 페이지당 5개씩 게시물 출력, 총 페이지수: 4페이지+1 => 5페이지

        int totalPage=totalBoardSize%pageBoardSize==0?
                    totalBoardSize/pageBoardSize:
                    totalBoardSize/pageBoardSize+1;

        // 페이지별 시작 버튼 번호, 끝 버튼 번호
            /*

                페이지당 최대 버튼수 5개
                1~5페이지: [1] [2] [3] [4] [5]
                6~10페이지: [6] [7] [8] [9] [10]
                11~13페이지: [11] [12] [13]
            */
        int btnSize=5; // 페이지당 최대 버튼수
        int startBtn=((pageDto.getPage()-1)/btnSize)*btnSize+1; // 페이지별 시작 버튼 번호 변수
        int endBtn=startBtn+btnSize-1; // 페이지별 끝 버튼 번호 변수, 단 end는 최대페이지수 보다 커질 수 없다.
        if (endBtn>totalPage){endBtn=totalPage;}
        // 게시물 정보 조회
        List<BoardDto> data= boardDao.bAllPrint(startRow,pageBoardSize,pageDto.getBcno());

        // 반환 객체 구성
        BoardPageDto boardPageDto=BoardPageDto.builder()
                .page(pageDto.getPage())
                .totalBoardSize(totalBoardSize)
                .totalPage(totalPage)
                .data(data)
                .startBtn(startBtn)
                .endBtn(endBtn)
                .build();
        return boardPageDto;


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
