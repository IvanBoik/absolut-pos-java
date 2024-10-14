package ru.boiko;

public class Main {
    public static void main(String[] args) {
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product eggs = new Product("Яйца");
        Product water = new Product("Вода");
        Product wheat = new Product("Пшеница");

        System.out.println(dough.addProduct(flour));
        System.out.println(dough.addProduct(eggs));
        System.out.println(dough.addProduct(water));
        System.out.println(flour.addProduct(dough));
        System.out.println(flour.addProduct(wheat));
        System.out.println(wheat.addProduct(dough));
    }
}
