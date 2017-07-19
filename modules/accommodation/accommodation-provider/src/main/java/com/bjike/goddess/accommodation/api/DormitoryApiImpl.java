package com.bjike.goddess.accommodation.api;

import com.bjike.goddess.accommodation.bo.DormitoryBO;
import com.bjike.goddess.accommodation.dto.DormitoryDTO;
import com.bjike.goddess.accommodation.entity.Dormitory;
import com.bjike.goddess.accommodation.service.DormitorySer;
import com.bjike.goddess.accommodation.to.DormitoryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 10:16]
 * @Description: [宿舍信息借口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("dormitoryApiImpl")
public class DormitoryApiImpl implements DormitoryAPI{
    @Autowired
    private DormitorySer dormitorySer;
    @Override
    public DormitoryBO insertDormitory(DormitoryTO dormitoryTO) throws SerException {
        return dormitorySer.insertDormitory(dormitoryTO);
    }
    @Override
    public DormitoryBO editDormitory(DormitoryTO dormitoryTO) throws SerException {
        return dormitorySer.editDormitory(dormitoryTO);
    }

    @Override
    public void removeDormitory(String id) throws SerException {
        dormitorySer.removeDormitory(id);
    }

    @Override
    public List<DormitoryBO> findListDormitory(DormitoryDTO dormitoryDTO) throws SerException {
        return dormitorySer.findListDormitory(dormitoryDTO);
    }

    /**
     * 导入数据
     */
    public void importFile() throws SerException {
        dormitorySer.importFile();
    }
    /**
     * 导出宿舍信息明细
     */
    public String exportExcel(String area)throws SerException {
        return dormitorySer.exportExcel(area);
    }

    @Override
    //chenjunhao
    public Set<String> allDormitoryAddress() throws SerException {
        return dormitorySer.allDormitoryAddress();
    }

    @Override
    //chenjunhao
    public String findContact(String dormitoryAddress) throws SerException {
        return dormitorySer.findContact(dormitoryAddress);
    }
}
