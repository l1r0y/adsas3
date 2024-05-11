public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        table.put(new MyTestingClass(1), "Value1");
        table.put(new MyTestingClass(2), "Value2");
        table.put(new MyTestingClass(3), "Value3");

        System.out.println("Value for key 2: " + table.get(new MyTestingClass(2)));

        table.remove(new MyTestingClass(2));

        System.out.println("Contains value 'Value1': " + table.contains("Value1"));

        System.out.println("Key for value 'Value1': " + table.getKey("Value1"));

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