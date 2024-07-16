package example.day08.board;

public class BoardDto {

    private int bno;
    private String content;
    private String bpw;
    private String title;
    private String date ;
    private int view;

    public BoardDto(){}


    public BoardDto(int bno, String title, String date, int view){
        this.bno = bno;
        this.title = title;
        this.date = date;
        this.view = view;
    }



    public BoardDto(int bno, String content, String bpw, String title, String date, int view) {
        this.bno = bno;
        this.content = content;
        this.bpw = bpw;
        this.title = title;
        this.date = date;
        this.view = view;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBpw() {
        return bpw;
    }

    public void setBpw(String bpw) {
        this.bpw = bpw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", content='" + content + '\'' +
                ", bpw='" + bpw + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", view=" + view +
                '}';
    }
}
