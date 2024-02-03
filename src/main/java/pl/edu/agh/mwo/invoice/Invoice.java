package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>(); //kolecja i lista są interfejsami, więc musi być arraylist
    private Map<Product, Integer> quantities;

    public void addProduct(Product product) {
        this.products.add(product);

    }

    public void addProduct(Product product, Integer quantity) {
        this.products.put(product, quantity);
        // TODO: implement
    }

    public BigDecimal getNetPrice() {
        BigDecimal netPrice = BigDecimal.ZERO;
        for (Product product : this.products) {
            netPrice = netPrice.add(product.getPrice());
        }

        return netPrice;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getGrossPrice() {
        return BigDecimal.ZERO;
    }
}
