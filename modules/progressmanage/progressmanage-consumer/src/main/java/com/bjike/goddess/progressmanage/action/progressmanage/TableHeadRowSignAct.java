package com.bjike.goddess.progressmanage.action.progressmanage;


import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressTableAPI;
import com.bjike.goddess.progressmanage.api.TableHeadRowSignAPI;
import com.bjike.goddess.progressmanage.bo.TableHeadRowSignBO;
import com.bjike.goddess.progressmanage.dto.TableHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.to.TableHeadRowSignTO;
import com.bjike.goddess.progressmanage.vo.TableHeadForValueVO;
import com.bjike.goddess.progressmanage.vo.TableHeadRowSignVO;
import com.bjike.goddess.progressmanage.vo.TableHeadValueVO;
import com.bjike.goddess.progressmanage.vo.TableListForHeadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 进度表表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tableheadrowsign")
public class TableHeadRowSignAct {

    @Autowired
    private ProgressTableAPI progressTableAPI;
    @Autowired
    private TableHeadRowSignAPI tableHeadRowSignAPI;

    /**
     * 进度表下拉列表
     *
     * @return class TableListForHeadVO
     * @version v1
     */
    @GetMapping("v1/tables")
    public Result tables(HttpServletRequest request) throws ActException {
        try {
            List<TableListForHeadVO> voList = BeanTransform.copyProperties(progressTableAPI.tables(), TableListForHeadVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据进度表Id查询所有表头
     *
     * @return class TableListForHeadVO
     * @version v1
     */
    @GetMapping("v1/heads")
    public Result heads(@Validated({TableHeadValueDTO.TableValidate.class}) TableHeadRowSignDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TableHeadForValueVO> voList = BeanTransform.copyProperties(tableHeadRowSignAPI.heads(dto), TableHeadForValueVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /* * 根据进度表Id查询表头对应值
     *
     * @return class TableListForHeadVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({TableHeadValueDTO.TableValidate.class}) TableHeadRowSignDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TableHeadRowSignVO> voList = new ArrayList<TableHeadRowSignVO>();
            List<TableHeadRowSignBO> boList = tableHeadRowSignAPI.pageList(dto);
            for (TableHeadRowSignBO bo : boList) {
                TableHeadRowSignVO vo = new TableHeadRowSignVO();
                vo.setId(bo.getId());
                vo.setTableHeadValueList(BeanTransform.copyProperties(bo.getBoList(),TableHeadValueVO.class));
                voList.add(vo);
            }

            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 表头信息List
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TableHeadRowSignTO to) throws ActException {
        try {
            tableHeadRowSignAPI.add(to);
            return new ActResult("新增成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 表头信息List
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class})TableHeadRowSignTO to) throws ActException {
        try {
            tableHeadRowSignAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 行Id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            tableHeadRowSignAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}