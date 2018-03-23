package priv.thinkam.thinkmall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.common.annotation.BaseService;
import priv.thinkam.thinkmall.common.base.BaseServiceImpl;
import priv.thinkam.thinkmall.common.exception.UsernamePasswordNotMatchException;
import priv.thinkam.thinkmall.common.util.Md5Util;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;
import priv.thinkam.thinkmall.dao.mapper.AdministratorMapper;
import priv.thinkam.thinkmall.service.AdministratorService;

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
    public Administrator validate(String username, String password) throws UsernamePasswordNotMatchException {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Administrator> administrators = administratorMapper.selectByExample(example);
        for (Administrator administrator : administrators) {
            String salt = administrator.getSalt();
            String realDigestPassword = administrator.getPassword();
            String digestPassword = Md5Util.digest(password + salt);
            logger.debug("realDigestPassword: {}, digestPassword: {}", realDigestPassword, digestPassword);
            if (digestPassword != null && digestPassword.equalsIgnoreCase(realDigestPassword)) {
                return administrator;
            }
        }
        throw new UsernamePasswordNotMatchException();
    }
}