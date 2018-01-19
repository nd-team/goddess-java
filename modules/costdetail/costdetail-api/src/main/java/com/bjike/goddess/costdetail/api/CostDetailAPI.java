package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CostDetailBO;
import com.bjike.goddess.costdetail.entity.CostDetail;

import java.util.List;
import java.util.Map;

/**
 * 成本明细业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostDetailAPI {

    void getSave(CostDetail costDetail) throws SerException;

    List<CostDetailBO> getCostDetail(String date, String department);

    void save(List<CostDetail> costDetails) throws SerException;

    List<CostDetailBO> edit(String date, String department);

    void update(List<CostDetail> costDetails) throws SerException;

    void del(String date, String department);

    void testlist(List<CostDetail> list) throws SerException;

    Map<String, List<CostDetailBO>> dateAndDepart() throws SerException;

//    CostDetailBO getCostById(String id) throws SerException;

//    List<CostDetailBO> getCostDetail(Integer year, Integer month, String department) throws SerException;

//    Map<String, CostDetail> getCostDetailMap(String date, String department);


    List<CostDetailBO> getDao();
}