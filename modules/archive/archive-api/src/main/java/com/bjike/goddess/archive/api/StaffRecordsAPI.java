package com.bjike.goddess.archive.api;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * 员工档案业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffRecordsAPI {

    /**
     * 上传数据
     *
     * @throws SerException
     */
    void upload() throws SerException;

}