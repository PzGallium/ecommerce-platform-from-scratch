package com.qiuzhitech.onlineshopping_09.service;

import com.qiuzhitech.onlineshopping_09.db.dao.OnlineShoppingCommodityDao;
import com.qiuzhitech.onlineshopping_09.db.po.OnlineShoppingCommodity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchService {
    @Resource
    OnlineShoppingCommodityDao onlineShoppingCommodityDao;

    public List<OnlineShoppingCommodity> searchCommodityByDB(String keyword) {
        return onlineShoppingCommodityDao.searchCommodityByKeyword(keyword);
    }
}

