package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadRowSignBO;
import com.bjike.goddess.progressmanage.dto.TableHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.TableHeadRowSign;
import com.bjike.goddess.progressmanage.to.TableHeadRowSignTO;

import java.util.List;

/**
 * 进度表表头对应值的行标记业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TableHeadRowSignSer extends Ser<TableHeadRowSign, TableHeadRowSignDTO> {

    List<TableHeadForValueBO> heads(TableHeadRowSignDTO dto) throws SerException;

    void insertModel(TableHeadRowSignTO to) throws SerException;

    void updateModel(TableHeadRowSignTO to) throws SerException;

    void delete(String id) throws SerException;

    List<TableHeadRowSignBO> pageList(TableHeadRowSignDTO dto) throws SerException;
}