import java.util.LinkedList;
public class CollectionUtil {


    public static class MyStack<T> {
        private LinkedList<T> list = new LinkedList<>();

        public void push(T element) { list.addFirst(element); }
        public T pop() { return list.isEmpty() ? null : list.removeFirst(); }
        public T peek() { return list.peekFirst(); }
        public boolean isEmpty() { return list.isEmpty(); }
    }


    public static class MyQueue<T> {
        private LinkedList<T> list = new LinkedList<>();

        public void enqueue(T element) { list.addLast(element); }
        public T dequeue() { return list.isEmpty() ? null : list.removeFirst(); }
        public boolean isEmpty() { return list.isEmpty(); }
    }


    public static void test() {
        MyStack<Integer> s = new MyStack<>();
        s.push(10); s.push(20);
        System.out.println("Stack pop: " + s.pop());
        System.out.println("Stack peek: " + s.peek());

        MyQueue<String> q = new MyQueue<>();
        q.enqueue("A"); q.enqueue("B");
        System.out.println("Queue dequeue: " + q.dequeue());
        System.out.println("Queue empty? " + q.isEmpty());
    }
}
