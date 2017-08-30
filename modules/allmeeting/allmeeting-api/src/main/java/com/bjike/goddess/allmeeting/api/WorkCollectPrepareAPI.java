package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.WorkCollectPrepareBO;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 工作汇总议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkCollectPrepareAPI {
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
     * 根据id来查询工作汇总议题准备信息
     * @param id
     * @return
     * @throws SerException
     */
    WorkCollectPrepareBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(WorkCollectPrepareDTO dto) throws SerException;

    /**
     * 查询列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<WorkCollectPrepareBO> pageList(WorkCollectPrepareDTO dto) throws SerException;

    /**
     * 解冻
     * @param id
     * @throws SerException
     */
    void unfreeze(String id) throws SerException;

    /**
     * 冻结
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 编辑
     * @param to
     * @return
     * @throws SerException
     */
    WorkCollectPrepareBO edit(WorkCollectPrepareTO to) throws SerException;

    /**
     * 添加
     * @param to
     * @return
     * @throws SerException
     */
    WorkCollectPrepareBO add(WorkCollectPrepareTO to) throws SerException;
}