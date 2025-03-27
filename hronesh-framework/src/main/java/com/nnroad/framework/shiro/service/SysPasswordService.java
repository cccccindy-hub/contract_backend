package com.nnroad.framework.shiro.service;


import com.nnroad.common.constant.ShiroConstants;
import com.nnroad.common.core.domain.entity.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

public class SysPasswordService {
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

//    public void validate(SysUser user, String password) {
//        String loginName = user.getLoginName();
//
//        AtomicInteger retryCount = loginRecordCache.get(loginName);
//
//        if (retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            loginRecordCache.put(loginName, retryCount);
//        }
//        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
//            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
//        }
//
//        if (!matches(user, password)) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
//            loginRecordCache.put(loginName, retryCount);
//            throw new UserPasswordNotMatchException();
//        } else {
//            clearLoginRecordCache(loginName);
//        }
//    }
//
//    public boolean matches(SysUser user, String newPassword) {
//        // 输入匹配的密码和验证码都可以正常登陆系统
//        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()))
//                || newPassword.equals(user.getDynamicCode());
//    }
//
//    public void clearLoginRecordCache(String loginName) {
//        loginRecordCache.remove(loginName);
//    }

    public String encryptPassword(String loginName, String password, String salt) {
        return new Md5Hash(loginName + password + salt).toHex();
    }
}

