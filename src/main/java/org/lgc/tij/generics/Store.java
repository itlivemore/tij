package org.lgc.tij.generics;
import net.mindview.util.Generator;
import org.lgc.tij.generics.Generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * 构建一个零售店模型，它包含走廊/货架和商品
 * Created by lgc on 17-1-3.
 */
class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random random = new Random(47);

        @Override
        public Product next() {
            return new Product(random.nextInt(10000), "Test", Math.round(random.nextDouble() * 1000.0) + 0.99);
        }
    };
}

class Shelf extends ArrayList<Product> {
    public Shelf(int nProduct) {
        Generators.fill(this, Product.generator, nProduct);
    }
}

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProduct) {
        for (int i = 0; i < nShelves; i++) {
            add(new Shelf(nProduct));
        }
    }
}

class CheckoutStand {
}

class Office {
}

public class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
    private Office office = new Office();

    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Aisle aisle : this) {
            for (Shelf shelf : aisle) {
                for (Product product : shelf) {
                    sb.append(product);
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}
