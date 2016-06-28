package com.orange.security.service.impl;

import com.orange.security.model.Function;
import com.orange.security.service.FunctionService;
import com.orange.basedao.service.impl.BaseService;

import org.springframework.stereotype.Service;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
@Service("functionService")
public class FunctionServiceImpl extends BaseService<Function>
        implements FunctionService {
    @Override
    public int insertFunction(Function function) {
        return getMapper().insert(function);
    }

    @Override
    public int updateFunction(Function function) {
        return getMapper().updateByPrimaryKey(function);
    }

    @Override
    public Function findFuntionById(int id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int deleteFunctionById(int id) {
        return getMapper().deleteByPrimaryKey(id);
    }
}
