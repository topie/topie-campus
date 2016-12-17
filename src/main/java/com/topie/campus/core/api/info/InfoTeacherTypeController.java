package com.topie.campus.core.api.info;

import com.topie.campus.common.Option;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.TreeNode;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dao.TeacherMapper;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.TeacherType;
import com.topie.campus.core.service.IStudentService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.core.service.ITeacherTypeService;
import com.topie.campus.security.utils.SecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 2016/10/14.
 */
@Controller
@RequestMapping("/api/info/teacherType")
public class InfoTeacherTypeController {

    @Autowired
    private ITeacherTypeService iTeacherTypeService;
    
    @Autowired
    ITeacherService teacherService;
    
    @Autowired
    IStudentService studentService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(TeacherType teacherType,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<TeacherType> pageInfo = iTeacherTypeService.selectByPage(teacherType, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{teacherTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public Result load(@PathVariable(value = "teacherTypeId") Integer teacherTypeId) {
        TeacherType teacherType = iTeacherTypeService.selectByKey(teacherTypeId);
        return ResponseUtil.success(teacherType);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insert(TeacherType teacherType) {
        int result = iTeacherTypeService.insertSelective(teacherType);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(TeacherType teacherType) {
        int result = iTeacherTypeService.updateSelective(teacherType);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@RequestParam("teacherTypeId") Integer teacherTypeId) {
        int result = iTeacherTypeService.delete(teacherTypeId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/options", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Option> options(TeacherType teacherType) {
        List<Option> optionList = iTeacherTypeService.selectOptions(teacherType);
        return optionList;
    }

    @RequestMapping(value = "/treeNodes", method = RequestMethod.POST)
    @ResponseBody
    public Object treeNodes(TeacherType teacherType) {
        List<TreeNode> treeNodes = iTeacherTypeService.selectTreeNodes(teacherType);
        return treeNodes;
    }
    
    @RequestMapping(value = "/treeNodesByTeacherId")
    @ResponseBody
    public List<TreeNode> collegeTeacherType() {
    	Integer userId = SecurityUtil.getCurrentUserId();
    	Integer teacherId = teacherService.findTeacherIdByUserId(userId);
    	List<TreeNode> treeNodes = iTeacherTypeService.selectTreeNodesByTeacherId(teacherId);
        return treeNodes;
    }
    
    @RequestMapping(value = "/treeNodesAndStudentIdByTeacherId")
    @ResponseBody
    public List<TreeNode> TreeTeacherType() {
    	Integer userId = SecurityUtil.getCurrentUserId();
    	Integer teacherId = teacherService.findTeacherIdByUserId(userId);
    	List<TreeNode> treeNodes = iTeacherTypeService.selectTreeNodesByTeacherId(teacherId);
    	List<TreeNode> studentNodes = new ArrayList<>();
    	for(TreeNode t:treeNodes)
    	{
    		List<StudentSimpleDto> students = studentService.findStudentByTeacherIdAndTypeId(teacherId, t.getId());
    		t.setId(-t.getId());
    		for(StudentSimpleDto dto:students)
    		{
    			TreeNode node = new TreeNode();
    			node.setId(dto.getId());
    			node.setpId(t.getId());
    			node.setName(dto.getName());
    			studentNodes.add(node);
    		}
    	}
    	treeNodes.addAll(studentNodes);
        return treeNodes;
    }
}
