package com.bjike.goddess.subjectcollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.subjectcollect.bo.CompareCollectBO;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.excel.SonPermissionObject;
import com.bjike.goddess.subjectcollect.to.GuidePermissionTO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;

import java.util.List;
import java.util.Set;

/**
 * 科目汇总表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SubjectCollectSer extends Ser<SubjectCollect, SubjectCollectDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 科目汇总表列表总条数
     */
    default Long countSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return null;
    }

    /**
     * 一个科目汇总表
     *
     * @return class SubjectCollectBO
     */
    default SubjectCollectBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 科目汇总表
     *
     * @param subjectCollectDTO 科目汇总表dto
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default List<SubjectCollectBO> findListSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return null;
    }

    /**
     * 添加科目汇总表
     *
     * @param subjectCollectTO 科目汇总表数据to
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default SubjectCollectBO insertSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return null;
    }

    /**
     * 编辑科目汇总表
     *
     * @param subjectCollectTO 科目汇总表数据to
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default SubjectCollectBO editSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除科目汇总表
     *
     * @param id
     * @throws SerException
     */
    default void removeSubjectCollect(String id) throws SerException {

    }

    /**
     * 获取地区
     * @return String
     * @throws SerException
     */
    default List<String> getArea() throws SerException {
        return null;
    }


    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(SubjectCollectDTO dto) throws SerException;

    /**
     * 汇总对比
     *
     * @return class CompareCollectBO
     * @throws SerException
     */
    default List<CompareCollectBO> collectCompare(Integer[] months) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 根据dto条件获取合计记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    SubjectCollectBO getSum(SubjectCollectDTO dto) throws SerException;

    /**
     * chenjunhao
     * 获取所有一级科目
     *
     * @return
     * @throws SerException
     */
    Set<String> allFirstSubjects() throws SerException;

    /**
     * chenjunhao
     * 获取所有项目名称
     *
     * @return
     * @throws SerException
     */
    Set<String> allProjectNames() throws SerException;

    /**
     * zhuangkaiqin
     * 同步记账凭证数据
     *
     * @return
     * @throws SerException
     */
    List<VoucherGenerateBO> synchrodata() throws SerException;

    /**
     * zhuangkaiqin
     * 科目汇总
     *
     * @param subjectCollectDTO
     * @return
     */
    List<SubjectCollectBO> subjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException;

    /**
     * zhuangkaiqin
     * 地区汇总
     *
     * @param subjectCollectDTO
     * @return
     */
    List<SubjectCollectBO> areaCollect(SubjectCollectDTO subjectCollectDTO) throws SerException;

    /**
     * zhuangkaiqin
     * 项目组汇总
     *
     * @param subjectCollectDTO
     * @return
     */
    List<SubjectCollectBO> groupCollect(SubjectCollectDTO subjectCollectDTO) throws SerException;

    /**
     * zhuangkaiqin
     * 项目名称汇总
     *
     * @param subjectCollectDTO
     * @return
     */
    List<SubjectCollectBO> pNameCollect(SubjectCollectDTO subjectCollectDTO) throws SerException;
}