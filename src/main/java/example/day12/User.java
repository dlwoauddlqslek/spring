package example.day12;

public class User {
    String name;
    int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
       return this.name.hashCode();
    }
   @Override
    public boolean equals(Object obj){
        if (obj instanceof User){
            User user=(User) obj;
            if (this.name.equals(user.name)){
                return true;
            }else {return false;}
        }
        return false;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
