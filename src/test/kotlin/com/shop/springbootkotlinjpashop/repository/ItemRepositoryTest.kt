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

    fun createItemList() {
        for (i in 1..10) {
            val item = Item(
                itemNm = "테스트 상품$i",
                price = 1000+i,
                itemDetail = "테스트 상품 상세 설명$i",
                itemSellStatus = ItemSellStatus.SELL,
                stockNumber = 100,
                regTime = LocalDateTime.now(),
                updateTime = LocalDateTime.now()
            )
            val savedItem: Item = itemRepository.save(item)
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    fun findByItemNmTest() {
        this.createItemList()
        val itemList = itemRepository.findByItemNm("테스트 상품1")
        for (item in itemList) {
            println("itemList = $itemList")
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    fun findByItemNmOrItemDetailTest() {
        this.createItemList()
        val itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5")
        for (item in itemList) {
            println("itemList = $itemList")
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    fun findByPriceLessThanTest() {
        this.createItemList()
        val itemList = itemRepository.findByPriceLessThan(10005)
        for (item in itemList) {
            println("itemList = $itemList")
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    fun findByPriceLessThanOrderByPriceDescTest() {
        this.createItemList()
        val itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005)
        for (item in itemList) {
            println("itemList = $itemList")
        }
    }
}