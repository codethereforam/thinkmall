package priv.thinkam.thinkmall.service.impl;

import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.dao.mapper.ShippingAddressMapper;
import priv.thinkam.thinkmall.dao.entity.ShippingAddress;
import priv.thinkam.thinkmall.dao.entity.ShippingAddressExample;
import priv.thinkam.thinkmall.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ShippingAddressService实现
 *
 * @author thinkam
 * @date 18-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class ShippingAddressServiceImpl extends BaseServiceImpl<ShippingAddressMapper, ShippingAddress, ShippingAddressExample> implements ShippingAddressService {

    private static Logger logger = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);

    @Autowired
    ShippingAddressMapper shippingAddressMapper;

}