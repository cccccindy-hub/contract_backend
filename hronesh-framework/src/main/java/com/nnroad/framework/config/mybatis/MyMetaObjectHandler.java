package com.nnroad.framework.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.nnroad.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATE_USER = "createUser";
    private static final String UPDATE_USER = "updateUser";

    /**
     * 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object data = getFieldValByName(CREATE_TIME, metaObject);
        if (Objects.isNull(data)) {
            this.setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        }
        data = getFieldValByName(UPDATE_TIME, metaObject);
        if (Objects.isNull(data)) {
            this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
        data = getFieldValByName(CREATE_USER, metaObject);
        if (Objects.isNull(data)) {
            this.setFieldValByName(CREATE_USER, SecurityUtils.getUserId(), metaObject);
        }
    }

    /**
     * 更新时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object data = getFieldValByName(UPDATE_TIME, metaObject);
        if (Objects.isNull(data)) {
            this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
        data = getFieldValByName(UPDATE_USER, metaObject);
        if (Objects.isNull(data)) {
            this.setFieldValByName(UPDATE_USER, SecurityUtils.getUserId(), metaObject);
        }
    }

}
