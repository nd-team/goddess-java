package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.SchemeImplementBO;
import com.bjike.goddess.interiorrecommend.dto.SchemeImplementDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.SchemeImplementTO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;

import java.util.List;
import java.util.Set;

/**
* 内部推荐方案实施业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:29 ]
* @Description:	[ 内部推荐方案实施业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SchemeImplementAPI  {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 我要推荐
     */
    void add(SchemeImplementTO to) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void modify(SchemeImplementTO to) throws SerException;

    /**
     * 列表查询
     */
    List<SchemeImplementBO> pageList(SchemeImplementDTO dto) throws SerException;

    /**
     * 列表总条数
     */
    Long count(SchemeImplementDTO dto) throws SerException;

    /**
     * 根据id查询单条数据
     */
    SchemeImplementBO findOne(String id) throws SerException;

    /**
     * 根据方案名称和岗位获取推荐奖金额
     */
    Integer findAward(String type,String recommendPosition) throws SerException;

    /**
     * 根据方案名称查找推荐岗位
     */
    String findPosition(String type) throws SerException;

    /**
     * 获取所有入职员工姓名
     */
    List<EntryBasicInfoBO> findEntry() throws SerException;


}