package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.service

import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model.Product

interface ProductService {
    fun getProducts():List<Product>
    fun getProductById(id:Long):Product?
    fun add(product: Product):Product
    fun delete(id:Long):Boolean
    fun update(product: Product):Product
}