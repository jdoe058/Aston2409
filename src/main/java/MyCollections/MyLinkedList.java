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


    private Node<T> getNode (int index) {
        if (index < 0 || index > _length) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> currNode = head;
        while (currNode != null && index-- > 0) {
                currNode = currNode.next;
        }

        return currNode;
    }

    public T get(int index) {
        return getNode(index).value;
    }


    public void push(T value) {
        Node<T> newNode = new Node<>(value, null, head);
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        _length++;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value,tail,null);
        if (head == null) {
            tail = head = newNode;
        } else {
            tail =  tail.next = newNode;
        }
        _length++;
    }

    public void push(Iterable<T> values) {
        MyLinkedList<T> tmp = new MyLinkedList<>(values);
        tmp.tail.next = head;
        head.prev = tmp.tail;
        head = tmp.head;
        _length += tmp._length;
    }

    public void add(Iterable<T> values) {
        MyLinkedList<T> tmp = new MyLinkedList<>(values);
        tail.next = tmp.head;
        tmp.head.prev = tail;
        tail = tmp.tail;
        _length += tmp._length;
    }


    public void add (T value, int index) {
        if (index == 0) {
            push(value);
        } else {
            Node<T> currentNode = getNode(index);
            currentNode.prev.next = new Node<>(value, currentNode.prev, currentNode);
            _length++;
        }
    }

    public void add(Iterable<T> values, int index) {
        if (index == 0) {
            push(values);
        } else {
            MyLinkedList<T> tmp = new MyLinkedList<>(values);
            Node<T> currentNode = getNode(index);
            tmp.tail.next = currentNode.next;
            currentNode.next = tmp.head;
            tmp.head.prev = currentNode;
            _length += tmp._length;
        }
    }
}
