package com.topie.campus.security.dao;

import com.topie.campus.common.TreeNode;
import com.topie.campus.security.model.Function;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FunctionMapper extends Mapper<Function> {

    public List<TreeNode> selectFunctionTreeNodes(Function function);

    List<Function> findFunctionList(Function function);
}
