package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@Component
public class MemberDao extends Dao {
    // 1. 회원가입
    public boolean mSignup( MemberDto memberDto ){
        System.out.println("MemberDao.mSignup");
        System.out.println("memberDto = " + memberDto);
        try{
            String sql = "insert into member( id , pw , name , email , phone ) values( ? , ? , ? , ? , ? )";
            PreparedStatement ps = super.conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ps.setString( 3 , memberDto.getName() );
            ps.setString( 4 , memberDto.getEmail() );
            ps.setString( 5 , memberDto.getPhone() );
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println("e = " + e);     }
        return false;
    }
    // 2. 로그인 : 로그인 성공한 회원번호 반환( 세션에 저장할려고 )
    public int mLogin( MemberDto memberDto ){
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);
        try{String sql = "select * from member where id = ? and pw =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getId() );
            ps.setString( 2 , memberDto.getPw() );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt("no"); }
        }catch (Exception e ){ System.out.println(e);   }
        return 0; // 0 은 회원번호가 없다 뜻
    }
    // 5. 마이페이지 정보
    public MemberDto mMyInfo( int loginMno ){
        try{ String sql ="select * from member where no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , loginMno );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return MemberDto.builder()
                        .no( rs.getInt("no") )
                        .id( rs.getString("id"))
                        .phone( rs.getString("phone"))
                        .email( rs.getString("email"))
                        .name( rs.getString("name"))
                        .build();
            }
        }catch (Exception e ){  System.out.println(e);  } return null;
    }


    public boolean idCheck(String id){
        System.out.println("MemberDao.idCheck");
        try { String sql="select * from member where binary(id)=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    public boolean mLeave(int no,String pw){
        try {String sql="delete from member where no=? and pw=?;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,no);
            ps.setString(2,pw);
            int count =ps.executeUpdate();
            if (count==1){return true;}

        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public boolean mUpdate(Map<String,String>map){
        try {String sql="update member set name=?,pw=?,phone=? where no=? and pw=?; ";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, map.get("name"));
            ps.setString(2, map.get("newPw"));
            ps.setString(3, map.get("phone"));
            ps.setInt(4, Integer.parseInt(map.get("no")));
            ps.setString(5, map.get("pw"));
            int count=ps.executeUpdate();
            if (count==1){return true;}

        }catch (Exception e){System.out.println(e);}
        return false;
    }
}





