package example.day09.lombok;

public class Step1 {
    public static void main(String[] args) {
        Member m1=new Member();
        m1.setId("ase");
        m1.setName("이재명");
        System.out.println(m1.getId());
        System.out.println(m1.getName());
        System.out.println(m1);

        Member m2=new Member("www","차스");
        System.out.println(m2);

        Member m3=Member.builder().id("asdfef").name("신둥").build();
        System.out.println(m3);


    }
}
