package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.repository

import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>
