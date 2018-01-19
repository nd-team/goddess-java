package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CostDetailBO;
import com.bjike.goddess.costdetail.entity.CostDetail;
import com.bjike.goddess.costdetail.service.CostDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 成本明细业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costDetailApiImpl")
public class CostDetailApiImpl implements CostDetailAPI {

    @Autowired
    private CostDetailSer costDetailSer;

//    @Override
//    public void getSave(CostDetail costDetail) throws SerException {
//        costDetailSer.getSave(costDetail);
//    }
//
//    @Override
//    public void save() {
//
//    }

    @Override
    public void getSave(CostDetail costDetail) throws SerException {
        costDetailSer.save(costDetail);
    }

    @Override
    public List<CostDetailBO> getCostDetail(String date, String department) {
        return costDetailSer.getCostDetail(date, department);
    }

    @Override
    public void save(List<CostDetail> costDetails) throws SerException {
        costDetailSer.save(costDetails);
    }

    @Override
    public List<CostDetailBO> edit(String date, String department) {
        return null;
    }

    @Override
    public void update(List<CostDetail> costDetails) throws SerException {
        costDetailSer.update(costDetails);
    }

    @Override
    public void del(String date, String department) {
        costDetailSer.del(date, department);
    }

    @Override
    public void testlist(List<CostDetail> list) throws SerException {
        costDetailSer.testlist(list);
    }

    @Override
    public Map<String, List<CostDetailBO>> dateAndDepart() throws SerException {
        return costDetailSer.dateAndDepart();
    }

    @Override
    public List<CostDetailBO> getDao() {
        return null;
    }

//    @Override
//    public void remove(List<CostDetail> costDetails) {
//
//    }

//    @Override
//    public CostDetailBO getCostById(String id) throws SerException {
//        return costDetailSer.getCostById(id);
//    }

//    @Override
//    public List<CostDetailBO> getCostDetail(Integer year,Integer month ,String department) throws SerException {
//        return costDetailSer.getCostDetail(year, month, department);
//    }
//
//    @Override
//    public Map<String, CostDetail> getCostDetailMap(String date, String department) {
//        return costDetailSer.getCostDetailMap(date, department);
//    }
//
//    @Override
//    public List<CostDetailBO> getDao() {
//        return costDetailSer.getDao();
//    }
}