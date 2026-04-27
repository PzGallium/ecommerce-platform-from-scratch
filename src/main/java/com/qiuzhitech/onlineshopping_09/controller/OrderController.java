package com.qiuzhitech.onlineshopping_09.controller;

import com.qiuzhitech.onlineshopping_09.db.dao.OnlineShoppingCommodityDao;
import com.qiuzhitech.onlineshopping_09.db.dao.OnlineShoppingOrderDao;
import com.qiuzhitech.onlineshopping_09.db.po.OnlineShoppingCommodity;
import com.qiuzhitech.onlineshopping_09.db.po.OnlineShoppingOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class OrderController {

    @Resource
    private OnlineShoppingCommodityDao onlineShoppingCommodityDao;
    @Resource
    private OnlineShoppingOrderDao  onlineShoppingOrderDao;

    @GetMapping("/commodity/buy/{userID}/{itemID}")
    public String buyCommodity(@PathVariable Long userID, @PathVariable Long itemID, Map<String, Object> resultMap) {

        OnlineShoppingCommodity onlineShoppingCommodity = onlineShoppingCommodityDao.selectByCommodityId(itemID);
        if (onlineShoppingCommodity.getAvailableStock() > 0) {
            onlineShoppingCommodity.setAvailableStock(onlineShoppingCommodity.getAvailableStock() - 1);
            onlineShoppingCommodityDao.updateCommodity(onlineShoppingCommodity);
            OnlineShoppingOrder onlineShoppingOrder = OnlineShoppingOrder.builder()
                    .commodityId(onlineShoppingCommodity.getCommodityId())
                    .userId(userID)
                    .orderNo(UUID.randomUUID().toString())
                    .orderStatus(0)
                    .orderAmount(1L)
                    .createTime(new Date())
                    .build();
            onlineShoppingOrderDao.insertOrder(onlineShoppingOrder);
            resultMap.put("resultInfo", "success");
            resultMap.put("orderNo", onlineShoppingOrder.getOrderNo());
        } else {
            resultMap.put("resultInfo", "Out of Stock");
            resultMap.put("orderNo", "");
        }
        return "order_result";
    }

    @GetMapping("/commodity/orderQuery/{orderNum}")
    public String orderQuery(@PathVariable String orderNum, Map<String, Object> resultMap) {
        OnlineShoppingOrder order = onlineShoppingOrderDao.queryOrderByOrderNum(orderNum);
        resultMap.put("order", order);
        OnlineShoppingCommodity commodity = onlineShoppingCommodityDao.selectByCommodityId(order.getCommodityId());
        resultMap.put("commodity", commodity);
        return "order_check";
    }

    @GetMapping("/commodity/payOrder/{orderNum}")
    public String payOrder(@PathVariable String orderNum, Map<String, Object> resultMap) {
        OnlineShoppingOrder onlineShoppingOrder = onlineShoppingOrderDao.queryOrderByOrderNum(orderNum);
        onlineShoppingOrder.setOrderStatus(2);
        onlineShoppingOrder.setCreateTime(new Date());
        onlineShoppingOrderDao.updateOrder(onlineShoppingOrder);
        return orderQuery(orderNum, resultMap);
    }
}
