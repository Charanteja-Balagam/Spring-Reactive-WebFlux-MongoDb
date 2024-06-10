package com.springboot.spring_reactive_mongo_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProductDto {


   private  String id;
    private String name;
    private  int qnty;
    private  double price;
}
