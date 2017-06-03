package com.bjike.goddess.commerce.action.commerce;

import com.bjike.goddess.commerce.api.CommerceConferenceAPI;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.to.CollectTO;
import com.bjike.goddess.commerce.to.CommerceConferenceExcelTO;
import com.bjike.goddess.commerce.to.CommerceConferenceTO;
import com.bjike.goddess.commerce.vo.CommerceConferenceVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 商务会议
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("commerceconference")
public class CommerceConferenceAction extends BaseFileAction {

    @Autowired
    private CommerceConferenceAPI commerceConferenceAPI;

    /**
     * 保存
     *
     * @param to 商务会议传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommerceConferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.save(to), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 商务会议传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommerceConferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.update(to), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 商务会议数据id
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.congeal(id), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询商务会议数据
     *
     * @param id 商务会议数据id
     * @return class CommerceConferenceVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.getById(id), CommerceConferenceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 商务会议数据传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CommerceConferenceDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.maps(dto), CommerceConferenceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(commerceConferenceAPI.getTotal());
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
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CommerceConferenceExcelTO> list = ExcelUtil.excelToClazz(is, CommerceConferenceExcelTO.class, excel);
            commerceConferenceAPI.upload(list);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @version v1
     */
    @GetMapping("v1/export")
    public Result exportExcel(@Validated(EDIT.class) CollectTO to, BindingResult result, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务会议.xlsx";
            super.writeOutFile(response, commerceConferenceAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}