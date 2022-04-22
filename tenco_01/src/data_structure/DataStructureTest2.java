package data_structure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class DataStructureTest2 {

    public static void main(String[] args) {
        Map map;
        // key와 Value 구조로 데이터를 저장한다.
        // 순서 X
        HashMap<String, String> map1 = new HashMap<>();


        Hashtable<String, String> map2 = new Hashtable<>();
        // hashMap >> null값을 허용 하지 않는다.
        map1.put("A01", "김포공항 정문");
        map1.put("A02", "김포공항 후문");
        map1.put("A03", "김포공항 로비");
        map1.put("A04", "인천공항 정문");
        map1.put("A05", "인천공항 후문");
        map1.put("A06", "인천공항 후문");
        map1.put("C01", null);

        System.out.println(map1);
        System.out.println(map1.get("A03"));
        System.out.println(map1.get("C01"));
        map1.remove("a04"); // 키 값은 대소문자를 구별한다.
//        map1.clear();
        System.out.println(map1);

        // 사이즈 확인 방법
        System.out.println(map1.size());

        //map 계열에서 for문을 사용하는 방법
        // 1. 방법 - > Java.util.Map.Entry 활용
        for(Entry<String, String> entry : map1.entrySet()) {
            // map1에서 뽑아서 제네릭의 형태로 entry에 담는다.
            System.out.println("Key : " + entry.getKey());
            System.out.println("Value : " + entry.getValue());
        }
        System.out.println("========================================");

        // 2. 방법
        // keySet()를 활용 Map계열 인터페이스에 선언된 메서드
        for(String key : map1.keySet()) {
            System.out.println("[Key]" + (key));
            System.out.println("[Value]" + map1.get(key));
        }



    }
}
