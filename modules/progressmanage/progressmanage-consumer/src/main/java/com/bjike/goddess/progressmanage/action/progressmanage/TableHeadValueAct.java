package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressTableAPI;
import com.bjike.goddess.progressmanage.api.TableHeadValueAPI;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;
import com.bjike.goddess.progressmanage.vo.TableHeadForValueVO;
import com.bjike.goddess.progressmanage.vo.TableHeadValueVO;
import com.bjike.goddess.progressmanage.vo.TableListForHeadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进度表表头对应Value
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tableheadvalue")
public class TableHeadValueAct {

    @Autowired
    private TableHeadValueAPI tableHeadValueAPI;
    @Autowired
    private ProgressTableAPI progressTableAPI;

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
     * 根据进度表Id查询表头对应值
     *
     * @return class TableListForHeadVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({TableHeadValueDTO.TableValidate.class}) TableHeadValueDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TableHeadValueVO> voList = BeanTransform.copyProperties(tableHeadValueAPI.pageList(dto), TableHeadValueVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}