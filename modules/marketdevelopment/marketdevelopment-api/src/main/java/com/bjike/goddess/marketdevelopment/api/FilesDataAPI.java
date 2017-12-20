package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.FilesDataBO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;

/**
 * 阶段表头数据业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FilesDataAPI {

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 查询表头的数据
     *
     * @return
     * @throws SerException
     */
    default FilesDataBO findFiles() throws SerException {
        return null;
    }
}