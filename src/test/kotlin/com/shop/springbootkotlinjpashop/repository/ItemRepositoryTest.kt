package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.constant.ItemSellStatus
import com.shop.springbootkotlinjpashop.entity.Item
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDateTime

@SpringBootTest
@TestPropertySource(locations = ["classpath:application-test.yml"])
class ItemRepositoryTest @Autowired constructor(val itemRepository: ItemRepository) {

    @Test
    @DisplayName("상품 저장 테스트")
    fun createItemTest() {
        val item = Item(
            itemNm = "테스트 상품",
            price = 1000,
            itemDetail = "테스트 상품 상세 설명",
            itemSellStatus = ItemSellStatus.SELL,
            stockNumber = 100,
            regTime = LocalDateTime.now(),
            updateTime = LocalDateTime.now()
        )

        val savedItem: Item = itemRepository.save(item)

        println("savedItem = $savedItem")

    }
}