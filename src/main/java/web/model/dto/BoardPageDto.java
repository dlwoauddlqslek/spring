package web.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardPageDto {
    // 검색,카테고리별 페이징처리에 다양한 매개변수가 필요하므로 집합 Dto
    private int page; // 현재 페이지
    private int totalBoardSize; //전체 게시물수
    private int totalPage; // 전체 페이지수
    private int startBtn; // 페이지별 시작버튼 번호
    private int endBtn; // 페이지별 끝버튼 번호
    private List<BoardDto> data; //조회된 게시물 정보 목록
    private int bcno; //현재 카테고리 번호
    // + 검색 필드
    private String searchKey;   // 8. 검색 조회시 사용되는 필드명
    private String searchKeyword; // 9. 검색 조회시 사용되는 필드의 값

}
