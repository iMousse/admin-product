package com.admin.mapper;

import com.admin.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public interface ProductMapper {

    @Select("select * from product")
    List<Product> selectAll();

    @Select("select * from product where productId = #{productId}")
    Product selectByPrimaryKey(Long productId);

    @Insert("insert into product (productName,productPrice,productDesc,productContract,productStatus,productStartTime) values (#{productName},#{productPrice},#{productDesc},#{productContract},#{productStatus},#{productStartTime})")
    int insert(Product product);

    @Update("update product set productName = #{productName},productPrice = #{productPrice},productDesc = #{productDesc},productContract = #{productContract},productStatus = #{productStatus} where productId = #{productId} ")
    int update(Product product);

    @Delete("delete from product  where productId = #{productId}")
    int deleteByPrimaryKey(Long productId);

    @Update("update product set productContractUrl = #{productContractUrl}  where productId = #{productId}")
    int updateSelective(@Param("productId") Long productId, @Param("productContractUrl") String productContractUrl);
}
