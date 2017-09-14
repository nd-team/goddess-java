package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.to.DepartmentDetailTO;

import java.util.List;

/**
 * 部门详细业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DepartmentDetailSer extends Ser<DepartmentDetail, DepartmentDetailDTO> {

    /**
     * 部门详细列表
     *
     * @param dto 部门详细数据传输
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> view(DepartmentDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据体系ID查询部门详细
     *
     * @param hierarchy_id 体系ID
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByHierarchy(String hierarchy_id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结部门
     *
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 根据部门id集合查询部门详细信息
     *
     * @param ids 部门id集合
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        return null;
    }

    /**
     * 根据ID获取部门详细传输对象
     *
     * @param id 部门项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO findBOById(String id) throws SerException {
        return null;
    }

    /**
     * 保存部门/项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改部门/项目组详细信息
     *
     * @param to 部门/项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        return null;
    }


    /**
     * 冻结
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 刪除
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取部门项目组详细数据
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 查询所有地区
     *
     * @return
     * @throws SerException
     */
    default List<AreaBO> findArea() throws SerException {
        return null;
    }

    /**
     * 根据地区查询
     *
     * @param area
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByArea(String area) throws SerException {
        return null;
    }

    /**
     * 根据id查询部门项目组详细数据
     *
     * @param ids 部门项目组详细数据id数组
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findByIds(String... ids) throws SerException {
        return null;
    }

    /**
     * 查询未冻结部门选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }

    /**
     * 获取全部部门选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findAllOpinion() throws SerException {
        return null;
    }

    /**
     * 真实编号
     *
     * @param to
     * @return
     * @throws SerException
     */
    String number(DepartmentDetailTO to) throws SerException;

    /**
     * 一个部门的总人数
     *
     * @param department department
     * @return
     * @throws SerException
     */
    Integer departmentTotalPeople(String department) throws SerException;

}
