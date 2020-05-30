package asd;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите кол-во чисел: ");
        int n = scan.nextInt();
        //int n = 1000000;
        HashTable hashTable = new HashTable(n);


        //первые 100 элементов
        double start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }
        double end = System.nanoTime() - start;
        System.out.printf("\nВремя при добавлении первых 100 элементов: %4.6f", end / 1000000);
        start=0;

        //промежуточные
        for (int i = 100; i < n - 100; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }

        //последнии 100 элементов
        start = System.nanoTime();
        for (int i = n - 100; i < n; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }
        end = System.nanoTime() - start;
        System.out.printf("\nВремя при добавлении последних 100 элементов: %4.6f", end / 1000000);
        start =0;
        end = 0;

        //поиск
            Integer k = scan.nextInt();
            start = System.nanoTime();
            Integer value = hashTable.search(k);
            if (value == null) {
                System.out.println("Ключа нет");
            } else {
                System.out.println("Значение " + value);
            }
            end = System.nanoTime() - start;
            
            System.out.printf("\nВремя поиска элемента: %4.6f", end / 1000 / 1000000);
        
    }
  
}
