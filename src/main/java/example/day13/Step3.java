package example.day13;

import java.util.TreeSet;

public class Step3 {
    public static void main(String[] args) {

        TreeSet<Member> members=new TreeSet<>();
        members.add(new Member("홍길동",40));
        members.add(new Member("유재석",30));
        members.add(new Member("신동엽",20));
        members.add(new Member("강호동",50));
        System.out.println("members = " + members);
    }



}
