package com.bjike.goddess.otherexpenses.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.otherexpenses.bo.*;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.to.CollectTO;
import com.bjike.goddess.otherexpenses.to.OtherExpensesTO;

import java.util.List;

/**
 * 其他费用业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherExpensesAPI {

    /**
     * 保存
     *
     * @param to 其他费用传输对象
     * @return
     * @throws SerException
     */
    default OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 其他费用传输对象
     * @return
     * @throws SerException
     */
    default OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 其他费用数据id
     * @return
     * @throws SerException
     */
    default OtherExpensesBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询其他费用数据
     *
     * @param id 其他费用数据id
     * @return
     * @throws SerException
     */
    default OtherExpensesBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 其他费用数据传输对象
     * @return
     * @throws SerException
     */
    default List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @param to 汇总条件传输对象
     * @return
     * @throws SerException
     */
    default List<AreaCollectBO> areaCollect(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总
     *
     * @param to 汇总条件传输对象
     * @return
     * @throws SerException
     */
    default List<NameCollectBO> nameCollect(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 类型汇总
     *
     * @param to 汇总条件传输对象
     * @return
     * @throws SerException
     */
    default List<TypeCollectBO> typeCollect(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 项目组汇总
     *
     * @param to 汇总条件传输对象
     * @return
     * @throws SerException
     */
    default List<ProjectCollectBO> projectCollect(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}