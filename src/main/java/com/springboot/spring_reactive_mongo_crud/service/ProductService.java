package com.springboot.spring_reactive_mongo_crud.service;

import com.springboot.spring_reactive_mongo_crud.dto.ProductDto;
import com.springboot.spring_reactive_mongo_crud.entity.Product;
import com.springboot.spring_reactive_mongo_crud.repo.ProductRepository;
import com.springboot.spring_reactive_mongo_crud.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){

        return productRepository.findAll().map(AppUtils::entityToDto);

    }

    public Mono<Product> getProduct(String id){
        return productRepository.findById(id);
    }

    public Flux<ProductDto> getProductInRange(double min,double max){

        return productRepository.findBypriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono )
    {
        return productDtoMono.map(AppUtils::DtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){

      return   productRepository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::DtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteProduct(String id){

        return productRepository.deleteById(id);
    }

    public Flux<ProductDto> findByName(String name){

        return productRepository.findByName(name);
    }

    public Flux<ProductDto> getProductByPrice(double price){

        return productRepository.getByRate(price);
    }
}
