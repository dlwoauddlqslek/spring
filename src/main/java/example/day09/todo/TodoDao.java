package example.day09.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TodoDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public TodoDao() {
        try {   // DB 연동
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoList", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static TodoDao todoDao=new TodoDao();
    public static TodoDao getInstance(){return todoDao;}




    public boolean post(String content){
        try {
            String sql = "insert into member(content) values(?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }

    public ArrayList<TodoDto> get() {
        ArrayList<TodoDto> todoList = new ArrayList<>();
        try {
            String sql = "select * from member;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TodoDto todoDto = new TodoDto();
                todoDto.setMemberCode(rs.getInt(1));
                todoDto.setContent(rs.getString(2));
                todoDto.setColor(rs.getInt(3));
                todoList.add(todoDto);
            }
            return todoList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return todoList;
    }

    public boolean put(int memberCode) {
        try {
            String sql2 = "select * from member where memberCode = ? ";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, memberCode);
            rs = ps.executeQuery();
            if( rs.next() ){
                int color = rs.getInt("color");
                color = color == 0 ? 1 : 0;


                String sql = "update member set color = ?  where memberCode =?;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, color );
                ps.setInt(2, memberCode);
                int count = ps.executeUpdate();
                if (count == 1) {
                    return true;
                }

            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }




    public boolean delete(int memberCode) {
        try {
            String sql = "delete from member where memberCode = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, memberCode);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }


}
