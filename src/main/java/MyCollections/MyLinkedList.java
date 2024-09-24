package MyCollections;

public class MyLinkedList<T> {
    private int _length;
    private Node<T> head,
            tail;

    static class Node<T> {
        T value;
        Node<T> next,
                prev;

        public Node(T value) {
            prev = next = null;
            this.value = value;
        }

        public Node (T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        _length = 0;
        tail = head = null;
    }

    public MyLinkedList(Iterable<T> elements) {
        this();
        for (T elem : elements) {
            if (head == null) {
                tail = head = new Node<>(elem);
            } else {
                tail = tail.next = new Node<>(elem, tail, null);
            }
            _length++;
        }
    }

    public MyLinkedList(MyLinkedList<T> list) {
        this();
        Node<T> currentNode = list.head;
        while (currentNode != null) {
            if (head == null) {
                tail = head = new Node<>(currentNode.value);
            } else {
                tail = tail.next = new Node<>(currentNode.value, tail, null);
            }
            _length++;
            currentNode = currentNode.next;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Linked list (" + _length + ") : ");
        Node<T> currentNode = head;
        while (currentNode != null) {
            result.append(currentNode.value).append(" ");
            currentNode = currentNode.next;
        }
        return result.toString();
    }
}
