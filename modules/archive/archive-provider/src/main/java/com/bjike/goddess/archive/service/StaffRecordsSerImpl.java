package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.PerBO;
import com.bjike.goddess.archive.bo.StaffNameBO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工档案业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class StaffRecordsSerImpl extends ServiceImpl<StaffRecords, StaffRecordsDTO> implements StaffRecordsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getRotainCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busRotainCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busRotainCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getRotainCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public void upload(List<StaffRecordsExcelTO> toList) throws SerException {
        for (int i = 1; i <= toList.size(); i++) {
            this.isExist(toList.get(i - 1), i);
        }
        List<StaffRecords> list = BeanTransform.copyProperties(toList, StaffRecords.class, true);
        super.save(list);
    }

    private void isExist(StaffRecordsExcelTO to, Integer row) throws SerException {
        if (this.findByName(to.getUsername()) != null)
            throw new SerException(String.format("第%d行的姓名已存在", row));
        if (this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException(String.format("第%d行的员工编号已存在", row));

    }

    @Override
    public StaffRecordsBO findByName(String username) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("username", username));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(super.findOne(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("serialNumber", serialNumber));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO getById(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        return super.count(dto);
    }

    @Override
    public List<StaffNameBO> getName() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        List<StaffRecordsBO> list = maps(dto);
        List<StaffNameBO> nameBOs = new ArrayList<>();
        if(null != list && list.size() > 0){
            for(StaffRecordsBO bo : list){
                StaffNameBO staffNameBO = new StaffNameBO();
                staffNameBO.setId(bo.getSerialNumber());
                staffNameBO.setName(bo.getUsername());
                nameBOs.add(staffNameBO);
            }
        }
        return nameBOs;
    }

    @Override
    public List<PerBO> getPerBO(String name) throws SerException {
        EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoAPI.getEntryBasicInfoByName(name).get(0);
        String id = entryBasicInfoBO.getId();
        StaffRecordsBO staffRecordsBO = getById(id);
        PerBO perBO = new PerBO();
        perBO.setPerid(staffRecordsBO.getIdentityCard());
        perBO.setPhone(staffRecordsBO.getTelephone());
        List<PerBO> list = new ArrayList<>();
        list.add(perBO);
        return list;
    }
}