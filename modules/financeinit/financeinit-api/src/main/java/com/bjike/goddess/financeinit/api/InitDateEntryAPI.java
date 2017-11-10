package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.InitDateEntryBO;
import com.bjike.goddess.financeinit.dto.InitDateEntryDTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.InitDateEntryTO;

import java.util.List;

/**
 * 初始化数据录入业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InitDateEntryAPI {
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
     * 初始化数据列表总条数
     */
    default Long countInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取初始化数据
     *
     * @return class InitDateEntryBO
     */
    default InitDateEntryBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 初始化数据列表
     *
     * @return class InitDateEntryBO
     */
    default List<InitDateEntryBO> listInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param initDateEntryTO 初始化数据
     * @return class InitDateEntryBO
     */
    default InitDateEntryBO editInit(InitDateEntryTO initDateEntryTO) throws SerException {
        return null;
    }

    /**
     * 试算平衡
     *
     * @return
     * @throws SerException
     */
    default String trialBalance() throws SerException {
        return null;
    }
    /**
     * 根据会计科目名称获取方向和期初余额
     * @return
     * @throws SerException
     */
    default InitDateEntryBO findByName(String name) throws SerException{
        return null;
    }
}