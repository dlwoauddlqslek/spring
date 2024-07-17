package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDao extends Dao{
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean signUp(MemberDto memberDto){
        System.out.println("MemberDao.signUp");
        System.out.println("memberDto = " + memberDto);
        try {
            String sql="insert into member(id,pw,name,email,phone) values(?,?,?,?,?);";
            ps=conn.prepareStatement(sql);
            ps.setString(1,memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            int count=ps.executeUpdate();
            if (count==1){return true;}
        }catch (Exception e){System.out.println(e);}
        return false;
    }


}
