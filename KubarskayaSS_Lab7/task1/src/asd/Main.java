package asd;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("������� ���-�� �����: ");
        int n = scan.nextInt();
        //int n = 1000000;
        HashTable hashTable = new HashTable(n);


        //������ 100 ���������
        double start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }
        double end = System.nanoTime() - start;
        System.out.printf("\n����� ��� ���������� ������ 100 ���������: %4.6f", end / 1000000);
        start=0;

        //�������������
        for (int i = 100; i < n - 100; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }

        //��������� 100 ���������
        start = System.nanoTime();
        for (int i = n - 100; i < n; i++) {
            hashTable.add((int) (Math.random() * n), (int) (Math.random() * n));
        }
        end = System.nanoTime() - start;
        System.out.printf("\n����� ��� ���������� ��������� 100 ���������: %4.6f", end / 1000000);
        start =0;
        end = 0;

        //�����
            Integer k = scan.nextInt();
            start = System.nanoTime();
            Integer value = hashTable.search(k);
            if (value == null) {
                System.out.println("����� ���");
            } else {
                System.out.println("�������� " + value);
            }
            end = System.nanoTime() - start;
            
            System.out.printf("\n����� ������ ��������: %4.6f", end / 1000 / 1000000);
        
    }
  
}
