package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TableHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.to.TableHeadTO;

import java.util.List;

/**
 * 进度表表头业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TableHeadAPI {

    TableHeadBO add(TableHeadTO to) throws SerException;

    TableHeadBO edit(TableHeadTO to) throws SerException;

    void delete(String id) throws SerException;

    List<TableHeadBO> pageList(TableHeadDTO dto) throws SerException;
}