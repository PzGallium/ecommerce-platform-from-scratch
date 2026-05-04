package com.qiuzhitech.onlineshopping_09.service;

import com.qiuzhitech.onlineshopping_09.db.dao.OnlineShoppingCommodityDao;
import com.qiuzhitech.onlineshopping_09.db.dao.OnlineShoppingOrderDao;
import com.qiuzhitech.onlineshopping_09.db.po.OnlineShoppingCommodity;
import com.qiuzhitech.onlineshopping_09.db.po.OnlineShoppingOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
@Slf4j
@Service
public class OrderService {
    @Resource
    private OnlineShoppingOrderDao onlineShoppingOrderDao;
    @Resource
    private OnlineShoppingCommodityDao onlineShoppingCommodityDao;

    public OnlineShoppingOrder CreateOrderSQL(Long userID, Long itemID) {
        int res = onlineShoppingCommodityDao.deductStock(itemID);
        if (res > 0) {
            return CreateOrder(userID, itemID);
        } else {
            log.error("Order Is Not Available");
            return null;
        }
    }


    public OnlineShoppingOrder PlaceOrderOriginal (Long userID, Long itemID) {
        OnlineShoppingCommodity onlineShoppingCommodity = onlineShoppingCommodityDao.selectByCommodityId(itemID);
        if (onlineShoppingCommodity.getAvailableStock() > 0) {
            log.info("Order is placed");
            return CreateOrder(userID, itemID);
        } else {
            log.error("Order Not Available");
            return null;
        }
    }

    public OnlineShoppingOrder CreateOrder(Long userID, Long itemID) {
        OnlineShoppingOrder order = OnlineShoppingOrder.builder()
                .commodityId(itemID)
                .userId(userID)
                .orderNo(UUID.randomUUID().toString())
                .orderStatus(1)
                .orderAmount(1L)
                .createTime(new Date())
                .build();
        onlineShoppingOrderDao.insertOrder(order);
        return order;
    }

}

