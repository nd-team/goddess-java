
package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalAPI;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.accommodation.vo.RentalVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 租房信息业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房信息业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */

@RestController
@RequestMapping("rentalAct")
public class RentalAct {
    @Autowired
    private RentalAPI rentalAPI;

    /**
     * 获取租房信息
     *
     * @param rentalDTO 租房信息dto
     * @version v1
     */
    @GetMapping("v1/listRentalApply")
    public Result findListRental(RentalDTO rentalDTO) throws ActException {
        try {
            List<RentalVO> rentalVOS = BeanTransform.copyProperties(
                    rentalAPI.findListRental(rentalDTO), RentalVO.class, true);
            return ActResult.initialize(rentalVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租房信息
     *
     * @param rentalTO 租房信息to
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addRental(@Validated RentalTO rentalTO) throws ActException {
        try {
            RentalBO rentalBO = rentalAPI.insertRental(rentalTO);
            return ActResult.initialize(rentalBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租房信息
     *
     * @param rentalTO 租房信息数据bo
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editRental(@Validated RentalTO rentalTO) throws ActException {
        try {
            RentalBO rentalBO = rentalAPI.editRental(rentalTO);
            return ActResult.initialize(rentalBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除租房信息记录
     *
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteRental(@PathVariable String id) throws ActException {
        try {
            rentalAPI.removeRental(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @PostMapping("v1/uploadAttachments")
    public Result uploadAttachments() throws ActException {
        try {
            rentalAPI.uploadAttachments();
            return new ActResult("uploadAttachments success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 附件
     *
     * @version v1
     */
    @PostMapping("v1/attachments")
    public Result attachments() throws ActException {
        try {
            rentalAPI.attachments();
            return new ActResult("attachments success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            rentalAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 租房状态
     *
     * @version v1
     */
    @PostMapping("v1/rentalStatus")
    public Result rentalStatus(@Validated RentalTO rentalTO) throws ActException {
        try {
            RentalBO rentalBO = rentalAPI.rentalStatus(rentalTO);
            return ActResult.initialize(rentalBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租房信息导出明细
     *
     * @param area 地区
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String area) throws ActException {
        String excel = null;
        try {
            excel = rentalAPI.exportExcel(area);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}


