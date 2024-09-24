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

    @Test
    public void addOneElementTest () {
        MyLinkedList<String> list1 = new MyLinkedList<>(List.of("1", "2", "3", "5", "asd"));
        MyLinkedList<String> list2 = new MyLinkedList<>(list1);

        list1.push("4");
        assertEquals("Linked list (6) : 4 1 2 3 5 asd ", list1.toString());

        list2.add("test");
        assertEquals("Linked list (6) : 1 2 3 5 asd test ", list2.toString());
    }

    @Test
    public void addIterableTest() {
        MyLinkedList<String> list1 = new MyLinkedList<>(List.of("1", "2", "3", "5", "asd"));
        list1.push(List.of("4", "2", "6"));
        assertEquals("Linked list (8) : 4 2 6 1 2 3 5 asd ", list1.toString());

        MyLinkedList<String> list2 = new MyLinkedList<>(List.of("1", "2", "3", "5", "asd"));
        list2.add(List.of("4", "2", "6"));
        assertEquals("Linked list (8) : 1 2 3 5 asd 4 2 6 ", list2.toString());
    }


}
