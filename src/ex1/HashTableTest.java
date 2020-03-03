package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    HashTable ht = new HashTable();

    @org.junit.jupiter.api.Test
    void size() {
        ht.put("","null");
        ht.put("0","q1q");
        ht.put("0","q2q");
        ht.put("1","qq111");
        System.out.println(ht.size());
    }

    @org.junit.jupiter.api.Test
    void realSize() {

    }

    @org.junit.jupiter.api.Test
    void put() {

        ht.put("","null");
        ht.put("0","q1q");
        ht.put("0","q2q");
        ht.put("1","qq111");

        ht.put("12","qwe");
        ht.put("2","qq");
        ht.put("3","qq");
        ht.put("4","qq");
        ht.put("99","qwe");
        ht.put("99","123");
        ht.put("77","qwe");
        ht.put("77","qwe1");

        Assertions.assertEquals(
                "\n" +
                " bucket[0] = [0, q2q] -> [99, qwe] -> [99, 123] -> [77, qwe]\n" +
                " bucket[1] = [1, qq111]\n" +
                " bucket[2] = [2, qq]\n" +
                " bucket[3] = [3, qq]\n" +
                " bucket[4] = [4, qq]", ht.toString());

    }

    @org.junit.jupiter.api.Test
    void get() {
        ht.put("1","qq");
        ht.put("78","qwe");
        Assertions.assertEquals("qwe", ht.get("78"));
        ht.put("2","q1q");

        Assertions.assertEquals("qq", ht.get("1"));
        Assertions.assertEquals("q1q", ht.get("2"));
        Assertions.assertEquals("", ht.get(""));
    }

    @org.junit.jupiter.api.Test
    void drop() {
        //
        ht.put("1","qq");
        ht.put("56","11");
        ht.put("78","qq");
        ht.put("100","qq");

        ht.drop("1");
        ht.drop("56");





        Assertions.assertEquals("", ht.toString());
    }
}