package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.CompanyFestivalTimeAPI;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import com.bjike.goddess.festival.vo.CompanyFestivalTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公司放假时间安排
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("festival/companyfestivaltime")
public class CompanyFestivalTimeAction {

    @Autowired
    private CompanyFestivalTimeAPI companyFestivalTimeAPI;

    /**
     *  公司放假时间安排列表总条数
     *
     * @param companyFestivalTimeDTO  公司放假时间安排信息dto
     * @des 获取所有公司放假时间安排信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws ActException {
        try {
            Long count = companyFestivalTimeAPI.countCompanyFestivalTime(companyFestivalTimeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司放假时间安排列表
     *
     * @param companyFestivalTimeDTO 公司放假时间安排信息dto
     * @des 获取所有公司放假时间安排信息
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @GetMapping("v1/listCompanyFestivalTime")
    public Result findListCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CompanyFestivalTimeVO> companyFestivalTimeVOList = BeanTransform.copyProperties(
                    companyFestivalTimeAPI.listCompanyFestivalTime(companyFestivalTimeDTO), CompanyFestivalTimeVO.class, true);
            return ActResult.initialize(companyFestivalTimeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司放假时间安排
     *
     * @param companyFestivalTimeTO 公司放假时间安排基本信息数据to
     * @des 添加公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCompanyFestivalTime(@Validated({CompanyFestivalTimeTO.TESTAddAndEdit.class}) CompanyFestivalTimeTO companyFestivalTimeTO, BindingResult bindingResult) throws ActException {
        try {
            CompanyFestivalTimeBO companyFestivalTimeBO1 = companyFestivalTimeAPI.addCompanyFestivalTime(companyFestivalTimeTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyFestivalTimeBO1,CompanyFestivalTimeVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司放假时间安排
     *
     * @param companyFestivalTimeTO 公司放假时间安排基本信息数据bo
     * @des 添加公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCompanyFestivalTime(@Validated({CompanyFestivalTimeTO.TESTAddAndEdit.class}) CompanyFestivalTimeTO companyFestivalTimeTO) throws ActException {
        try {
            CompanyFestivalTimeBO companyFestivalTimeBO1 = companyFestivalTimeAPI.editCompanyFestivalTime(companyFestivalTimeTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyFestivalTimeBO1,CompanyFestivalTimeVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司放假时间安排信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCompanyFestivalTime(@PathVariable String id) throws ActException {
        try {
            companyFestivalTimeAPI.deleteCompanyFestivalTime(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 查看公司放假时间安排
     *
     * @param companyFestivalTimeDTO 公司放假时间安排dto
     * @des 查看公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @GetMapping("v1/getCompanyFestivalTimeDetail")
    public Result getCompanyFestivalTimeDetail (@Validated(CompanyFestivalTimeDTO.TESTGetOne.class) CompanyFestivalTimeDTO companyFestivalTimeDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CompanyFestivalTimeVO> companyFestivalTimeVOS = BeanTransform.copyProperties(
                    companyFestivalTimeAPI.getCompanyFestivalTime(companyFestivalTimeDTO), CompanyFestivalTimeVO.class, true);
            return ActResult.initialize(companyFestivalTimeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}