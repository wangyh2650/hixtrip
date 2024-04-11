package com.hixtrip.sample.domain.inventory.repository;

/**
 *
 */
public interface InventoryRepository {

    Integer getInventory(String skuId);

    /**
     * 减库存
     * @param skuId
     * @param sellableQuantity 商品可售库存
     * @param withholdingQuantity 预售库存
     * @return
     */
    Boolean decrInventory(String skuId, Long sellableQuantity, Long withholdingQuantity);
}
