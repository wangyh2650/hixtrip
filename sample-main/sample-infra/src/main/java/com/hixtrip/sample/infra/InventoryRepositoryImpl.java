package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 当前库存
     */
    private static final String SKU_INVENTORY_KEY = "SKU_INVENTORY:%s";
    /**
     * 销量
     */
    private static final String SKU_INVENTORY_OCCUPIED_KEY = "SKU_INVENTORY_OCCUPIED:%s";

    /**
     * 使用LUA脚本 管理库存实现
     * 购买减库存 增加销量
     */
    private static final RedisScript<Long> INVENTORY_OCCUPIED_SCRIPT = RedisScript.of(
            "local sellable = tonumber(ARGV[1]) " +
                    "local withholding = tonumber(ARGV[2]) " +
                    "if (redis.call('exists', KEYS[1]) == 0) then " +
                    "    redis.call('set', KEYS[1], 0) " +
                    "end " +
                    "local occupied = tonumber(redis.call('get', KEYS[1])) " +
                    "local val = sellable - (occupied + withholding) " +
                    "if (val >= 0) then " +
                    "    redis.call('set', KEYS[2], val) " +
                    "    local num = tonumber(redis.call('incrby', KEYS[1], withholding)) " +
                    "    return 1 " +
                    "end " +
                    "return -1", Long.class);

    @Override
    public Integer getInventory(String skuId) {
        Object num = redisTemplate.opsForValue().get(String.format(SKU_INVENTORY_KEY, skuId));
        return Integer.valueOf(Optional.ofNullable(num).orElse(0).toString());
    }


    @Override
    public Boolean decrInventory(String skuId, Long sellableQuantity, Long withholdingQuantity) {
        List<String> keys = new ArrayList<>();
        keys.add(String.format(SKU_INVENTORY_OCCUPIED_KEY, skuId));
        keys.add(String.format(SKU_INVENTORY_KEY, skuId));
        // occupiedQuantity 正数 减库存；负数 加库存
        Long stock = redisTemplate.execute(INVENTORY_OCCUPIED_SCRIPT, keys, sellableQuantity, withholdingQuantity);
        return stock > -1;
    }
}
