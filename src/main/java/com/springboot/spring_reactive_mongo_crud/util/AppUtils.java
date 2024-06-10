package com.springboot.spring_reactive_mongo_crud.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.springboot.spring_reactive_mongo_crud.dto.ProductDto;
import com.springboot.spring_reactive_mongo_crud.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static  ProductDto entityToDto(Product product)
    {

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public static Product DtoToEntity(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);

        return product;
    }


}
