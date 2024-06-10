package com.springboot.spring_reactive_mongo_crud;

import com.springboot.spring_reactive_mongo_crud.controller.ProductController;
import com.springboot.spring_reactive_mongo_crud.dto.ProductDto;
import com.springboot.spring_reactive_mongo_crud.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringReactiveMongoCrudApplicationTests {


	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService service;

	@Test
	public void addProductTest(){

		Mono<ProductDto> productDtoMono= Mono.just(new ProductDto("102","mobile",1,1000));
		when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().
				uri("/products/save")
				.body(Mono.just(productDtoMono),ProductDto.class)
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	public void getProductsTest(){

		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102","mobile",1,1000),
				new ProductDto("102","mobile",1,1000));
		when(service.getProducts()).thenReturn(productDtoFlux);

		Flux<ProductDto> responsebody = webTestClient.get().uri("/products/all")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();
		StepVerifier.create(responsebody)
				.expectSubscription()
				.expectNext(new ProductDto("102","mobile",1,1000))
				.expectNext(new ProductDto("102","mobile",1,1000))
				.verifyComplete();
	}
	@Test
	public void getProductsByNameTest(){

		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102","mobile",1,1000)
				);
		when(service.findByName("mobile")).thenReturn(productDtoFlux);

		Flux<ProductDto> responsebody = webTestClient.get().uri("/products/byname/mobile")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();
		StepVerifier.create(responsebody)
				.expectSubscription()
				.expectNext(new ProductDto("102","mobile",1,1000))
				.verifyComplete();
	}

	@Test
	public void deleteProductTest(){

		given(service.deleteProduct(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/products/delete/102")
				.exchange()
				.expectStatus().isOk();
	}


}
