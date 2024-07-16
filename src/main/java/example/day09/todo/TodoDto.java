package example.day09.todo;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class TodoDto {
    int memberCode;
    String content;
    int color;



}
