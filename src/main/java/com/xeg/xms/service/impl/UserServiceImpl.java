package com.xeg.xms.service.impl;

import com.xeg.xms.entity.User;
import com.xeg.xms.mapper.UserMapper;
import com.xeg.xms.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuzeyun
 * @since 2024-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
