package com.springboot.spring_reactive_mongo_crud.controller;

import com.springboot.spring_reactive_mongo_crud.dto.ProductDto;
import com.springboot.spring_reactive_mongo_crud.entity.Product;
import com.springboot.spring_reactive_mongo_crud.service.ProductService;
import com.springboot.spring_reactive_mongo_crud.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Flux<ProductDto> getProducts(){

        return productService.getProducts();

    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable String id){

        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductInPriceRange(@RequestParam("min") double min,@RequestParam("max") double max){


        return productService.getProductInRange(min,max);
    }

    @PostMapping("/save")
    public Mono<ProductDto> save(@RequestBody Mono<ProductDto> productDtoMono )
    {
       return productService.saveProduct(productDtoMono);
    }
@PutMapping("/update/{id}")
    public Mono<ProductDto> update(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){

        return productService.updateProduct(productDtoMono,id);

    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){

        return productService.deleteProduct(id);
    }

    @GetMapping("/byname/{name}")
    public Flux<ProductDto> findProductByname(@PathVariable  String name){

        return productService.findByName(name);
    }


    @GetMapping("/price/{price}")
    public Flux<ProductDto> findByrate(@PathVariable double price){

        return productService.getProductByPrice(price);
    }


}
