package web.model.dao;

import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class BoardDao extends Dao{

    private PreparedStatement ps; private ResultSet rs;
    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(int startRow,int pageBoardSize,int bcno,String searchKey , String searchKeyword){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * " +                   // 조회
                    " from board inner join member " +  // 조인 테이블
                    " on board.no = member.no ";       // 조인 조건
                    if (bcno>=1){sql+= " where bcno= "+bcno;}                 // 일반 조건
                    // 전체보기는 where절 생략,bcno=0
                    // 카테고리별은 where절 삽입,bcno>=1
            // 4. 일반 조건2
            if( searchKeyword.isEmpty() ){ }
            else{
                if( bcno >=1 ){ sql += " and "; }
                else{ sql += " where "; }
                sql += searchKey + " like '%"+searchKeyword+"%'";
            }
                    sql +=" order by board.bno desc " +       // 정렬조건, 내림차수
                    " limit ?,?;";                      //레코드 제한
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
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

    // 전체 게시물수 반환 처리
    public int getTotalBoardSize(int bcno,String searchKey , String searchKeyword){
        try {
            String sql ="select count(*) as 총게시물수 " +
                    " from board inner join member " +
                    " on board.no = member.no ";

            // 카테고리가 1 이상이면 카테고리 존재(pk번호)
            if( bcno >= 1 ){ sql += " where bcno = "+bcno; } // 1. 전체보기 : select count(*) as 총게시물수 from board  // 2. 카테고리 보기 : select count(*) as 총게시물수 from board where bcno = 숫자
            // 검색이 존재 했을때 , keyword가 존재하면
            if( searchKeyword.isEmpty() ){} // 문자열이 비어 있으면 , 검색이 없다라는 의미의 뜻 으로 활용
            else{  // 비어있지 않으면 , 검색이 있다라는 의미의 뜻 으로 활용
                // - 카테고리가 있을때는 and 추가
                if( bcno >= 1 ) { sql += " and "; }
                // - 카테고리가 없을때[전체보기]는 where 추가
                else { sql += " where "; }
                // 검색 sql
                sql += searchKey +" like '%"+searchKeyword+"%' ";
            }
            System.out.println("sql = " + sql);
            //

            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1); // 총 게시물수
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return 0;
    }

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

    // 7. 조회수 증가 처리
    public boolean bViewIncrease( int bno ){
        try{
            String sql ="update board set bview = bview + 1 where bno =? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , bno );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println(e); }
        return false;
    } // 5 f end

    // 8. 댓글 작성
    public boolean bReplyWrite(Map<String ,String> map){
        try {
            String sql="insert into breply(brindex,brcontent,no,bno) values(?,?,?,?);";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(map.get("brindex"))); //String 타입이므로 강제로 형변환
            ps.setString(2,map.get("brcontent"));
            ps.setInt(3,Integer.parseInt(map.get("no")));
            ps.setInt(4,Integer.parseInt(map.get("bno")));
            int count=ps.executeUpdate();
            if (count==1)return true;
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 9. 댓글 출력
    public ArrayList<Map<String ,String>>  bReplyPrint(int bno){
        System.out.println("BoardDao.bReplyPrint");
        System.out.println("bno = " + bno);

        ArrayList<Map<String ,String>> list2=new ArrayList<>();
        try {
            String sql="select * from breply where bno=?;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){Map<String,String> list=new HashMap<>();
                list.put("brno",String.valueOf(rs.getInt(1)));
                list.put("brindex",String.valueOf(rs.getInt(2)));
                list.put("brcontent",rs.getString(3));
                list.put("brdate",rs.getString(4));
                list.put("no",String.valueOf(rs.getInt(5)));
                list.put("bno",String.valueOf(rs.getInt(6)));
                list2.add(list);
            }
            
        }catch (Exception e){System.out.println("e = " + e);}
        return list2;
    }

}   // class end
