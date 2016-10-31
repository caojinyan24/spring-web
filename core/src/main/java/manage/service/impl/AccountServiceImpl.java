package manage.service.impl;

import manage.dao.AccountMapper;
import manage.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jinyancao on 10/31/16.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    public boolean vefifyAccount(String userName, String password) {
        return accountMapper.queryAccountCount(userName, password) == 1;
    }
}
