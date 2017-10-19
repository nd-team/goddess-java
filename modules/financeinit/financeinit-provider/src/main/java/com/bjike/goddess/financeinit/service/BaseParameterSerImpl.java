package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.entity.BaseParameter;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.to.BaseParameterTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.BinaryClient;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基本参数业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class BaseParameterSerImpl extends ServiceImpl<BaseParameter, BaseParameterDTO> implements BaseParameterSer {
    @Autowired
    private CompanyBasicInfoSer companyBasicInfoSer;
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
                throw new SerException("您不是相应财务部门的人员，不可以查看");
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
                throw new SerException("您不是相应财务部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
            case DELETE:
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        seachCondi(baseParameterDTO);
        Long count = super.count(baseParameterDTO);
        return count;
    }

    @Override
    public BaseParameterBO getOneById(String id) throws SerException {
        BaseParameter baseParameter = super.findById(id);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }

    @Override
    public List<BaseParameterBO> listBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        checkSeeIdentity();
        seachCondi(baseParameterDTO);
        List<BaseParameter> baseParameters = super.findByCis(baseParameterDTO);
        return BeanTransform.copyProperties(baseParameters,BaseParameterBO.class);
    }

    public void seachCondi(BaseParameterDTO baseParameterDTO)throws SerException{
        if(StringUtils.isNotBlank(baseParameterDTO.getCompanyName())){
            baseParameterDTO.getConditions().add(Restrict.eq("companyName",baseParameterDTO.getCompanyName()));
        }
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public BaseParameterBO addBasicPara(BaseParameterTO baseParameterTO) throws SerException {
       checkAddIdentity();
        BaseParameter baseParameter = BeanTransform.copyProperties(baseParameterTO,BaseParameterBO.class,true);
        baseParameter.setCreateTime(LocalDateTime.now());
        CompanyBasicInfoBO companyBasicInfoBO = companyBasicInfoSer.findByCompanyName(baseParameterTO.getCompanyName());
        baseParameter.setEin(companyBasicInfoBO.getEin());
        baseParameter.setPhone(companyBasicInfoBO.getPhone());
        baseParameter.setAddress(companyBasicInfoBO.getAddress());
        baseParameter.setBank(companyBasicInfoBO.getBank());
        baseParameter.setAccount(companyBasicInfoBO.getAccount());
        baseParameter.setScaleShape(companyBasicInfoBO.getScaleShape());
        baseParameter.setRemark(companyBasicInfoBO.getRemark());
        super.save(baseParameter);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public BaseParameterBO editBasicPara(BaseParameterTO baseParameterTO) throws SerException {
       checkAddIdentity();
        BaseParameter baseParameter = super.findById(baseParameterTO.getId());
        LocalDateTime date = baseParameter.getCreateTime();
        baseParameter = BeanTransform.copyProperties(baseParameterTO,BaseParameter.class,true);
        baseParameter.setCreateTime(date);
        baseParameter.setModifyTime(LocalDateTime.now());
        super.update(baseParameter);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteBasicPara(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}