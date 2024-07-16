package example.day09.lombok;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Member {
    private String id;
    private String name;
    //private String address;

}
