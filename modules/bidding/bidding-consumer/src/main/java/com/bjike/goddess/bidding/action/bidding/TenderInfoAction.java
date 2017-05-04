package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.bidding.api.TenderInfoAPI;
import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.bidding.vo.TenderInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 标书资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.315 ]
 * @Description: [ 标书资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tenderinfo")
public class TenderInfoAction {
    @Autowired
    private TenderInfoAPI tenderInfoAPI;

    /**
     * 标书资料列表总条数
     *
     * @param tenderInfoDTO 标书资料dto
     * @des 获取所有标书资料
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TenderInfoDTO tenderInfoDTO) throws ActException {
        try {
            Long count = tenderInfoAPI.countTenderInfo(tenderInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个标书资料
     *
     * @param id
     * @return class TenderInfoVO
     * @des 获取一个标书资料
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(tenderInfoBO, TenderInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 标书资料列表
     *
     * @param tenderInfoDTO 标书资料dto
     * @return class TenderInfoVO
     * @des 获取所有标书资料
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TenderInfoDTO tenderInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<TenderInfoVO> tenderInfoVOS = BeanTransform.copyProperties(
                    tenderInfoAPI.findListTenderInfo(tenderInfoDTO), TenderInfoVO.class, request);
            return ActResult.initialize(tenderInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @return class TenderInfoVO
     * @des 添加标书资料
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TenderInfoTO tenderInfoTO, BindingResult bindingResult) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.insertTenderInfo(tenderInfoTO);
            return ActResult.initialize(tenderInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @return class TenderInfoVO
     * @des 编辑标书资料
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TenderInfoTO tenderInfoTO, BindingResult bindingResult) throws ActException {
        try {
            TenderInfoBO tenderInfoBO = tenderInfoAPI.editTenderInfo(tenderInfoTO);
            return ActResult.initialize(tenderInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除标书资料
     *
     * @param id 用户id
     * @des 根据用户id删除标书资料记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteTenderInfo(@PathVariable String id) throws ActException {
        try {
            tenderInfoAPI.removeTenderInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 标书资料导出
     *
     * @param projectName 项目名称
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String projectName) throws ActException {
        String excel = null;
        try {
            excel = tenderInfoAPI.exportExcel(projectName);
            return new ActResult(excel);
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
            tenderInfoAPI.upload();
            return new ActResult("upload success!");
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
            tenderInfoAPI.uploadAttachments();
            return new ActResult("uploadAttachments success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}