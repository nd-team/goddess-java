package com.bjike.goddess.attendance.api.overtime;

import com.bjike.goddess.attendance.bo.overtime.ExtralOverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDayDTO;
import com.bjike.goddess.attendance.service.overtime.ExtralOverWorkSer;
import com.bjike.goddess.attendance.to.overtime.ExtralOverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.ExtralOverWorkDayVO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补班设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("extralOverWorkApiImpl")
public class ExtralOverWorkApiImpl implements ExtralOverWorkAPI {

    @Autowired
    private ExtralOverWorkSer extralOverWorkSer;

    @Override
    public Long countExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        return extralOverWorkSer.countExtralOverWork( extralOverWorkDTO );
    }

    @Override
    public ExtralOverWorkBO getOneById(String id) throws SerException {
        return extralOverWorkSer.getOneById(id);
    }

    @Override
    public List<ExtralOverWorkBO> listExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        return extralOverWorkSer.listExtralOverWork(extralOverWorkDTO);
    }

    @Override
    public ExtralOverWorkBO addExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        return extralOverWorkSer.addExtralOverWork(extralOverWorkTO);
    }

    @Override
    public ExtralOverWorkBO editExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        return extralOverWorkSer.editExtralOverWork(extralOverWorkTO);
    }

    @Override
    public void deleteExtralOverWork(String id) throws SerException {
        extralOverWorkSer.deleteExtralOverWork(id);
    }

    @Override
    public ExtralOverWorkDayVO caculateTime(ExtralOverWorkDayDTO extralOverWorkDayDTO) throws SerException {
        return extralOverWorkSer.caculateTime(extralOverWorkDayDTO);
    }
}