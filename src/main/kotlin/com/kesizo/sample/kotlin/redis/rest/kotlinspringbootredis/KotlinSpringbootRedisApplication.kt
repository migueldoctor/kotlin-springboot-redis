package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis

import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model.Product
import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.repository.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean


@EnableCaching
@SpringBootApplication
class KotlinSpringbootRedisApplication {

	@Bean
	fun init(repository: ProductRepository) = CommandLineRunner {
		repository.save(Product(1,"car",32000.2))
		repository.save(Product(2,"house",420000.8))
		repository.save(Product(3,"t-shirt",35.5))
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinSpringbootRedisApplication>(*args)
}

