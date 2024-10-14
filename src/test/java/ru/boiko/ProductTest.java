package ru.boiko;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    @DisplayName("Готовим торт без ошибок и получаем true")
    public void preparingCakeAndGetTrue() {
        Product cake = new Product("Торт");
        Product vanillin = new Product("Ванилин");
        Product cacao = new Product("Какао");
        Product caffeine = new Product("Кофеин");
        Product water = new Product("Вода");
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product eggs = new Product("Яйца");

        assertTrue(cake.addProduct(vanillin));
        assertTrue(cake.addProduct(cacao));
        assertTrue(cacao.addProduct(caffeine));
        assertTrue(cacao.addProduct(water));
        assertTrue(cake.addProduct(dough));
        assertTrue(dough.addProduct(flour));
        assertTrue(dough.addProduct(eggs));
        assertTrue(dough.addProduct(water));
    }

    @Test
    @DisplayName("Готовим тесто, добавляя тесто как ингредиент, и получаем false")
    public void preparingDoughWithCycleAndGetFalse() {
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product eggs = new Product("Яйца");
        Product water = new Product("Вода");
        Product wheat = new Product("Пшеница");

        assertFalse(dough.addProduct(dough));
        assertTrue(dough.addProduct(flour));
        assertTrue(dough.addProduct(eggs));
        assertTrue(dough.addProduct(water));
        assertFalse(flour.addProduct(dough));
        assertTrue(flour.addProduct(wheat));
        assertFalse(wheat.addProduct(dough));
    }

    @Test
    @DisplayName("Готовим тесто, добавляя ингридиенты, уже содержащие в себе тесто, и получаем false")
    public void preparingDoughWithIncorrectIngredientsAndGetFalse() {
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product wheat = new Product("Пшеница");

        assertTrue(wheat.addProduct(dough));
        assertTrue(flour.addProduct(wheat));
        assertFalse(dough.addProduct(flour));
    }

    @Test
    @DisplayName("Готовим тесто, добавляя одинаковый продукт несколько раз, и получаем false")
    public void preparingDoughWithDuplicateIngredientsAndGetFalse() {
        Product dough = new Product("Тесто");
        Product flour1 = new Product("Мука");
        Product flour2 = new Product("Мука");

        assertTrue(dough.addProduct(flour1));
        assertFalse(dough.addProduct(flour2));
    }

    @Test
    @DisplayName("Готовим тесто, проверяем сохраненные ингридиенты")
    public void preparingDoughAndCheckSavedIngredients() {
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product water = new Product("Вода");
        Product dough2 = new Product("Тесто");

        assertTrue(dough.addProduct(flour));
        assertTrue(dough.addProduct(water));
        assertFalse(dough.addProduct(dough2));
        assertEquals(Set.of(flour, water), dough.getIngredients());
    }

    @Test
    @DisplayName("Готовим тесто, удаляем лишний ингредиент")
    public void preparingDoughAndRemovedIngredient() {
        Product dough = new Product("Тесто");
        Product flour = new Product("Мука");
        Product water = new Product("Вода");
        Product pepper = new Product("Перец");

        assertTrue(dough.addProduct(flour));
        assertTrue(dough.addProduct(water));
        assertTrue(dough.addProduct(pepper));

        dough.removeIngredient(pepper);

        assertEquals(Set.of(flour, water), dough.getIngredients());
    }
}
