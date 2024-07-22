package example.day13;

public class Member implements Comparable<Member> {
    String name;
    int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Member member){
        //return this.name.compareTo(member.name);
        if (this.age< member.age){return -1;}
        else if (this.age== member.age){return 0;}
        else return 1;
    }
}
