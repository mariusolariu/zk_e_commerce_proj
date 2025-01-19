package org.ecommerce.viewmodels;

import org.ecommerce.model.Product;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductViewModel {
    private List<Product> products;
    private boolean showAddProductDialog;
    private boolean showEditDialog;
    private Product productToEdit;
    private String newProductName;
    private Double newProductPrice;


    @Init
    public void init() {
        // Initialize the product list
        products = new ArrayList<>();
        products.add(new Product("1", "Laptop", 2D));
    }

    @Command
    @NotifyChange("showAddProductDialog")
    public void toggleAddProductDialog() {
        showAddProductDialog = !showAddProductDialog;
    }

    @Command
    @NotifyChange({"showEditDialog", "productToEdit"})
    public void toggleEditProductDialog(@BindingParam("productToEdit") Product productToEdit){
        showEditDialog = !showEditDialog;
        this.productToEdit = productToEdit;
    }

    @Command
    @NotifyChange({"showEditDialog"})
    public void saveEditedProduct() {
        showEditDialog = false;
    }

    @Command
    @NotifyChange({"products", "showAddProductDialog", "newProductName", "newProductPrice"})
    public void addNewProduct() {
        Product newProduct = new Product(
                UUID.randomUUID().toString(), newProductName, newProductPrice);

        products.add(newProduct);
        showAddProductDialog = false;
        newProductName = "";
        newProductPrice = 0d;
    }

    @Command
    @NotifyChange("products")
    public void deleteProduct(@BindingParam("productId") String productId) {
        int index = searchProductIndexByProductId(productId);
        products.remove(index);
    }

    public int searchProductIndexByProductId(String productId){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                return i;
            }
        }

        return -1;
    }


    public boolean isShowEditDialog() {
        return showEditDialog;
    }

    public void setShowEditDialog(boolean showEditDialog) {
        this.showEditDialog = showEditDialog;
    }

    public Product getProductToEdit() {
        return productToEdit;
    }

    public void setProductToEdit(Product productToEdit) {
        this.productToEdit = productToEdit;
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
