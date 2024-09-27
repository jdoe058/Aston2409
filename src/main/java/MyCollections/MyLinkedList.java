package MyCollections;

public class MyLinkedList<T> {
    private int _length;
    private boolean _isSorted;
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
        _isSorted = true;
    }

    public MyLinkedList(Iterable<T> elements) {
        this();
        _isSorted = false;
        for (T elem : elements) {
            tail = head == null ? (head = new Node<>(elem))
                : (tail.next = new Node<>(elem, tail, null));
            _length++;
        }
    }

    public MyLinkedList(MyLinkedList<T> list) {
        this(list, 0, -1);
    }

    public MyLinkedList(MyLinkedList<T> list, int index) {
        this(list, index, -1);
    }

    public MyLinkedList(MyLinkedList<T> list, int index, int count) {
        this();
        if (count > 0 && (index + count > list._length)) {
            throw new IndexOutOfBoundsException();
        }
        _isSorted = list._isSorted;
        Node<T> currentNode = list.getNode(index);
        while (currentNode != null && (count < 0 || count-- > 0)) {
            tail = head == null ? (head = new Node<>(currentNode.value))
                : (tail.next = new Node<>(currentNode.value, tail, null));
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
            _isSorted = false;
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
            _isSorted = false;
        }
        _length++;
    }

    public void push(Iterable<T> values) {
        MyLinkedList<T> tmp = new MyLinkedList<>(values);
        _isSorted = false;
        tmp.tail.next = head;
        head.prev = tmp.tail;
        head = tmp.head;
        _length += tmp._length;
    }

    public void add(Iterable<T> values) {
        MyLinkedList<T> tmp = new MyLinkedList<>(values);
        _isSorted = false;
        tail.next = tmp.head;
        tmp.head.prev = tail;
        tail = tmp.tail;
        _length += tmp._length;
    }


    public void add (T value, int index) {
        if (index == 0) {
            push(value);
        } else {
            _isSorted = false;
            Node<T> currentNode = getNode(index);
            currentNode.prev.next = new Node<>(value, currentNode.prev, currentNode);
            _length++;
        }
    }

    public void add(Iterable<T> values, int index) {
        if (index == 0) {
            push(values);
        } else {
            _isSorted = false;
            MyLinkedList<T> tmp = new MyLinkedList<>(values);
            Node<T> currentNode = getNode(index);
            tmp.tail.next = currentNode.next;
            currentNode.next = tmp.head;
            tmp.head.prev = currentNode;
            _length += tmp._length;
        }
    }

    public T pop () {
        if (head == null ) {
            throw new IndexOutOfBoundsException();
        }
        T tmp = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        _length--;
        return tmp;
    }

    public T remove () {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        T tmp = tail.value;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        _length--;
        return tmp;
    }

    public void clear() {
        tail = head = null;
        _isSorted = true;
        _length = 0;
    }

    public T remove(int index) {
        if (index == 0) {
            return pop();
        }

        Node<T> currentNode = getNode(index);
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        _length--;

        return currentNode.value;
    }

    public MyLinkedList<T> remove (int index, int count) {
        Node<T> lastNode = getNode(index+count);
        var tmp = new MyLinkedList<>(this, index, count);
        if (index == 0) {
            head = lastNode;
            lastNode.prev = head;
        } else {
            Node<T> firstNode = getNode(index-1);
            firstNode.next = lastNode;
            lastNode.prev = firstNode;
        }
        _length -= count;
        return tmp;
    }

    public static <T extends Comparable<T>> void sort(MyLinkedList<T> list) {
        if (list._isSorted) {
            return;
        }

        Node<T> j = list.head;
        while (j.next != null) {
            Node<T> i = j.next;
            while (i != null) {
                if (i.value.compareTo(j.value) < 0) {
                    var tmp = i.value;
                    i.value = j.value;
                    j.value = tmp;
                }
                i = i.next;
            }
            j = j.next;
        }
        list._isSorted = true;
    }
}
