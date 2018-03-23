package priv.thinkam.thinkmall.service.impl;

import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.dao.mapper.OrderItemMapper;
import priv.thinkam.thinkmall.dao.entity.OrderItem;
import priv.thinkam.thinkmall.dao.entity.OrderItemExample;
import priv.thinkam.thinkmall.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderItemService实现
 *
 * @author thinkam
 * @date 18-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemMapper, OrderItem, OrderItemExample> implements OrderItemService {

    private static Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    @Autowired
    OrderItemMapper orderItemMapper;

}