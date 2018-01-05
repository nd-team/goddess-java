package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.OtherSpendBO;
import com.bjike.goddess.fundcheck.dto.OtherSpendDTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherSpendCollectTO;
import com.bjike.goddess.fundcheck.to.OtherSpendTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他支出业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherSpendAPI {
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
     * 其他支出列表总条数
     */
    default Long count(OtherSpendDTO otherSpendDTO) throws SerException {
        return null;
    }

    /**
     * 一个其他支出
     *
     * @return class OtherSpendBO
     */
    default OtherSpendBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 其他支出
     *
     * @param otherSpendDTO 其他支出dto
     * @return class OtherSpendBO
     * @throws SerException
     */
    default List<OtherSpendBO> findListBack(OtherSpendDTO otherSpendDTO) throws SerException {
        return null;
    }

    /**
     * 添加其他支出
     *
     * @param otherSpendTO 其他支出数据to
     * @return class OtherSpendBO
     * @throws SerException
     */
    default OtherSpendBO insert(OtherSpendTO otherSpendTO) throws SerException {
        return null;
    }

    /**
     * 编辑其他支出
     *
     * @param otherSpendTO 其他支出数据to
     * @return class OtherSpendBO
     * @throws SerException
     */
    default OtherSpendBO edit(OtherSpendTO otherSpendTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除其他支出
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to
     * @return OtherSpendBO
     * @throws SerException
     */
    default LinkedHashMap<String,String> collect(OtherSpendCollectTO to) throws SerException {
        return null;
    }
    /**
     * 查询所有一级科目
     *
     * @return String
     * @throws SerException
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目查询二级科目
     *
     * @param firstSub 一级科目
     * @return String
     * @throws SerException
     */
    default List<String> listSubByFirst(String firstSub) throws SerException {
        return null;
    }

    /**
     * 根据一级二级查询三级科目
     *
     * @param firstSub  一级科目
     * @param secondSub 二级科目
     * @return String
     * @throws SerException
     */
    default List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return null;
    }
    /**
     * 导入
     *
     * @param otherSpendTOS 其他支出
     * @return class OtherSpendTO
     */
    default OtherSpendBO importExcel(List<OtherSpendTO> otherSpendTOS) throws SerException {
        return null;
    }
    /**
     * 导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;


}