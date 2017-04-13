package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.service.StaffRecordsSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工档案业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffRecordsApiImpl")
public class StaffRecordsApiImpl implements StaffRecordsAPI {

    @Autowired
    private StaffRecordsSer staffRecordsSer;

    @Override
    public void upload() throws SerException {
        staffRecordsSer.upload();
    }
}