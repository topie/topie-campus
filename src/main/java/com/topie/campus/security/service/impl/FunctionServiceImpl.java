package com.topie.campus.security.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.TreeNode;
import com.topie.campus.security.dao.FunctionMapper;
import com.topie.campus.security.model.Function;
import com.topie.campus.security.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
@Service("functionService")
public class FunctionServiceImpl extends BaseService<Function> implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

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

    @Override
    public List<TreeNode> getFunctionTreeNodes(Function function) {
        return functionMapper.selectFunctionTreeNodes(function);
    }
}
