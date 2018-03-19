package priv.thinkam.thinkmall.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.dao.AdministratorDAO;
import priv.thinkam.thinkmall.model.Administrator;
import priv.thinkam.thinkmall.service.AdministratorService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author thinkam
 * @date 2018/03/19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdministratorServiceImpl implements AdministratorService {
    @Resource
    private AdministratorDAO administratorDAO;

    @Override
    public List<Administrator> listByUsername(String username) {
        return administratorDAO.listByUsername(username);
    }
}
