package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.entity.DormitoryInfo;
import com.bjike.goddess.checkhost.service.DormitoryInfoSer;
import com.bjike.goddess.checkhost.to.DormitoryInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宿舍信息管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dormitoryInfoApiImpl")
public class DormitoryInfoApiImpl implements DormitoryInfoAPI {
    @Autowired
    private DormitoryInfoSer dormitoryInfoSer;
    @Override
    public Long countDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        return dormitoryInfoSer.countDormitoryInfo(dormitoryInfoDTO);
    }

    @Override
    public DormitoryInfoBO getOne(String id) throws SerException {
        return dormitoryInfoSer.getOne(id);
    }
    @Override
    public List<DormitoryInfoBO> findListDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        return dormitoryInfoSer.findListDormitoryInfo(dormitoryInfoDTO);
    }

    @Override
    public DormitoryInfoBO insertDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        return dormitoryInfoSer.insertDormitoryInfo(dormitoryInfoTO);
    }

    @Override
    public DormitoryInfoBO editDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        return dormitoryInfoSer.editDormitoryInfo(dormitoryInfoTO);
    }

    @Override
    public void removeDormitoryInfo(String id) throws SerException {
        dormitoryInfoSer.removeDormitoryInfo(id);
    }


}