package org.ecommerce.viewmodels;

import org.ecommerce.model.Product;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel {
    private List<Product> products;
    private boolean showAddProductDialog;
    private String newProductName="initialValue";
    private Double newProductPrice=3d;

    @Init
    public void init() {
        // Initialize the product list
        products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 899.99));
        products.add(new Product(2, "Smartphone", 499.99));
        products.add(new Product(3, "Headphones", 79.99));
    }

    @Command
    @NotifyChange("showAddProductDialog")
    public void toggleAddProductDialog() {
        showAddProductDialog = !showAddProductDialog;
    }

    @Command
    @NotifyChange({"products", "showAddProductDialog", "newProductName", "newProductPrice"})
    public void addNewProduct() {
        Product newProduct = new Product(
                products.size() + 1, newProductName, newProductPrice);

        products.add(newProduct);
        showAddProductDialog = false;
        newProductName = "";
        newProductPrice = 0d;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isShowAddProductDialog() {
        return showAddProductDialog;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public Double getNewProductPrice() {
        return newProductPrice;
    }

    public void setNewProductPrice(Double newProductPrice) {
        this.newProductPrice = newProductPrice;
    }
}
