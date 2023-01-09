package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.ItemSellStatus
import com.shop.springbootkotlinjpashop.repository.ItemRepository
import com.shop.springbootkotlinjpashop.repository.OrderRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
@TestPropertySource(locations = ["classpath:application-test.yml"])
class OrderTest {
    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var itemRepository: ItemRepository

    @PersistenceContext
    lateinit var em: EntityManager

    fun createItem(): Item {
        return Item(
            itemNm = "테스트 상품",
            price = 10000,
            itemDetail = "상세설명",
            itemSellStatus = ItemSellStatus.SELL,
            stockNumber = 100,
            regTime = LocalDateTime.now(),
            updateTime = LocalDateTime.now()
        )
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    fun cascadeTest() {
        val item = this.createItem();
        itemRepository.save(item);

        val orderItem = OrderItem(
            item = item,
            count = 10,
            orderPrice = 1000,
            order = null,
            regTime = LocalDateTime.now(),
            updateTime = LocalDateTime.now()
        )
    }

}
