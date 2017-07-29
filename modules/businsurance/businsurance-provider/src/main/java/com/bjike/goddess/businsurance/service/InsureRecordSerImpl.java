package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.entity.InsureRecord;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.excel.InsureRecordExcel;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.InsureRecordNextTO;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 意外险记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class InsureRecordSerImpl extends ServiceImpl<InsureRecord, InsureRecordDTO> implements InsureRecordSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(总经办)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经办岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块审核)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是福利模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(运营商务部审核)
     *
     * @throws SerException
     */
    private void checkBussPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是运营商务部人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对财务模块审核权限（福利模块审核）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对查看权限（运营商务部）
     */
    private Boolean guideBussIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMond = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosin = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagBuss = guideBussIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagMond || flagPosin || flagBuss) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case MODULEAUDIT:
                flag = guideMondIdentity();
                break;
            case MANAGEAUDIT:
                flag = guidePosinIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case BUSINESSAUDIT:
                flag = guideBussIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        Long count = super.count(insureRecordDTO);
        return count;
    }

    @Override
    public List<InsureRecordBO> listInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        checkPermission();
        insureRecordDTO.getSorts().add("createTime=asc");
        List<InsureRecord> list = super.findByCis(insureRecordDTO,true);

        return BeanTransform.copyProperties(list, InsureRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InsureRecordBO addInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        checkPermission();
        InsureRecord insureRecord = BeanTransform.copyProperties(insureRecordTO,InsureRecord.class,true);
        insureRecord.setCreateTime(LocalDateTime.now());
        super.save( insureRecord );
        return BeanTransform.copyProperties(insureRecord, InsureRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InsureRecordBO editInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(insureRecordTO.getId())){
            throw new SerException("id不能为空");
        }
        InsureRecord insureRecord = BeanTransform.copyProperties(insureRecordTO,InsureRecord.class,true);
        InsureRecord cusLevel = super.findById( insureRecordTO.getId() );

        BeanUtils.copyProperties(insureRecord , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(insureRecord, InsureRecordBO.class);
    }


    @Override
    public InsureRecordBO editNextInsureRecord(InsureRecordNextTO insureRecordNextTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(insureRecordNextTO.getId())){
            throw new SerException("id不能为空");
        }

        InsureRecord cusLevel = super.findById( insureRecordNextTO.getId() );
        InsureRecord temp = new InsureRecord();

        BeanUtils.copyProperties(cusLevel , temp ,"id","createTime","modifyTime");
        temp.setStartDate( LocalDate.parse(insureRecordNextTO.getStartDate()));
        temp.setEndDate( LocalDate.parse(insureRecordNextTO.getEndDate()));
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());

        cusLevel.setRenewal("是");
        super.update( cusLevel );

        super.save( temp );
        return BeanTransform.copyProperties(cusLevel, InsureRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteInsureRecord(String id) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }



    @Override
    public InsureRecordBO getInsureRecord(String id) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        InsureRecord cusLevel = super.findById( id  );
        return BeanTransform.copyProperties(cusLevel, InsureRecordBO.class);
    }

    @Override
    public byte[] exportExcel( ) throws SerException {
        checkPermission();
        InsureRecordDTO insureRecordDTO = new InsureRecordDTO();
        List<InsureRecord> insureRecords = super.findByCis(insureRecordDTO);
        List<InsureRecordExcel> insureRecordExcels = new ArrayList<>();
        insureRecords.stream().forEach(str -> {
            InsureRecordExcel excel = BeanTransform.copyProperties(str,InsureRecordExcel.class);
            insureRecordExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(insureRecordExcels, excel);
        return bytes;
    }
    
}