/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau6;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class HashTable {
    Node[] table;
    ArrayList<Integer> cellar;
    int collisionCount = 0;
    int cellarCount = 0;
    final int MAX_CHAIN_LENGTH = 10;
    final int CELLAR_CAPACITY = 1000;

    public HashTable(int size) {
        table = new Node[size];
        cellar = new ArrayList<>();
    }

    public void insert(int key) {
        int index = key % table.length;
        Node head = table[index];

        if (head == null) {
            table[index] = new Node(key);
        } else {
            collisionCount++;

            // Tính độ dài chain hiện tại
            int length = 1;
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
                length++;
            }

            if (length < MAX_CHAIN_LENGTH) {
                curr.next = new Node(key);
            } else {
                // Đẩy vào Cellar nếu có chỗ
                if (cellar.size() < CELLAR_CAPACITY) {
                    cellar.add(key);
                    cellarCount++;
                }
            }
        }
    }
    public void search(int key) {
    int index = key % table.length;
    Node curr = table[index];
    int position = 0;

    while (curr != null) {
        if (curr.value == key) {
            System.out.println("Tìm thấy trong bảng băm tại index " + index + ", vị trí thứ " + position + " trong chuỗi.");
            return;
        }
        curr = curr.next;
        position++;
    }

    // Nếu không thấy trong bảng băm, kiểm tra cellar
    for (int i = 0; i < cellar.size(); i++) {
        if (cellar.get(i) == key) {
            System.out.println("Tìm thấy trong cellar tại vị trí " + i);
            return;
        }
    }

    System.out.println("Không tìm thấy số " + key);
}
    public boolean contains(int key) {
    int index = key % table.length;
    Node curr = table[index];
    while (curr != null) {
        if (curr.value == key) return true;
        curr = curr.next;
    }
    for (int value : cellar) {
        if (value == key) return true;
    }
    return false;
}

}