package ex2;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    HashTable ht = new HashTable();

    @org.junit.jupiter.api.Test
    void size() {
        // añadir los valores.
        ht.put("1","qq1");
        ht.put("34","qq34");
        ht.put("56","qq56");
        ht.put("78","qq78");
        ht.put("100","qq100");
        //hay 5 valor
        Assertions.assertEquals(5, ht.size());
        //volvar añadir valor y aumentar size
        ht.put("2","qq2");
        ht.put("35","qq35");
        ht.put("57","qq57");
        ht.put("79","qq79");
        ht.put("101","qq101");
        //hay 10 valor
        Assertions.assertEquals(10, ht.size());

        //eleminar en hash 1 en posiscion primero valor, reducir size
        ht.drop("1");
        Assertions.assertEquals(9, ht.size());
        //eleminar en hash 1 en posiscion ultimo valor, reducir size
        ht.drop("100");
        Assertions.assertEquals(8, ht.size());
        //eleminar en hash 1 en posiscion medio valor, reducir size
        ht.drop("56");
        Assertions.assertEquals(7, ht.size());
        //repertir poso con otro hash
        ht.drop("57");
        Assertions.assertEquals(6, ht.size());
        ht.drop("2");
        Assertions.assertEquals(5, ht.size());
        ht.drop("101");
        Assertions.assertEquals(4, ht.size());
        // eleminar volor no exister y no cambia size
        ht.drop("55");
        Assertions.assertEquals(4, ht.size());
        // eleminar volor vacio y no cambia size
        ht.drop("");
        Assertions.assertEquals(4, ht.size());

        //añadir valor vacion pero tiene hash, size añader.
        ht.put("","");
        Assertions.assertEquals(5, ht.size());
    }

    @org.junit.jupiter.api.Test
    void realSize() {
        // añadir los valores.
        ht.put("1","qq1");
        ht.put("34","qq34");
        ht.put("56","qq56");
        ht.put("78","qq78");
        ht.put("100","qq100");
        ht.put("2","qq2");
        ht.put("35","qq35");
        ht.put("57","qq57");
        ht.put("79","qq79");
        ht.put("101","qq101");
        ht.drop("1");
        ht.drop("100");
        ht.drop("56");
        ht.drop("57");
        ht.drop("2");
        ht.drop("101");
        ht.drop("55");

        System.out.println(ht.realSize());

    }

    @org.junit.jupiter.api.Test
    void put() {
        //add valor
        ht.put("0","q1q");
        Assertions.assertEquals("\n bucket[0] = [0, q1q]", ht.toString());
        //add valor en mismo hash hay que añadir final
        ht.put("22","qq1");
        Assertions.assertEquals("\n bucket[0] = [0, q1q] -> [22, qq1]", ht.toString());
        //add nuevo valor en nuevo hash
        ht.put("1","qq");
        ht.put("23","qq");
        ht.put("45","qq");
        Assertions.assertEquals("\n bucket[0] = [0, q1q] -> [22, qq1]"
                                        + "\n bucket[1] = [1, qq] -> [23, qq] -> [45, qq]", ht.toString());
        //sobre escribir valor de posicion inicio
        ht.put("1","qq1");
        Assertions.assertEquals("\n bucket[0] = [0, q1q] -> [22, qq1]"
                                        + "\n bucket[1] = [1, qq1] -> [23, qq] -> [45, qq]", ht.toString());
        ht.put("23","qq1");
        Assertions.assertEquals("\n bucket[0] = [0, q1q] -> [22, qq1]"
                                        + "\n bucket[1] = [1, qq1] -> [23, qq1] -> [45, qq]", ht.toString());
        ht.put("45","qq1");
        Assertions.assertEquals("\n bucket[0] = [0, q1q] -> [22, qq1]"
                                        + "\n bucket[1] = [1, qq1] -> [23, qq1] -> [45, qq1]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void get() {
        // añadir los valores.
        ht.put("1","qq1");
        ht.put("34","qq34");
        ht.put("56","qq56");
        ht.put("78","qq78");
        ht.put("100","qq100");
        ht.put("2","qq2");
        ht.put("35","qq35");
        ht.put("57","qq57");
        ht.put("79","qq79");
        ht.put("101","qq101");
        //buscar volar
        Assertions.assertEquals("qq2", ht.get("2"));
        Assertions.assertEquals("qq34", ht.get("34"));
        Assertions.assertEquals("qq100", ht.get("100"));
        //busca valor null
        Assertions.assertEquals(null, ht.get("null"));
        //buscar hash no exister
        Assertions.assertEquals(null, ht.get("5"));
        //buscar volor no existe en hash.
        ht.drop("101");
        Assertions.assertEquals(null, ht.get("101"));
        ht.drop("1");
        Assertions.assertEquals(null, ht.get("1"));

    }

    @org.junit.jupiter.api.Test
    void drop() {
        // añadir los valores.
        ht.put("1","qq");
        ht.put("34","qq");
        ht.put("56","11");
        ht.put("78","qq");
        ht.put("100","qq");

        // eleminar primer valor.
        ht.drop("1");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [56, 11] -> [78, qq] -> [100, qq]", ht.toString());
        // eleminar ultima valor.
        ht.drop("100");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [56, 11] -> [78, qq]", ht.toString());
        // eleminar medio valor.
        ht.drop("56");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]", ht.toString());
        // eleminar valor no exiter
        ht.drop("55");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]", ht.toString());
        ht.drop("qwe");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]", ht.toString());
        // eleminar valor null
        ht.drop("");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]", ht.toString());

        // añadir valores en otro hash.
        ht.put("2","qwe");
        // eleminar valores de otro hash.
        ht.drop("2");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]", ht.toString());

        // volver añadir valores en otro hash.
        ht.put("2","qq");
        ht.put("35","qq");
        ht.put("57","11");
        ht.put("79","qq");
        ht.put("101","qq");
        //probar otra vez con otro hash y mantiene valor de hash anterio.
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [2, qq] -> [35, qq] -> [57, 11] -> [79, qq] -> [101, qq]", ht.toString());
        ht.drop("2");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [57, 11] -> [79, qq] -> [101, qq]", ht.toString());
        // eleminar ultima valor.
        ht.drop("101");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [57, 11] -> [79, qq]", ht.toString());
        // eleminar medio valor.
        ht.drop("57");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [79, qq]", ht.toString());
        // eleminar valor no exiter
        ht.drop("5");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [79, qq]", ht.toString());
        ht.drop("qwe");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [79, qq]", ht.toString());
        // eleminar valor null
        ht.drop("");
        Assertions.assertEquals("\n bucket[1] = [34, qq] -> [78, qq]\n bucket[2] = [35, qq] -> [79, qq]", ht.toString());
    }
}