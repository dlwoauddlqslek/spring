package example.day09.todo;

public class TodoDto {
    int memberCode;
    String content;
    int color;


    public TodoDto(){}

    public TodoDto(String content, int color) {
        this.content = content;
        this.color = color;
    }

    public TodoDto(int memberCode, String content, int color) {
        this.memberCode = memberCode;
        this.content = content;
        this.color = color;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "memberCode=" + memberCode +
                ", content='" + content + '\'' +
                ", color=" + color +
                '}';
    }
}
