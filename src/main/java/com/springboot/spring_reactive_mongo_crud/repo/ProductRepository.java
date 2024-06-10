package com.springboot.spring_reactive_mongo_crud.repo;

import com.springboot.spring_reactive_mongo_crud.dto.ProductDto;
import com.springboot.spring_reactive_mongo_crud.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product,String>{


    Flux<ProductDto> findBypriceBetween(Range<Double> priceRange);

    Flux<ProductDto> findByName(String name);

    @Query("{'price' : ?0}")
    Flux<ProductDto> getByRate(double price);
}
