package web.model.dto;

import lombok.*;

@Builder // 해당 클래스의 빌더 패턴 주입/생성
@AllArgsConstructor // 해당 클래스의 풀 생성자 를 자동으로 주입/생성
@NoArgsConstructor // 해당 클래스의 빈/기본 생성자 를 자동으로 주입/생성
@ToString // 해당 클래스의 toString 메소드를 자동으로 재정의
@Getter // 해당 클래스의 getter 메소드를 자동으로 주입/생성
@Setter // 해당 클래스의 setter 메소드를 자동으로 주입/생성
public class MemberDto {
    private int no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
}
