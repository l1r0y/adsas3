import java.util.Random;

public class MyHashTable<K, V> {

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    public void put(K key, V value) {
        int hash = hash(key);
        if (chainArray[hash] == null) {
            chainArray[hash] = new HashNode<>(key, value);
        } else {
            HashNode<K, V> current = chainArray[hash];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = new HashNode<>(key, value);
        }
        size++;
    }

    public V get(K key) {
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[hash] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(1000));
            table.put(key, "Value" + i);
        }
        int[] bucketCounts = new int[table.M];
        for (int i = 0; i < table.M; i++) {
            HashNode<MyTestingClass, String> node = table.chainArray[i];
            while (node != null) {
                bucketCounts[i]++;
                node = node.next;
            }
            System.out.println("Bucket " + i + ": " + bucketCounts[i] + " elements");
        }
    }
}

class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class MyTestingClass {
    int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id % 11;
    }
}