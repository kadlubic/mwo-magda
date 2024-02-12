package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>(); //kolecja i lista są interfejsami, więc musi być arraylist
    private Map<Product, Integer> quantities = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Produkt nie może być zerem.");
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa niż 0.");
        }
        this.products.add(product);
        this.quantities.put(product, quantity);
    }

    public BigDecimal getNetPrice() {
        BigDecimal netPrice = BigDecimal.ZERO;
        for (Product product : this.products) {
            Integer quantity = this.quantities.getOrDefault(product, 1);
            BigDecimal productPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            netPrice = netPrice.add(productPrice);
        }

        return netPrice;
    }

    public BigDecimal getTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for (Product product : this.products) {
            Integer quantity = this.quantities.getOrDefault(product, 1);
            BigDecimal productTax = product.getPriceWithTax().subtract(product.getPrice()).multiply(BigDecimal.valueOf(quantity));
            tax = tax.add(productTax);
        }
        return tax;
    }

    public BigDecimal getGrossPrice() {
        BigDecimal netPrice = getNetPrice();
        BigDecimal tax = getTax();
        return netPrice.add(tax);
    }
}
