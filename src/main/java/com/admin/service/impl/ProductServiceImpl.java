package com.admin.service.impl;

import com.admin.domain.Product;
import com.admin.mapper.ProductMapper;
import com.admin.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll(Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return productMapper.selectAll();
    }

    @Override
    public int insert(Product product) {
        product.setProductStartTime(new Date());
        return productMapper.insert(product);
    }

    @Override
    public int update(Product product) {
        return productMapper.update(product);
    }

    @Override
    public Product getById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public int delete(Long productId) {

        return productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public int updateSelective(Long productId, String productContractUrl) {
        return productMapper.updateSelective(productId, productContractUrl);
    }
}
