package priv.thinkam.thinkmall.service.impl;

import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.dao.mapper.ProductMapper;
import priv.thinkam.thinkmall.dao.entity.Product;
import priv.thinkam.thinkmall.dao.entity.ProductExample;
import priv.thinkam.thinkmall.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductService实现
 *
 * @author thinkam
 * @date 18-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product, ProductExample> implements ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductMapper productMapper;

}