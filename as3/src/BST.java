import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    private Node root;
    private int size;

    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V> left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo((K) x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return (V) x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Node min(Node x) {
        while (x.left != null) x = x.left;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            pushLeft(node.right);
            return (K) node.key;
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");
        tree.put(6, "Six");
        tree.put(8, "Eight");

        System.out.println("Keys in ascending order:");
        for (var elem : tree) {
            System.out.println("Key is " + elem);
        }
    }
}