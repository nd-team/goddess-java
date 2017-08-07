package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;

import java.util.List;

/**
* 薪资管理薪资资料业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理薪资资料业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryInformationAPI  {
    /**
     * 查询列表
     * @param dto
     * @throws SerException
     */
    default List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException{
        return null;
    }

    /**
     * 添加
     * @param to
     * @throws SerException
     */
    default SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException{
        return null;
    }

    /**
     * 修改
     * @param to
     * @throws SerException
     */
    default SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException{
        return null;
    }

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    default void deleteSalaryInformation(String id) throws SerException{
        return;
    }

    /**
     * 导入
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<SalaryInformationTO> toList) throws SerException{
        return;
    };

    /**
     * 导出
     * @param to
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ExportSalaryInformationTO to) throws SerException;

    /**
     * 获取所有的计薪周期开始时间和计薪周期结束时间
     * @throws SerException
     */
    default List<String> findTime() throws SerException{
        return null;
    }

    /**
     * 导出Excel模板
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
 }