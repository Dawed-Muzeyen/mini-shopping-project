package edu.miu.cs.se.productsubsystem.service;

import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<List<Product>> getAllProducts() {
        return Optional.of(productRepository.findAll());
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> saveProduct(Product product) {
        return Optional.of(productRepository.save(product));
    }

    public Optional<Product> saveProductInfoOfOrderItem(Product product, long order_item_id) {

        Set<Long> allIds = productRepository.allProductIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }


        productRepository.insertProductInfoOfOrderItem(id, product.getProductName(), product.getQuantityAvail(), product.getUnitPrice(), product.getMfgDate(), product.getDescription(), order_item_id);
        return Optional.of(product);
    }

    public Optional<List<Product>> getProductsByOrderItemId(long order_item_id){
        return productRepository.selectProductsByOrderItemId(order_item_id);
    }
    //
    public Optional<Product> saveProductInfoOfShoppingCartItem(Product product, long shopping_cart_item_id) {

        Set<Long> allIds = productRepository.allProductIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }


        productRepository.insertProductInfoOfShoppingCartItem(id, product.getProductName(), product.getQuantityAvail(), product.getUnitPrice(), product.getMfgDate(), product.getDescription(), shopping_cart_item_id);
        return Optional.of(product);
    }

    public Optional<List<Product>> getProductsByShoppingCartItemId(long shopping_cart_item_id){
        return productRepository.selectProductsByShoppingCartItemId(shopping_cart_item_id);
    }

    public Optional<Product> deleteProduct(Product product) {
        productRepository.delete(product);
        return Optional.of(product);
    }

    public Optional<Product> deleteProductById(long id) {

        Optional<Product> productDeleted =  productRepository.findById(id);
        productRepository.deleteById(id);
        return productDeleted;
    }
    public Optional<Product> updateProductById(long id, Product product) {
        Product productTemp = productRepository.getById(id);

        if(!Optional.of(product.getProductName()).isEmpty())
        productTemp.setProductName(product.getProductName());

        if(!Optional.of(product.getQuantityAvail()).isEmpty())
        productTemp.setQuantityAvail(product.getQuantityAvail());

        if(!Optional.of(product.getUnitPrice()).isEmpty())
        productTemp.setUnitPrice(product.getUnitPrice());

        if(!Optional.of(product.getMfgDate()).isEmpty())
        productTemp.setMfgDate(product.getMfgDate());

        if(!Optional.of(product.getDescription()).isEmpty())
        productTemp.setDescription(product.getDescription());

        if(!Optional.of(product.getCatalogs()).isEmpty())
        productTemp.setCatalogs(product.getCatalogs());

        return Optional.of(productRepository.save(productTemp));
    }


    public Optional<List<Product>> getProductsByProductName(String productName){
        return productRepository.findByProductName(productName);
    }
    public Optional<List<Product>> getProductsByQuantityAvail(long quantityAvail) {
        return productRepository.findByQuantityAvail(quantityAvail);
    }
    public Optional<List<Product>> getProductsByUnitPrice(double unitPrice) {
        return productRepository.findByUnitPrice(unitPrice);
    }
    public Optional<List<Product>> getProductsByMfgDate(LocalDate mfgDate) {
        return productRepository.findByMfgDate(mfgDate);
    }
    public Optional<List<Product>> getProductsByDescription(String description) {
            return productRepository.findByDescription(description);
    }
}
