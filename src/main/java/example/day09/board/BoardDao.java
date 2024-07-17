package example.day09.board;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@Component
public class BoardDao {


    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private BoardDao() {
        try {   // DB 연동
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Board", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean post(String content,String title,String bpw){
        try {
            String sql="insert into board(content,title,bpw) values(?,?,?);";
            ps=conn.prepareStatement(sql);
            ps.setString(1,content);
            ps.setString(2,title);
            ps.setString(3,bpw);
            int count=ps.executeUpdate();
            if (count==1){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }return false;
    }

    public boolean update(String bpw,String content,int bno){
        try {
            System.out.println(bno);
            String sql="select * from board where bno=?;";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,bno);
            rs= ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString(3));
                if (bpw.equals(rs.getString(3))){
                    String sql2="update board set content=? where bno=?;";
                    ps=conn.prepareStatement(sql2);
                    ps.setString(1,content);
                    ps.setInt(2,bno);
                    int count=ps.executeUpdate();
                    if (count==1){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }return false;
    }


    // 전체 조회 함수
    public ArrayList<BoardDto> read(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt("bno"))
                        .title(rs.getString("title"))
                        .date(rs.getString("date"))
                        .view(rs.getInt("view"))
                                .build();
                list.add( boardDto );
            } //while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    public BoardDto detail(int bno){

        try {
            String sql="select * from board where bno=?;";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,bno);
            rs=ps.executeQuery();
            if (rs.next()){
                BoardDto boardDto=new BoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                return boardDto;

            }
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    //삭제 함수
    public boolean delete(int bno,String bpw){
        try {
            System.out.println(bno);
            String sql="select * from board where bno=?;";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,bno);
            rs= ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString(3));
                if (bpw.equals(rs.getString(3))){
                    String sql2="delete from board where bno=?;";
                    ps=conn.prepareStatement(sql2);
                    ps.setInt(1,bno);
                    int count=ps.executeUpdate();
                    if (count==1){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }return false;
    }






}
