package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.CostDetailBO;
import com.bjike.goddess.costdetail.dao.CostDetailRep;
import com.bjike.goddess.costdetail.dto.CostDetailDTO;
import com.bjike.goddess.costdetail.entity.CostDetail;
import com.bjike.goddess.costdetail.entity.SonCostDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 成本明细业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class CostDetailSerImpl extends ServiceImpl<CostDetail, CostDetailDTO> implements CostDetailSer {

    @Autowired
    private CostDetailRep costDetailRep;

    public void getSave(CostDetail costDetail) throws SerException {
        super.save(costDetail);
    }

    @Override
    public List<CostDetail> find(String date, String department) {
        return costDetailRep.findByDateAndDepartment(date, department);
    }

    @Override
    public void save(List<CostDetail> costDetails) throws SerException {
        super.save(costDetails);
    }

    @Override
    public List<CostDetail> edit(String date, String department) {
        return costDetailRep.findByDateAndDepartment(date, department);
    }
    @Override
    public void update(List<CostDetail> costDetails) throws SerException {
        super.update(costDetails);
    }

    @Override
    public void del(String date, String department) {
        costDetailRep.deleteByDateAndDepartment(date, department);
    }

    @Override
    public List<CostDetailBO> getCostDetail(String date, String department) {
        String[] strings = {"期初余额", "营业成本", "劳务成本", "应交税金", "公司借入", "实收资本", "公司借出", "公司还款", "其他收入", "主营业务收入", "预估应收账款", "实际资金缺口", "预估结余资金", "累计实际资金缺口", "累计预估结余资金"};
        List<CostDetailBO> costDetailBOS = BeanTransform.wanycopyProperties(costDetailRep.findByDateAndDepartment(date, department), CostDetailBO.class);
        List<CostDetailBO> list = new ArrayList<>();
        for (int i = 0; i < costDetailBOS.size(); i++) {
            for (int o=0;o<costDetailBOS.size();o++) {
                if (strings[i].equals(costDetailBOS.get(o).getType())) {
                    list.add(costDetailBOS.get(o));
                }
            }
        }
        System.out.println("hello");
        return list;
    }

    @Override
    public void testlist(List<CostDetail> list) throws SerException {
        formula(list);
    }

    @Override
    public Map<String, List<CostDetailBO>> dateAndDepart() throws SerException {
        String datesSql = "SELECT date FROM costdetail_costdetail  GROUP BY date";
        String departmentSql = "SELECT department FROM costdetail_costdetail GROUP BY department";
        String[] deparField = {"department"};
        String[] dateField = {"date"};
        List<CostDetailBO> dates = super.findBySql(datesSql, CostDetailBO.class, dateField);
        List<CostDetailBO> depars = super.findBySql(departmentSql, CostDetailBO.class, deparField);
        Map<String, List<CostDetailBO>> map = new HashMap<>();
        map.put("date", dates);
        map.put("department", depars);
        return map;
    }

    private void formula(List<CostDetail> list) throws SerException {
        Map<String,CostDetail> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {

            List<SonCostDetail> sonCostDetaillist = list.get(i).getSonCostDetails();
            if (sonCostDetaillist != null) {
                Double total = 0.0;
                Double ththday = 0.0;
                Double fifteen = 0.0;
                Double twentie = 0.0;
                Double thirt = 0.0;
                for (int o = 0; o < sonCostDetaillist.size(); o++) {
                    SonCostDetail s = sonCostDetaillist.get(o);
                    s.setTotal(s.getTenthDay() + s.getFifteenthDay() + s.getTwentiethDay() + s.getThirtiethDay());
                    total = total + s.getTotal();
                    ththday = ththday + s.getTenthDay();
                    fifteen = fifteen + s.getFifteenthDay();
                    twentie = twentie + s.getTwentiethDay();
                    thirt = thirt + s.getThirtiethDay();
                }
                list.get(i).setTenthDay(ththday);
                list.get(i).setFifteenthDay(fifteen);
                list.get(i).setTwentiethDay(twentie);
                list.get(i).setThirtiethDay(thirt);
            }
            list.get(i).setTotal(list.get(i).getTenthDay() + list.get(i).getFifteenthDay() + list.get(i).getTwentiethDay() + list.get(i).getThirtiethDay());
        }

        for (int i = 0; i < list.size(); i++){
            map.put(list.get(i).getType(), list.get(i));
        }

        map.get("营业成本").setTotal(map.get("劳务成本").getTotal()+map.get("应交税金").getTotal());
        map.get("营业成本").setTenthDay(map.get("劳务成本").getTenthDay()+map.get("应交税金").getTenthDay());
        map.get("营业成本").setFifteenthDay(map.get("劳务成本").getFifteenthDay()+map.get("应交税金").getFifteenthDay());
        map.get("营业成本").setTwentiethDay(map.get("劳务成本").getTwentiethDay()+map.get("应交税金").getTwentiethDay());
        map.get("营业成本").setThirtiethDay(map.get("劳务成本").getThirtiethDay()+map.get("应交税金").getThirtiethDay());

        map.get("实际资金缺口").setTenthDay(map.get("主营业务收入").getTenthDay() + map.get("公司借入").getTenthDay() + map.get("实收资本").getTenthDay() - map.get("营业成本").getTenthDay() - map.get("公司借出").getTenthDay() + map.get("其他收入").getTenthDay() - map.get("公司还款").getTenthDay());
        map.get("实际资金缺口").setFifteenthDay(map.get("主营业务收入").getFifteenthDay() + map.get("公司借入").getFifteenthDay() + map.get("实收资本").getFifteenthDay() - map.get("营业成本").getFifteenthDay() - map.get("公司借出").getFifteenthDay() + map.get("其他收入").getFifteenthDay() - map.get("公司还款").getFifteenthDay());
        map.get("实际资金缺口").setTwentiethDay(map.get("主营业务收入").getTwentiethDay() + map.get("公司借入").getTwentiethDay() + map.get("实收资本").getTwentiethDay() - map.get("营业成本").getTwentiethDay() - map.get("公司借出").getTwentiethDay() + map.get("其他收入").getTwentiethDay() - map.get("公司还款").getTwentiethDay());
        map.get("实际资金缺口").setThirtiethDay(map.get("主营业务收入").getThirtiethDay() + map.get("公司借入").getThirtiethDay() + map.get("实收资本").getThirtiethDay() - map.get("营业成本").getThirtiethDay() - map.get("公司借出").getThirtiethDay() + map.get("其他收入").getThirtiethDay() - map.get("公司还款").getThirtiethDay());
        map.get("实际资金缺口").setTotal(map.get("实际资金缺口").getTenthDay() + map.get("实际资金缺口").getFifteenthDay() + map.get("实际资金缺口").getTwentiethDay() + map.get("实际资金缺口").getThirtiethDay());

        map.get("预估结余资金").setTenthDay(map.get("主营业务收入").getTenthDay() + map.get("公司借入").getTenthDay() + map.get("实收资本").getTenthDay() - map.get("营业成本").getTenthDay() - map.get("公司借出").getTenthDay() + map.get("其他收入").getTenthDay() - map.get("公司还款").getTenthDay() + map.get("预估应收账款").getTenthDay());
        map.get("预估结余资金").setFifteenthDay(map.get("主营业务收入").getFifteenthDay() + map.get("公司借入").getFifteenthDay() + map.get("实收资本").getFifteenthDay() - map.get("营业成本").getFifteenthDay() - map.get("公司借出").getFifteenthDay() + map.get("其他收入").getFifteenthDay() - map.get("公司还款").getFifteenthDay() + map.get("预估应收账款").getTenthDay());
        map.get("预估结余资金").setTwentiethDay(map.get("主营业务收入").getTwentiethDay() + map.get("公司借入").getTwentiethDay() + map.get("实收资本").getTwentiethDay() - map.get("营业成本").getTwentiethDay() - map.get("公司借出").getTwentiethDay() + map.get("其他收入").getTwentiethDay() - map.get("公司还款").getTwentiethDay() + map.get("预估应收账款").getTenthDay());
        map.get("预估结余资金").setThirtiethDay(map.get("主营业务收入").getThirtiethDay() + map.get("公司借入").getThirtiethDay() + map.get("实收资本").getThirtiethDay() - map.get("营业成本").getThirtiethDay() - map.get("公司借出").getThirtiethDay() + map.get("其他收入").getThirtiethDay() - map.get("公司还款").getThirtiethDay() + map.get("预估应收账款").getTenthDay());
        map.get("预估结余资金").setTotal(map.get("预估结余资金").getTenthDay() + map.get("预估结余资金").getFifteenthDay() + map.get("预估结余资金").getTwentiethDay() + map.get("预估结余资金").getThirtiethDay());

        map.get("累计实际资金缺口").setTenthDay(map.get("主营业务收入").getTenthDay() + map.get("公司借入").getTenthDay() + map.get("实收资本").getTenthDay() - map.get("公司借出").getTenthDay() - map.get("营业成本").getTenthDay() + map.get("期初余额").getTotal() + map.get("其他收入").getTenthDay() - map.get("公司还款").getTenthDay());
        map.get("累计实际资金缺口").setFifteenthDay(map.get("主营业务收入").getFifteenthDay() + map.get("公司借入").getFifteenthDay() + map.get("实收资本").getFifteenthDay() - map.get("公司借出").getFifteenthDay() - map.get("营业成本").getFifteenthDay() + map.get("期初余额").getTenthDay() + map.get("其他收入").getFifteenthDay() - map.get("公司还款").getFifteenthDay());
        map.get("累计实际资金缺口").setTwentiethDay(map.get("主营业务收入").getTwentiethDay() + map.get("公司借入").getTwentiethDay() + map.get("实收资本").getTwentiethDay() - map.get("公司借出").getTwentiethDay() - map.get("营业成本").getTwentiethDay() + map.get("期初余额").getFifteenthDay() + map.get("其他收入").getTwentiethDay() - map.get("公司还款").getTwentiethDay());
        map.get("累计实际资金缺口").setThirtiethDay(map.get("主营业务收入").getThirtiethDay() + map.get("公司借入").getThirtiethDay() + map.get("实收资本").getThirtiethDay() - map.get("公司借出").getThirtiethDay() - map.get("营业成本").getThirtiethDay() + map.get("期初余额").getTwentiethDay() + map.get("其他收入").getThirtiethDay() - map.get("公司还款").getThirtiethDay());
        map.get("累计实际资金缺口").setTotal(map.get("主营业务收入").getTotal() + map.get("公司借入").getTotal() + map.get("实收资本").getTotal() - map.get("公司借出").getTotal() - map.get("营业成本").getTotal() + map.get("期初余额").getTotal() + map.get("其他收入").getTotal());

        map.get("累计预估结余资金").setTenthDay(map.get("主营业务收入").getTenthDay()+map.get("公司借入").getTenthDay()+map.get("实收资本").getTenthDay()+map.get("预估应收账款").getTenthDay()-map.get("营业成本").getTenthDay()-map.get("公司借出").getTenthDay()+map.get("期初余额").getTotal()+map.get("其他收入").getTenthDay()-map.get("公司还款").getTenthDay());
        map.get("累计预估结余资金").setFifteenthDay(map.get("主营业务收入").getFifteenthDay()+map.get("公司借入").getFifteenthDay()+map.get("实收资本").getFifteenthDay()+map.get("预估应收账款").getFifteenthDay()-map.get("营业成本").getFifteenthDay()-map.get("公司借出").getFifteenthDay()+map.get("累计实际资金缺口").getTenthDay()+map.get("其他收入").getFifteenthDay()-map.get("公司还款").getFifteenthDay());
        map.get("累计预估结余资金").setTwentiethDay(map.get("主营业务收入").getTwentiethDay()+map.get("公司借入").getTwentiethDay()+map.get("实收资本").getTwentiethDay()+map.get("预估应收账款").getTwentiethDay()-map.get("营业成本").getTwentiethDay()-map.get("公司借出").getTwentiethDay()+map.get("累计实际资金缺口").getFifteenthDay()+map.get("其他收入").getTwentiethDay()-map.get("公司还款").getTwentiethDay());
        map.get("累计预估结余资金").setThirtiethDay(map.get("主营业务收入").getThirtiethDay()+map.get("公司借入").getThirtiethDay()+map.get("实收资本").getThirtiethDay()+map.get("预估应收账款").getThirtiethDay()-map.get("营业成本").getThirtiethDay()-map.get("公司借出").getThirtiethDay()+map.get("累计实际资金缺口").getTwentiethDay()+map.get("其他收入").getThirtiethDay()-map.get("公司还款").getThirtiethDay());
        map.get("累计预估结余资金").setTotal(map.get("期初余额").getTotal() - map.get("营业成本").getTotal() + map.get("公司借入").getTotal() + map.get("实收资本").getTotal() - map.get("公司借出").getTotal() + map.get("主营业务收入").getTotal() + map.get("预估应收账款").getTotal());

        list.removeAll(list);
        Iterator<Map.Entry<String,CostDetail>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getValue());
        }

        super.save(list);
    }
}