package com.kesizo.sample.kotlin.redis.rest.kotlinspringbootredis.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val productId : Long,
    val productName : String,
    val productPrice : Double
    ):Serializable // it needs to implement Serializable to be stored on Redis