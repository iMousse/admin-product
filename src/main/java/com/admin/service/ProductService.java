package com.admin.service;

import com.admin.domain.Product;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */
public interface ProductService {

    List<Product> getAll(Integer page, Integer size);

    Product getById(Long productId);

    int insert(Product product);

    int update(Product product);

    int delete(Long productId);

    int updateSelective(Long productId, String productContractUrl);
}
