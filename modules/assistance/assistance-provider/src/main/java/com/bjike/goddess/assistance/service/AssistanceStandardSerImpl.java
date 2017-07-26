package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceStandardBO;
import com.bjike.goddess.assistance.dto.AssistanceStandardDTO;
import com.bjike.goddess.assistance.entity.AssistanceStandard;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.AssistanceStandardTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 补助标准业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceStandardSerImpl extends ServiceImpl<AssistanceStandard, AssistanceStandardDTO> implements AssistanceStandardSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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
    public Long countAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        if( StringUtils.isNotBlank(assistanceStandardDTO.getName() )){
            assistanceStandardDTO.getConditions().add(Restrict.like("name",assistanceStandardDTO.getName() ));
        }
        assistanceStandardDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceStandardDTO);
        return count;
    }

    @Override
    public AssistanceStandardBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AssistanceStandard  list = super.findById(id);

        return BeanTransform.copyProperties(list, AssistanceStandardBO.class );
    }

    @Override
    public List<AssistanceStandardBO> listAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        checkSeeIdentity();
        if( StringUtils.isNotBlank(assistanceStandardDTO.getName() )){
            assistanceStandardDTO.getConditions().add(Restrict.like("name",assistanceStandardDTO.getName() ));
        }
        assistanceStandardDTO.getSorts().add("createTime=desc");
        List<AssistanceStandard> list = super.findByCis(assistanceStandardDTO,true);

        return BeanTransform.copyProperties(list, AssistanceStandardBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceStandardBO addAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        checkAddIdentity();
        AssistanceStandard assistanceStandard = BeanTransform.copyProperties(assistanceStandardTO,AssistanceStandard.class,true);
        assistanceStandard.setCreateTime(LocalDateTime.now());
        super.save( assistanceStandard );
        return BeanTransform.copyProperties(assistanceStandard, AssistanceStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceStandardBO editAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        checkAddIdentity();
        AssistanceStandard assistanceStandard = BeanTransform.copyProperties(assistanceStandardTO,AssistanceStandard.class,true);
        AssistanceStandard rs = super.findById( assistanceStandardTO.getId() );

        rs.setName( assistanceStandard.getName() );
        rs.setRemark( assistanceStandard.getRemark() );
        rs.setStandardForm( assistanceStandard.getStandardForm() );
        rs.setModifyTime(LocalDateTime.now());
        super.update( rs );
        return BeanTransform.copyProperties(rs, AssistanceStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceStandard(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AssistanceStandard assistanceStandard = super.findById( id );
        if(assistanceStandard != null ){
            super.remove( id );
        }else{
            throw new SerException("删除的对象不存在");
        }
    }

    @Override
    public List<AssistanceStandardBO> getAgeStands() throws SerException {
        AssistanceStandardDTO assistanceStandardDTO = new AssistanceStandardDTO();
        assistanceStandardDTO.getConditions().add(Restrict.like("name","工龄补助"));
        List<AssistanceStandard> list = super.findByCis( assistanceStandardDTO );
        return BeanTransform.copyProperties(list,AssistanceStandardBO.class);
    }
}