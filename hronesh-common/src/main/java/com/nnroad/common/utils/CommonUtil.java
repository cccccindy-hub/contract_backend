package com.nnroad.common.utils;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

    public static <T> List<T> objToArr(Object val, Class<T> clazz) {

        return ObjectUtil.isNotEmpty(val) ? JSONArray.parseArray(JSONObject.toJSONString(val), clazz) : new ArrayList<>();
    }
}
