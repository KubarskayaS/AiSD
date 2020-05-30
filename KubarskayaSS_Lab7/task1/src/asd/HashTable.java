package asd;
import java.util.Arrays;
import java.util.LinkedList;

public class HashTable {
    int copasity; //объЄм таблицы
    LinkedList<Node>[] table;

    HashTable(int copasity) {
        this.copasity = copasity;
        table = new LinkedList[this.copasity];
        Arrays.fill(table, null);
    }

    private int hashCode(int key) { //высчитывание номера €чейки
        return key % this.copasity;
    }

    public void add(int key, int value) {
        Node node = new Node(key, value); //иницилизаци€ вершины
        int hash = hashCode(key);
        if (table[hash] == null) { //проверка на зан€то ли €чейка
            table[hash] = new LinkedList<>(); //если пуста€ то создаЄм в ней список
            table[hash].add(node);
        } else {
            table[hash].addFirst(node); //если зан€та то к существующему списку добавл€ем новый Node
        }
    }

    public Integer search(int key) {
        int hash = hashCode(key);
        Integer value = null;
        if (table[hash] != null) {
            for (int i = 0; i < table[hash].size(); i++) {
                if (key == table[hash].get(i).key) {
                    value = table[hash].get(i).value;
                    break;
                }
            }
        }
        return value;
    }
}
