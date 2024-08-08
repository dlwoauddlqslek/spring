package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends Dao{
    // 제품, 이미지 등록
    public boolean pRegister(ProductDto productDto){
        System.out.println("ProductDao.pRegister");
        System.out.println("productDto = " + productDto);

        // 각 테이블에 따른 DTO 정보를 각 INSERT 한다.
        try {
            // 제품 등록
            String sql="insert into product(pname,pdesc,pprice) values(?,?,?);";
                // JDBC 에서 insert한 레코드의 자동번호가 부여된 pk번호를 반환하는 방법
                    // 1. conn.prepareStatement(sql, Statement,RETURN_GENERATED_KEYS)
                    // 2.
            PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,productDto.getPName());
            ps.setString(2, productDto.getPDesc());
            ps.setInt(3,productDto.getPPrice());
            int count=ps.executeUpdate();
            if (count==1) { // 등록된 레코드 1개이면(제품등록 성공)
                // 제품 이미지 등록
                ResultSet pkRs = ps.getGeneratedKeys(); // 생성된 pk번호들을 ResultSet에 반환한다.
                if (pkRs.next()) {
                    int pno=pkRs.getInt(1);
                    System.out.println("pno = " + pno);
                    // 이미지 등록
                    productDto.getFileNames().forEach(fileName-> {
                        try {
                            String sql2 = "insert into productimg(pno,pimgname) values(?,?);";
                            PreparedStatement ps2 = conn.prepareStatement(sql2);
                            ps2.setInt(1, pno);
                            ps2.setString(2, fileName);
                            ps2.executeUpdate();
                        }catch (Exception e){System.out.println("e = " + e);}
                    });
                }
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return true;
    }

    // 제품목록 전체 출력(1개: dto, 여러개: list<Dto>
    public List<ProductDto> getProductAll(){
        System.out.println("ProductDao.pPrint");
        List<ProductDto> list=new ArrayList<>();
        try {// 1. 제품 조회
            String sql="select * from product";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                // 제품 객체 생성
                ProductDto productDto=ProductDto.builder()
                        .pName(rs.getString("pname"))
                        .pDesc(rs.getString("pdesc"))
                        .pPrice(rs.getInt("pprice"))
                        .pno(rs.getInt("pno"))
                        .pdate(rs.getString("pdate"))
                        .pview(rs.getInt("pview"))
                        .build();
                // 2. 제품의 모든 이미지 조회: fileNames
                List<String> fileNames=new ArrayList<>();
                    // 해당 제품의 모든 이미지 조회
                    String sql2="select * from productimg where pno=?;";
                    PreparedStatement ps2=conn.prepareStatement(sql2);
                    ps2.setInt(1,rs.getInt("pno"));
                    ResultSet rs2=ps2.executeQuery();
                    while (rs2.next()){
                        fileNames.add(rs2.getString("pimgname"));
                    }
                    // 조회한 모든 이미지를 Dto에 담기
                    productDto.setFileNames(fileNames);
                    // 제품 객체를 리스트에 담기
                    list.add(productDto);
            } // while e
        }catch (Exception e){System.out.println("e = " + e);}
        return list; // 제품 리스트 반환
    }
}
