package org.well.web.service;

import org.well.web.entity.Product;

public interface ProductService {

    Product add(Product product);

    Product get(long id);

}
