package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.controller

import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model.Product
import com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.service.ProductService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
class ProductController {

    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var productService: ProductService


    @GetMapping("products")
    fun fetchProducts(): ResponseEntity<List<Product>> {
        logger.info { "Accessing fetchProducts" }
        val products = productService.getProducts()
        return when (products.isEmpty()) {
            true -> {
                logger.debug { "product list is empty" }
                ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)
            }
            else -> {
                logger.debug { "product list is not empty" }
                ResponseEntity<List<Product>>(products, HttpStatus.OK)
            }
        }
    }

    @GetMapping("products/{id}")
    fun getProductById(@PathVariable id:Long): ResponseEntity<Product> {
        logger.info { "Accessing get productById with $id" }
        val currentProduct:Product? = productService.getProductById(id)

        return currentProduct?.let {
            ResponseEntity<Product>(currentProduct, HttpStatus.OK)
        } ?: run {
            // execute this block if null
            ResponseEntity<Product>(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("products")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
        logger.debug { "Adding a new product" }
        return ResponseEntity<Product>(productService.add(product), HttpStatus.CREATED)
    }

    @PutMapping("products/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product): ResponseEntity<Product> {
        logger.debug { "Updating a product" }
        val currentProduct:Product? = productService.getProductById(id)

        return when (currentProduct!=null){
            true -> {
                val newProduct:Product = Product(productId =currentProduct.productId,
                                                 productName=product.productName,
                                                 productPrice = product.productPrice)
                ResponseEntity<Product>(productService.add(newProduct), HttpStatus.OK)
            }
            else -> ResponseEntity<Product>(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("products/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Boolean> {
        logger.debug { "Deleting product with id $id" }
        return when(productService.delete(id)) {
            true -> ResponseEntity<Boolean>(true, HttpStatus.OK)
            else -> ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity<Boolean>(true, HttpStatus.OK)
    }
}