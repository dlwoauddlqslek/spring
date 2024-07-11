package example.day06;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Step1 {
    public static void main(String[] args) {
        int  value1=100;

        Integer value2=new Integer(100);
        value2.intValue();

        //1. 자주 사용되는 메소드

        int myValue = value2.intValue();
        System.out.println("myValue = " + myValue);

        Integer value3=Integer.valueOf("100");
        Integer value4=Integer.valueOf(100);

        int value5= Integer.parseInt("100");
        double value6 = Double.parseDouble("3.14");
        float value7 = Float.parseFloat("3.14");
        byte value8=Byte.parseByte("100");
        short value9=Short.parseShort("30000");
        long value10=Long.parseLong("3000000");

        int value11=value1+value2;
        System.out.println("value11 = " + value11);
        Integer value12=value11;
        
        String s=new String();
        Class c=s.getClass();
        System.out.println(c.toString());
        
        //try {
            //Class c3=Class.forName("java.lang.String");
            
            
        //}//catch (Exception e)
        
        Person person=new Person();
        Class class1=person.getClass();
        System.out.println("class1.toString() = " + class1.toString());
        
        Class class2= Person.class;
        System.out.println("class2.toString() = " + class2.toString());
        
        try{
            Class class3= Class.forName("example.day06.Person");
            System.out.println("class3.toString() = " + class3.toString());
        }catch (Exception e){
            System.out.println("e = " + e);
        }

        Constructor[] cons =c.getConstructors();
        for (Constructor con:cons){
            System.out.println("con = " + con);
        }

        Field[] fields=c.getFields();
        for (Field f:fields){
            System.out.println("f = " + f);
        }

        Method[] methods=c.getMethods();
        for (Method m:methods){
            System.out.println("m = " + m);
        }

        Person p2 = new Person();
        System.out.println("p2 = " + p2);

        try {


            Class pClass = Class.forName("example.day06.Person");
            Person p3 = (Person) pClass.newInstance();
            System.out.println("p3 = " + p3);
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }
}
