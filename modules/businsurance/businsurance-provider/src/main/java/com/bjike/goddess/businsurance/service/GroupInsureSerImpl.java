package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.GroupInsureBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.entity.GroupByInsurer;
import com.bjike.goddess.businsurance.entity.GroupInsure;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.to.GroupInsureTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.entity.GroupInsure;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 团体意外险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class GroupInsureSerImpl extends ServiceImpl<GroupInsure, GroupInsureDTO> implements GroupInsureSer {

    @Autowired
    private GroupByInsurerSer groupByInsurerSer;

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
            case COLLECT:
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
    public Long countGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(groupInsureDTO.getContractor())){
            groupInsureDTO.getConditions().add(Restrict.like("contractor",groupInsureDTO.getContractor()));
        }
        Long count = super.count(groupInsureDTO);
        return count;
    }

    @Override
    public List<GroupInsureBO> listGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        checkPermission();
        if (StringUtils.isNotBlank(groupInsureDTO.getContractor())){
            groupInsureDTO.getConditions().add(Restrict.like("contractor",groupInsureDTO.getContractor()));
        }
        groupInsureDTO.getSorts().add("createTime=desc");
        List<GroupInsure> list = super.findByCis(groupInsureDTO,true);

        return BeanTransform.copyProperties(list, GroupInsureBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupInsureBO addGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        checkPermission();
        GroupInsure groupInsure = BeanTransform.copyProperties(groupInsureTO,GroupInsure.class,true);
        groupInsure.setCreateTime(LocalDateTime.now());
        super.save( groupInsure );
        return BeanTransform.copyProperties(groupInsure, GroupInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GroupInsureBO editGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        checkPermission();
        GroupInsure groupInsure = BeanTransform.copyProperties(groupInsureTO,GroupInsure.class,true);
        GroupInsure cusLevel = super.findById( groupInsureTO.getId() );

        BeanUtils.copyProperties(groupInsure , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(groupInsure, GroupInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteGroupInsure(String id) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }

        GroupByInsurerDTO groupByInsurerDTO = new GroupByInsurerDTO();
        groupByInsurerDTO.getConditions().add(Restrict.eq("groupInsureId", id ));
        List<GroupByInsurer> groupByInsurerList = groupByInsurerSer.findByCis( groupByInsurerDTO  );
        if( groupByInsurerDTO != null && groupByInsurerList.size()>0 ){
            groupByInsurerSer.remove( groupByInsurerList );
        }
        super.remove( id );
    }

    @Override
    public GroupInsureBO getGroupInsure(String id) throws SerException {
        GroupInsure groupInsure = super.findById(id);
        return BeanTransform.copyProperties(groupInsure , GroupInsureBO.class);
    }
}