package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder // 해당 클래스의 빌더 패턴 주입/생성
@AllArgsConstructor // 해당 클래스의 풀 생성자 를 자동으로 주입/생성
@NoArgsConstructor // 해당 클래스의 빈/기본 생성자 를 자동으로 주입/생성
@ToString // 해당 클래스의 toString 메소드를 자동으로 재정의
@Getter // 해당 클래스의 getter 메소드를 자동으로 주입/생성
@Setter // 해당 클래스의 setter 메소드를 자동으로 주입/생성
public class ProductDto {
    // 여러개 첨부파일 필드
    private List<MultipartFile> files;
    // 여러개 파일명 필드
    private List<String> fileNames;
    // 제품 정보
    private String pName;
    private int pPrice;
    private String pDesc;
    private int pno;
    private String pdate;
    private int pview;
}
