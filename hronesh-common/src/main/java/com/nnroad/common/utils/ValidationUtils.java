package com.nnroad.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    private static final String LINE_ERROR_MSG = "Line %s Data errors：%s";

    public static <T> String validate(List<T> dataList, boolean matchAll) {
        if (CollUtil.isEmpty(dataList)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            T t = dataList.get(i);
            String errors = validate(t);
            if (StrUtil.isNotBlank(errors)) {
                errors = String.format(LINE_ERROR_MSG, i + 1, errors);
                if (!matchAll) {
                    return errors;
                }
                sb.append(errors);
            }
        }
        return sb.toString();
    }

    public static <T> String validate(T data) {
        Set<ConstraintViolation<T>> violations = validator.validate(data);
        return Optional.ofNullable(violations).orElse(new HashSet<>()).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
    }

}