package priv.thinkam.thinkmall.service.impl;

import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.dao.mapper.AdministratorMapper;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;
import priv.thinkam.thinkmall.service.AdministratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AdministratorService实现
 *
 * @author thinkam
 * @date 18-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class AdministratorServiceImpl extends BaseServiceImpl<AdministratorMapper, Administrator, AdministratorExample> implements AdministratorService {

    private static Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    @Autowired
    AdministratorMapper administratorMapper;

    @Override
    public List<Administrator> listByUsername(String username) {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andUsernameEqualTo(username);
        return administratorMapper.selectByExample(example);
    }
}