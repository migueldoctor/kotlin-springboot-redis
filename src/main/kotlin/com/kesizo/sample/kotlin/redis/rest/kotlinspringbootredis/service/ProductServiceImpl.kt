package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.service

import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model.Product
import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.lang.Exception

@Service("ProductService")
class ProductServiceImpl:ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Cacheable("products")
    override fun getProducts(): List<Product> = productRepository.findAll()
    override fun getProductById(id: Long): Product? = productRepository.findById(id).orElse(null)

    @CacheEvict("products", allEntries=true) //it reset the cache
    override fun add(product: Product): Product = productRepository.save(product)

    @CacheEvict("products", allEntries=true) //it reset the cache
    override fun delete(id: Long): Boolean = try { productRepository.deleteById(id); true} catch (e:Exception) { false }

    @CacheEvict("products", allEntries=true) //it reset the cache
    override fun update(product: Product): Product = productRepository.save(product)

}