package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.vo.DriverInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DriverDispatchFeeBO;
import com.bjike.goddess.dispatchcar.bo.DriverDispatchsBO;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoEditTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.vo.AuditDetailVO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.vo.OilCardBasicVO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 出车记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dispatchcarinfo")
public class DispatchCarInfoAct extends BaseFileAction {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DispatchCarInfoDTO dto) throws ActException {
        try {
            Long count = dispatchCarInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id出车记录
     *
     * @param id 出车记录id
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findById(id), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增出车记录
     *
     * @param editTO 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DispatchCarInfoEditTO editTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoTO to = BeanTransform.copyProperties(editTO, DispatchCarInfoTO.class);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.addModel(to), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑出车记录
     *
     * @param editTO 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DispatchCarInfoEditTO editTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoTO to = BeanTransform.copyProperties(editTO, DispatchCarInfoTO.class);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.editModel(to), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 附件内容
     * @param id      出车id
     * @version v1
     */
    @PostMapping("v1/upload/{id}")
    public Result fileUpload(@PathVariable String id ,HttpServletRequest request) throws ActException {
        try {
            String path = "dispatchCar";
            fileAPI.upload(this.getInputStreams(request, path.toString()));
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 查看附件
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/files/{id}")
    public Result findFiles(@PathVariable String id,HttpServletRequest request) throws ActException {
        // 17-4-14 查看附件
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/siginmanage/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @return class AuditDetailVO
     * @version v1
     */
    @GetMapping("v1/audit/{id}")
    public Result findAudit(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AuditDetailVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findAudit(id), AuditDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DispatchCarInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有司机信息和车牌号码
     * @return class DriverInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/driver")
    public Result findDriver() throws ActException{
        try {
            List<DriverInfoBO> boList = dispatchCarInfoAPI.findDriver();
            List<DriverInfoVO> voList = BeanTransform.copyProperties(boList,DriverInfoVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有用车陪同人员和用车人员和任务下达人员和所属地区和所属项目组
     * @return class EntryBasicInfoVO
     * @throws ActException
     * @version v1
     */
   @GetMapping("v1/find/entry")
    public Result findAllEntry() throws ActException{
        try {
            List<EntryBasicInfoBO> boList = dispatchCarInfoAPI.findAllEntry();
            List<EntryBasicInfoVO> voList = BeanTransform.copyProperties(boList,EntryBasicInfoVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
   }

    /**
     * 查询所有油卡信息
     * @return class OilCardBasicVO
     * @throws ActException
     * @version v1
     */
   @GetMapping("v1/find/oil")
    public Result findAllOil() throws ActException{
       try {
           List<OilCardBasicBO> boList = dispatchCarInfoAPI.findAllOil();
           List<OilCardBasicVO> voList = BeanTransform.copyProperties(boList,OilCardBasicVO.class);
           return ActResult.initialize(voList);
       }catch (SerException e){
           throw new ActException(e.getMessage());
       }
   }


}