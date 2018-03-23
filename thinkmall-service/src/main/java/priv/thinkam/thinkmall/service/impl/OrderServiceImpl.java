package priv.thinkam.thinkmall.service.impl;

import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.dao.mapper.OrderMapper;
import priv.thinkam.thinkmall.dao.entity.Order;
import priv.thinkam.thinkmall.dao.entity.OrderExample;
import priv.thinkam.thinkmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderService实现
 *
 * @author thinkam
 * @date 18-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, OrderExample> implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderMapper orderMapper;

}