package web.model.dao;

import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class BoardDao extends Dao{

    private PreparedStatement ps; private ResultSet rs;
    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select *from board inner join member where board.no = member.no";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .build();
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // bAllPrint() end

    public ArrayList<BoardDto> getBoardCategory() {
        try{
            ArrayList<BoardDto> list = new ArrayList<>();
            String sql = "select bcno, bcname from bcategory;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                BoardDto dto = BoardDto.builder()
                        .bcno(rs.getInt(1))
                        .bcname(rs.getString(2))
                        .build();
                list.add(dto);
            }
            return list;
        }catch (Exception e){
            System.out.println("getBoardCategory()" + e);
        }
        return null;
    }

    // 글 쓰기
    public boolean bWrite(BoardDto boardDto){
        try {
            String sql="insert into board(btitle,bcontent,no,bcno,bfile) values(?,?,?,?,?);";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getNo());
            ps.setLong(4,boardDto.getBcno());
            ps.setString(5,boardDto.getBfile());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
            return true;
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        try{
            String sql = "select * from board inner join member on board.no = member.no inner join bcategory on board.bcno = bcategory.bcno where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bcname(rs.getString("bcname"))
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .bfile(rs.getString("bfile"))
                        .build();
                return boardDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 5. 글 수정
    public boolean bEdit(BoardDto boardDto){
        try {
            System.out.println("boardDto = " + boardDto);
            String sql="update board set btitle=?, bcontent=?, bcno=? where bno=? and no=?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setLong(4,boardDto.getBno());
            ps.setLong(5,boardDto.getNo());
            int count= ps.executeUpdate();
            if (count==1){return true;}

        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 6. 글 삭제
    public boolean bDelete(int bno,int loginMno){
        try {
            String sql="delete from board where bno=? and no=?;";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.setInt(2,loginMno);
            int count= ps.executeUpdate();
            if (count==1){return true;}
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

}   // class end
