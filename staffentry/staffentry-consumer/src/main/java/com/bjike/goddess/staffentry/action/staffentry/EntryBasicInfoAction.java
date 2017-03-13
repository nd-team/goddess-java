package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 入职基本信息
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 12:08]
 * @Description: [入职基本信息]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffentry/entrybasicinfo")
public class EntryBasicInfoAction {

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    /**
     * 获取所有入职基本信息
     * @param entryBasicInfoDTO 入职基本信息dto
     * @return class entryBasicInfoBO
     * @throws ActException
     */
    @GetMapping("v1/listEntryBasicInfo")
    public ActResult findListEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws ActException {
        try {
            List<EntryBasicInfoVO> entryBasicInfoVOList = BeanTransform.copyProperties(
                    entryBasicInfoAPI.listEntryBasicInfo( entryBasicInfoDTO ) , EntryBasicInfoVO.class ,true);
            return ActResult.initialize( entryBasicInfoVOList );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 添加员工入职
     * @param entryBasicInfoTO 员工入职基本信息数据to
     * @return class entryBasicInfoBO
     * @throws ActException
     */
    @PostMapping("v1/add")
    public ActResult addEntryBasicInfo (@Valid EntryBasicInfoTO entryBasicInfoTO) throws ActException {
        try {
            EntryBasicInfoBO entryBasicInfoBO1 = entryBasicInfoAPI.insertEntryBasicInfo(entryBasicInfoTO );
            return ActResult.initialize( entryBasicInfoBO1);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 编辑员工入职
     * @param entryBasicInfoTO 员工入职基本信息数据bo
     * @return class entryBasicInfoBO
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public ActResult editEntryBasicInfo (@Valid EntryBasicInfoTO entryBasicInfoTO ) throws ActException {
        try {
            EntryBasicInfoBO entryBasicInfoBO1 = entryBasicInfoAPI.editEntryBasicInfo(entryBasicInfoTO );
            return ActResult.initialize( entryBasicInfoBO1);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 根据用户id删除员工入职基本信息记录
     * @param id 用户id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public ActResult deleteEntryBasicInfo(@PathVariable String id) throws ActException {
        try {
            entryBasicInfoAPI.removeEntryBasicInfo( id );
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 根据id查找某个员工入职基本信息
     * @param id 员工入职基本信息id
     * @return class entryBasicInfoTO
     * @throws ActException
     */
    @GetMapping("v1/getEntryBasicInfo")
    public ActResult findOneEntryBasicInfo(@RequestParam String id) throws ActException {
        try {
            EntryBasicInfoVO entryBasicInfoVO = BeanTransform.copyProperties(
                    entryBasicInfoAPI.getEntryBasicInfo( id ) , EntryBasicInfoVO.class ,true);
            return ActResult.initialize( entryBasicInfoVO );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 根据id emails发送入职通告邮件
     * @param entryBasicInfoTO 员工入职基本信息bo 主要id 和 emails
     * @return class entryBasicInfoBO
     * @throws ActException
     */
    @GetMapping("v1/sendEmailEntryBasicInfo")
    public ActResult emialEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws ActException {
        try {
            EntryBasicInfoVO entryBasicInfoVO = BeanTransform.copyProperties(
                    entryBasicInfoAPI.sendEntryBasicInfo( entryBasicInfoTO ) , EntryBasicInfoVO.class ,true);
            return ActResult.initialize( entryBasicInfoVO );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 根据岗位(position)、时间段(entryTime) 汇总入职情况统计
     * @param entryBasicInfoDTO 员工入职基本信息bo 主要position 和 entryTime
     * @return class entryBasicInfoBO
     * @throws ActException
     */
    @GetMapping("v1/collect")
    public ActResult collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws ActException {
        try {
            List<EntryBasicInfoVO> entryBasicInfoVOList = BeanTransform.copyProperties(
                    entryBasicInfoAPI.collectEntryBasicInfo( entryBasicInfoDTO ) , EntryBasicInfoVO.class ,true);
            return ActResult.initialize( entryBasicInfoVOList );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    //TODO: tanghaixiang 2017-03-10 上传附件

    //TODO: tanghaixiang 2017-03-10 查看附件

    
}
