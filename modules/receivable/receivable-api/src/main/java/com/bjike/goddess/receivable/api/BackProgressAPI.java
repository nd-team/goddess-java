package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.BackProgressBO;
import com.bjike.goddess.receivable.bo.BackProgressCollectBO;
import com.bjike.goddess.receivable.bo.CollectDetailBO;
import com.bjike.goddess.receivable.dto.BackProgressDTO;
import com.bjike.goddess.receivable.to.BackProgressCollectTO;
import com.bjike.goddess.receivable.to.BackProgressTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.YearCollectTO;

import java.util.List;
import java.util.Set;

/**
 * 回款进度业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BackProgressAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 回款进度列表总条数
     */
    default Long count(BackProgressDTO dto) throws SerException {
        return null;
    }

    /**
     * 回款进度列表
     *
     * @param dto
     * @return class BackProgressBO
     * @throws SerException
     */
    default List<BackProgressBO> list(BackProgressDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个回款进度
     *
     * @return class BackProgressBO
     */
    default BackProgressBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param backProgressTOS 回款进度
     * @return class BackProgressBO
     */
    default BackProgressBO importExcel(List<BackProgressTO> backProgressTOS) throws SerException {
        return null;
    }

    /**
     * Excel下载模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 编辑回款进度
     *
     * @param to
     * @return class BackProgressBO
     * @throws SerException
     */
    default BackProgressBO edit(BackProgressTO to) throws SerException {
        return null;
    }

    /**
     * 删除回款进度
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 根据地区,外包单位,类型,专业,开发票,预收账款时间,运营商名称,派工情况,实际完工状态 汇总
     *
     * @param to
     * @return class BackProgressCollectBO
     * @throws SerException
     */
    default List<BackProgressCollectBO> backProgressCollect(BackProgressCollectTO to) throws SerException {
        return null;
    }

    /**
     * 获取所有地区
     *
     * @throws SerException
     */
    default Set<String> areas() throws SerException {
        return null;
    }

    /**
     * 获取所有外包单位
     *
     * @throws SerException
     */
    default Set<String> contractors() throws SerException {
        return null;
    }

    /**
     * 获取所有类型
     *
     * @throws SerException
     */

    default Set<String> types() throws SerException {
        return null;
    }

    /**
     * 获取所有专业
     *
     * @throws SerException
     */
    default Set<String> majors() throws SerException {
        return null;
    }

    /**
     * 获取所有运营商名称
     *
     * @throws SerException
     */
    default Set<String> operatorNames() throws SerException {
        return null;
    }

    /**
     * 获取所有派工情况
     *
     * @throws SerException
     */
    default Set<String> taskCases() throws SerException {
        return null;
    }

    /**
     * 获取所有实际完工状态
     *
     * @throws SerException
     */
    default Set<String> completeStatus() throws SerException {
        return null;
    }

    /**
     * 总汇总表
     *
     * @param to
     * @return class CollectDetailBO
     * @throws SerException
     */
    default List<CollectDetailBO> yearCollect(YearCollectTO to) throws SerException {
        return null;
    }
}