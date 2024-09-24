package MyCollectionsTest;

import MyCollections.MyLinkedList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyLinkedListTest {

    @Test
    public void constructorTest() {
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        assertEquals("Linked list (0) : ", list1.toString());

        MyLinkedList<String> list2 = new MyLinkedList<>(List.of("1", "2", "3", "5", "asd"));
        assertEquals("Linked list (5) : 1 2 3 5 asd ", list2.toString());

        MyLinkedList<String> list3 = new MyLinkedList<>(list2);
        assertEquals("Linked list (5) : 1 2 3 5 asd ", list3.toString());
    }

}
