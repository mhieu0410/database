package cau6;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int TABLE_SIZE = 131071; // số nguyên tố lớn giúp giảm collision
        HashTable hashTable = new HashTable(TABLE_SIZE);
        List<Integer> allNumbers = new ArrayList<>();

        // Đọc file 50k.txt và chèn vào hash table
        try (BufferedReader reader = new BufferedReader(new FileReader("50k.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    allNumbers.add(number);
                    hashTable.insert(number);
                } catch (NumberFormatException ignored) {}
            }
        }

        // Kiểm tra lại toàn bộ các số vừa chèn có nằm trong hash table không
        int found = 0, notFound = 0;
        for (int number : allNumbers) {
            if (hashTable.contains(number)) {
                found++;
            } else {
                notFound++;
            }
        }

        // In kết quả
        System.out.println("✅ Tổng số số được chèn: " + allNumbers.size());
        System.out.println("🔍 Số lượng tìm thấy lại: " + found);
        System.out.println("❌ Số lượng không tìm thấy: " + notFound);
        System.out.println("🔁 Tổng số collision: " + hashTable.collisionCount);
        System.out.println("📦 Số phần tử trong Cellar: " + hashTable.cellarCount);

        // Đánh giá
        if (hashTable.collisionCount < 5000 && hashTable.cellarCount < 200) {
            System.out.println("✅ Hash function hoạt động tốt (ít collision, ít phần tử vào cellar).");
        } else {
            System.out.println("⚠️ Hash function chưa tốt (nhiều collision hoặc cellar đầy).");
        }

        // Cho phép người dùng nhập 1 số để kiểm tra thủ công
        Scanner scanner = new Scanner(System.in);
        System.out.print("🔎 Nhập số cần tìm: ");
        int num = scanner.nextInt();
        hashTable.search(num);
    }
}
