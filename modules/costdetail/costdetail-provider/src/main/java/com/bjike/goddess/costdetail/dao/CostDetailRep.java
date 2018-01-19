package com.bjike.goddess.costdetail.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.costdetail.dto.CostDetailDTO;
import com.bjike.goddess.costdetail.entity.CostDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 成本明细持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostDetailRep extends JpaRep<CostDetail, CostDetailDTO> {

//    @Query(value = "SELECT * FROM costdetail_costdetail WHERE id = '184d69d2-0fda-4d27-a0ab-999966420336'",nativeQuery = true)
//    CostDetail findbysql();

//    @Query(value = "SELECT\n" +
//            "  any_value(id)                          AS id,\n" +
//            "  any_value(createTime)                  AS createTime,\n" +
//            "  any_value(modifyTime)                  AS modifyTime,\n" +
//            "  type,\n" +
//            "  any_value(costdetail_costdetail.total) AS total,\n" +
//            "  any_value(tenthDay)                    AS tenthDay,\n" +
//            "  any_value(fifteenthDay)                AS fifteenthDay,\n" +
//            "  any_value(twentiethDay)                AS twentiethDay,\n" +
//            "  any_value(thirtiethDay)                AS thirtiethDay,\n" +
//            "  any_value(date)                        AS date,\n" +
//            "  any_value(department)                  AS department\n" +
//            "FROM costdetail_costdetail\n" +
//            "WHERE date LIKE '2017-12%' AND department = 'lll'\n" +
//            "GROUP BY type;", nativeQuery = true)
//    List<CostDetail> SQLByDateAndDepartment(String date, String department);

    void deleteByDateAndDepartment(String date,String department);

    List<CostDetail> findByDateAndDepartment(String date, String department);
}