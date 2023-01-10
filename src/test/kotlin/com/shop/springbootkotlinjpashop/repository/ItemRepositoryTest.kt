package com.shop.springbootkotlinjpashop.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.shop.springbootkotlinjpashop.constant.ItemSellStatus
import com.shop.springbootkotlinjpashop.entity.Item
import com.shop.springbootkotlinjpashop.entity.QItem
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.TestPropertySource
import org.thymeleaf.util.StringUtils

@SpringBootTest
@TestPropertySource(locations = ["classpath:application-test.yml"])
class ItemRepositoryTest @Autowired constructor (val itemRepository: ItemRepository) {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Test
    @DisplayName("상품 저장 테스트")
    fun createItemTest() {
        val item = Item(
            itemNm = "테스트 상품",
            price = 1000,
            itemDetail = "테스트 상품 상세 설명",
            itemSellStatus = ItemSellStatus.SELL,
            stockNumber = 100,
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

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    fun queryDslTest() {
        this.createItemList()
        val queryFactory = JPAQueryFactory(em)
        val qItem = QItem.item
        val query = queryFactory.selectFrom(qItem)
            .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
            .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
            .orderBy(qItem.price.desc())

        val itemList = query.fetch()

        for (item in itemList) {
            println("itemList = $itemList")
        }

    }

    fun createItemList2() {
        for (i in 1..5) {
            val item = Item(
                itemNm = "테스트 상품$i",
                price = 1000+i,
                itemDetail = "테스트 상품 상세 설명$i",
                itemSellStatus = ItemSellStatus.SELL,
                stockNumber = 100,
            )
            val savedItem: Item = itemRepository.save(item)
        }

        for (i in 6..10) {
            val item = Item(
                itemNm = "테스트 상품$i",
                price = 1000+i,
                itemDetail = "테스트 상품 상세 설명$i",
                itemSellStatus = ItemSellStatus.SELL,
                stockNumber = 0,
            )
            val savedItem: Item = itemRepository.save(item)
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    fun queryDslTest2() {
        this.createItemList2()
        val booleanBuilder = BooleanBuilder()
        val item = QItem.item
        val itemDetail = "테스트 상품 상세 설명"
        val price = 10003
        val itemSellStat = "SELL"

        booleanBuilder.and(item.itemDetail.like("%$itemDetail%"))
        booleanBuilder.and(item.price.gt(price))

        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL))
        }

        val pageable: Pageable = PageRequest.of(0, 5)
        val itemPageResult: Page<Item> = itemRepository.findAll(booleanBuilder, pageable)
        println("total elements :  = ${itemPageResult.totalElements}")

        val resultItemList: List<Item> = itemPageResult.content
        for (resultItem: Item in resultItemList) {
            println(resultItem)
        }

    }
}