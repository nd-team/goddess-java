package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.bo.VacateCountBO;
import com.bjike.goddess.attendance.bo.VacateMailBO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.excel.VacateImportExcel;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 请假管理业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VacateSer extends Ser<Vacate, VacateDTO> {
    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateBO> list(VacateDTO dto) throws SerException;

    /**
     * 移动端列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateBO> listPhone(VacateDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(VacateTO to) throws SerException;

    /**
     * 补录
     *
     * @param to
     * @throws SerException
     */
    void fill(VacateTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    VacateBO findByID(String id) throws SerException;

    /**
     * 移动端通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    VacateBO findByIDPhone(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(VacateDTO dto) throws SerException;

    /**
     * 获取请假天数
     *
     * @param to
     * @return
     * @throws SerException
     */
    Double getTime(VacateTO to) throws SerException;


    /**
     * 根据请假人和请假开始时间查询请假数据
     *
     * @return
     */
    default List<VacateBO> findByCon(VacateConDTO vacateConDTO) throws SerException {
        return null;
    }

    /**
     * 审核列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateBO> auditList(VacateDTO dto) throws SerException;

    /**
     * 移动端审核列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateBO> auditListPhone(VacateDTO dto) throws SerException;

    /**
     * 审核列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long auditListCount(VacateDTO dto) throws SerException;

    /**
     * 审核
     *
     * @param to
     * @throws SerException
     */
    void audit(VacateTO to) throws SerException;

    /**
     * 请假汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    VacateCountBO vacateCount(VacateDTO dto) throws SerException;

    /**
     * 请假汇总邮件
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateMailBO> vacateCountMail(VacateDTO dto) throws SerException;

    /**
     * 获取某一天的请假时长
     *
     * @param start
     * @param end
     * @param date
     * @return
     * @throws SerException
     */
    Double currentVacateTime(String start, String end, String date) throws SerException;

    /**
     * 某人当前周从周一至周日请假次数或某个季度分别请假次数
     *
     * @param overTimesDTO
     * @return
     * @throws SerException
     */
    default OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        return null;
    }


    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(VacateDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
     * @throws SerException
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<VacateImportExcel> tos) throws SerException {
        return;
    }

    /**
     * 当前用户请假总条数
     *
     * @throws SerException
     */
    default Long currentUserVacate() throws SerException {
        return null;
    }

}