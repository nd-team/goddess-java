package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.file.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractQuoteDataAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.vo.ContractQuoteDataVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 合同单价资料信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.318 ]
 * @Description: [ 合同单价资料信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractquotedata")
public class ContractQuoteDataAct extends BaseFileAction {

    @Autowired
    private ContractQuoteDataAPI contractQuoteDataAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 根据id查询合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @return class ContractQuoteDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractquotedata/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ContractQuoteDataBO bo = contractQuoteDataAPI.findById(id);
            ContractQuoteDataVO vo = BeanTransform.copyProperties(bo, ContractQuoteDataVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同单价资料信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ContractQuoteDataDTO dto, BindingResult result) throws ActException {
        try {
            Long count = contractQuoteDataAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ContractQuoteDataDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ContractQuoteDataBO> boList = contractQuoteDataAPI.list(dto);
            List<ContractQuoteDataVO> voList = BeanTransform.copyProperties(boList, ContractQuoteDataVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同单价资料信息
     *
     * @param to 合同单价资料信息to信息
     * @return class ContractQuoteDataVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractQuoteDataTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ContractQuoteDataBO bo = contractQuoteDataAPI.save(to);
            ContractQuoteDataVO vo = BeanTransform.copyProperties(bo, ContractQuoteDataVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同单价资料信息
     *
     * @param to 合同单价资料信息to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractQuoteDataTO to, BindingResult result) throws ActException {
        try {
            contractQuoteDataAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.congealStatus(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @des 根据id标识为正常状态
     * @version v1
     */
    @PostMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.thawStatus(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合同单价资料信息
     *
     * @param dto 合同单价资料信息
     * @return class ContractQuoteDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collects")
    public Result collect(@Validated(ContractQuoteDataDTO.Collect.class) ContractQuoteDataDTO dto, BindingResult result) throws ActException {
        try {
            List<ContractQuoteDataVO> listVO = BeanTransform.copyProperties(
                    contractQuoteDataAPI.collect(dto),
                    ContractQuoteDataVO.class);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区或项目组查询合同单价资料信息
     *
     * @param area 地区
     * @param project 项目组
     * @return class ContractQuoteDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/searchs")
    public Result searchs(String area, String project) throws ActException {
        try {

            List<ContractQuoteDataVO> listVO = BeanTransform.copyProperties(
                    contractQuoteDataAPI.searchs(area, project),
                    ContractQuoteDataVO.class);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 上传附件
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String path = "/upload/contractquotedata";
            List<InputStream> inputStream = this.getInputStreams(request, path);
            fileAPI.upload(inputStream);
            return new ActResult("上传成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //TODO: yewenbo 2017-03-21 查看附件
    //TODO: yewenbo 2017-03-21 下载附件
}