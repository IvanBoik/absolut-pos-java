package ru.boiko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Product {
    private final String name;
    private final Set<Product> ingredients;
    private final List<Product> roots;

    public Product(String name) {
        this.name = name;
        this.ingredients = new HashSet<>();
        this.roots = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        if (!checkProduct(product)) {
            return false;
        }
        product.addRoot(this);
        return ingredients.add(product);
    }

    private boolean checkProduct(Product product) {
        if (equals(product) || roots.contains(product) || ingredients.contains(product)) {
            return false;
        }

        Set<Product> allRoots = getAllRoots();
        if (allRoots.contains(product)) {
            return false;
        }

        Set<Product> allIngredients = product.getAllIngredients();
        for (Product ingredient : allIngredients) {
            if (equals(ingredient) || allRoots.contains(ingredient)) {
                return false;
            }
        }
        return true;
    }

    private Set<Product> getAllRoots() {
        if (roots.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Product> allRoots = new HashSet<>(roots);
        for (Product root : roots) {
            allRoots.addAll(root.getAllRoots());
        }
        return allRoots;
    }

    private Set<Product> getAllIngredients() {
        if (ingredients.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Product> allIngredients = new HashSet<>(ingredients);
        for (Product ingredient : ingredients) {
            allIngredients.addAll(ingredient.getAllIngredients());
        }
        return allIngredients;
    }

    private void addRoot(Product product) {
        roots.add(product);
    }

    public void removeIngredient(Product ingredient) {
        ingredients.remove(ingredient);
        ingredient.removeRoot(this);
    }

    private void removeRoot(Product product) {
        roots.remove(product);
    }

    public Set<Product> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        String s = "Product{name='" + name + "'";
        if (!ingredients.isEmpty()) {
            s += ", ingredients=" + ingredients;
        }
        return s + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
