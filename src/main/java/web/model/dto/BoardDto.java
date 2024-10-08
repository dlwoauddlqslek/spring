package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class BoardDto {
    private long bno;           // 번호
    private String btitle;      // 제목
    private String bcontent;    // 내용
    // 업로드용
    private MultipartFile uploadFile;       // 첨부파일
    // db에 저장/출력할 업로드된 파일명
    private String bfile;
    private long bview;         // 조회수
    private String bdate;       // 작성일
    // 카테고리
    private long bcno;          // 카테고리 번호
    private String bcname;        // 카테고리 이름
    // 회원
    private long no;            // 작성자 번호
    private String id;          // 작성자 아이디
}
