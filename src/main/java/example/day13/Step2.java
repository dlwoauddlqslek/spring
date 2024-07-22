package example.day13;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Step2 {
    public static void main(String[] args) {
        TreeSet<Integer> scores= new TreeSet<>();

        scores.add(23);
        scores.add(10);
        scores.add(48);
        scores.add(15);
        scores.add(7);
        scores.add(22);
        scores.add(56);
        System.out.println("scores = " + scores);
        scores.forEach(score ->{
            System.out.println("score = " + score);
        });
        System.out.println("top score = " +scores.last());
        System.out.println("lowest score = " +scores.first());
        System.out.println("score = " +scores.lower(48));
        System.out.println("scores = " + scores.higher(48));
        System.out.println("top score = " +scores.floor(48));
        System.out.println("top score = " +scores.ceiling(48));
        System.out.println("scores.descendingSet() = " + scores.descendingSet());
        System.out.println("scores.tailSet(48,true) = " + scores.tailSet(48,true));
        System.out.println("scores.headSet(48,true) = " + scores.headSet(48,true));
        System.out.println("scores.subSet(10,true,48,false) = " + scores.subSet(10,true,48,false));

        TreeMap<Integer,String> treeMap=new TreeMap<>();
        treeMap.put(23,"apple");
        treeMap.put(10,"forever");
        treeMap.put(48,"ever");
        treeMap.put(15,"band");
        treeMap.put(7,"drum");
        treeMap.put(22,"base");
        treeMap.put(56,"first");
        System.out.println("treeMap = " + treeMap);
        treeMap.entrySet().forEach(key->{
            System.out.println("key = " + key);
        });

        System.out.println("treeMap.descendingMap() = " + treeMap.descendingMap());
    }
}
