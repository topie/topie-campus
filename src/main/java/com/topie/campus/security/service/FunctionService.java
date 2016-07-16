package com.topie.campus.security.service;

import com.topie.campus.security.model.Function;
import com.topie.campus.basedao.service.IService;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
public interface FunctionService extends IService<Function> {
    int insertFunction(Function function);

    int updateFunction(Function function);

    Function findFuntionById(int id);

    int deleteFunctionById(int id);
}
