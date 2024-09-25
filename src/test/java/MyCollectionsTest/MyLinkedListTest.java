package MyCollectionsTest;

import MyCollections.MyLinkedList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

    @Test
    public void getTest() {
        MyLinkedList<Integer> list1 = new MyLinkedList<>(List.of(1, 2, 3));
        int x = list1.get(1);
        assertEquals(2, x);

        assertThrows(IndexOutOfBoundsException.class, () -> list1.get(4));
    }
}
