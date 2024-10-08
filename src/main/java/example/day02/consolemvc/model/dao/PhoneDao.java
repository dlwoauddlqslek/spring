package example.day02.consolemvc.model.dao;

import example.day02.consolemvc.model.dto.PhoneDto;
import example.day02.consolemvc.view.PhoneView;

import java.sql.*;
import java.util.ArrayList;

public class PhoneDao {
    private static PhoneDao phoneDao=new PhoneDao();
    private PhoneDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/springexample","root","1234"
            );

        }catch (Exception e){System.out.println(e);}
    }
    public static PhoneDao getInstance(){
        return phoneDao;
    }

    //[2] JDBC 인터페이스
    Connection conn; PreparedStatement ps; ResultSet rs;

    public boolean postPhone(PhoneDto phoneDto){
        try{
            String sql="insert into phonebook(name,phone) values(?,?);";
            ps=conn.prepareStatement(sql);
            ps.setString(1, phoneDto.getName());
            ps.setString(2, phoneDto.getPhone());
            int count=ps.executeUpdate();
            if(count==1){return true;}

        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public ArrayList<PhoneDto> getPhone(){
        ArrayList<PhoneDto> list=new ArrayList<>();
        try {
            String sql="select * from phonebook;";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int id=rs.getInt(1); // 첫번째 필드순서번호 또는 필드명
                String name=rs.getString(2);
                String phone=rs.getString(3);
                PhoneDto phoneDto=new PhoneDto(id,name,phone);
                list.add(phoneDto);
            }

        }catch (Exception e){System.out.println(e);}
        return list;
    }
}
