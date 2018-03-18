package org.well.web.service;

import org.springframework.stereotype.Service;
import org.well.web.entity.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Long, Product> products = new HashMap<Long, Product>();

    private AtomicLong generator = new AtomicLong();

    public ProductServiceImpl() {
        Product product = new Product();
        product.setName("JX1 Power Drill");
        product.setDescription("Powerful hand drill, made to perfection");
        product.setPrice(new BigDecimal(129.99));
        add(product);
    }

    public Product add(Product product) {
        long newLong = generator.incrementAndGet();
        product.setId(newLong);
        products.put(newLong, product);
        return product;
    }

    public Product get(long id) {
        return products.get(id);
    }
}
