package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.entity.BaseParameter;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.entity.Currency;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CurrencySer currencySer;


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

    //TODO: 这里还不知道财务部门主管的账号所以先写死为IKE009999
    /**
     * 核对财务部门主管权限,假设是:(IKE009999)
     */
    private void checkFinanDepartSup() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
        String employeeNumber = userBO.getEmployeeNumber();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userName.toLowerCase())) {
            flag = "IKE009999".equalsIgnoreCase(employeeNumber);
            if (!flag) {
                throw new SerException("您不是财务部门的主管，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    //TODO: 这里还不知道财务部门主管的账号所以先写死为IKE009999
    /**
     * 导航栏核对财务部门主管权限,假设是:(IKE009999)
     */
    private Boolean guideFinanDepartSup() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
        String employeeNumber = userBO.getEmployeeNumber();
        RpcTransmit.transmitUserToken(userToken);
        if (!"admin".equals(userName.toLowerCase())) {
            flag = "IKE009999".equalsIgnoreCase(employeeNumber);
        } else {
            flag = true;
        }
        return flag;
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
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFinanSup = guideFinanDepartSup();
        if (flagSee || flagAdd || flagFinanSup) {
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
                flag = guideFinanDepartSup();
                break;
            case EDIT:
                flag = guideFinanDepartSup();
                break;
            case DELETE:
                flag = guideFinanDepartSup();
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
        return BeanTransform.copyProperties(baseParameter, BaseParameterBO.class);
    }

    @Override
    public List<BaseParameterBO> listBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        checkSeeIdentity();
        seachCondi(baseParameterDTO);
        List<BaseParameter> baseParameters = super.findByCis(baseParameterDTO);
        return BeanTransform.copyProperties(baseParameters, BaseParameterBO.class);
    }

    public void seachCondi(BaseParameterDTO baseParameterDTO) throws SerException {
        if (StringUtils.isNotBlank(baseParameterDTO.getCompanyName())) {
            baseParameterDTO.getConditions().add(Restrict.eq("companyName", baseParameterDTO.getCompanyName()));
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public BaseParameterBO addBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        checkFinanDepartSup();
        BaseParameter baseParameter = BeanTransform.copyProperties(baseParameterTO, BaseParameter.class, true);
        baseParameter.setCreateTime(LocalDateTime.now());
        super.save(baseParameter);
        //初始化公司基本信息
        CompanyBasicInfo companyBasicInfo = new CompanyBasicInfo();
        companyBasicInfo.setCompanyName(baseParameter.getCompanyName());
        companyBasicInfoSer.save(companyBasicInfo);
        //初始化币别
        Currency currency = new Currency();
        currency.setName(baseParameter.getFunctionalCurrency());
        currency.setCode("01");
        currency.setStandardMoney(true);
        currencySer.save(currency);
//        CompanyBasicInfoBO companyBasicInfoBO = companyBasicInfoSer.findByCompanyName(baseParameterTO.getCompanyName());
//        baseParameter.setEin(companyBasicInfoBO.getEin());
//        baseParameter.setPhone(companyBasicInfoBO.getPhone());
//        baseParameter.setAddress(companyBasicInfoBO.getAddress());
//        baseParameter.setBank(companyBasicInfoBO.getBank());
//        baseParameter.setAccount(companyBasicInfoBO.getAccount());
//        baseParameter.setScaleShape(companyBasicInfoBO.getScaleShape());
//        baseParameter.setRemark(companyBasicInfoBO.getRemark());
        return BeanTransform.copyProperties(baseParameter, BaseParameterBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public BaseParameterBO editBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        checkFinanDepartSup();
        //TODO:这里需要问一下需求当编辑的时候,添加那边初始化的公司基本信息和币别是否也需要随之改变,还是让财务主管自行到对应的地方修改信息
        BaseParameter baseParameter = super.findById(baseParameterTO.getId());
        LocalDateTime date = baseParameter.getCreateTime();
        baseParameter = BeanTransform.copyProperties(baseParameterTO, BaseParameter.class, true);
        baseParameter.setCreateTime(date);
        baseParameter.setModifyTime(LocalDateTime.now());
        super.update(baseParameter);
        return BeanTransform.copyProperties(baseParameter, BaseParameterBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteBasicPara(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public String findDoudap() throws SerException {
        List<BaseParameter> baseParameters = super.findAll();
        if (null != baseParameters && baseParameters.size() > 0) {
            List<LocalDate> time = baseParameters.stream().map(BaseParameter::getDateDuringPeriod).collect(Collectors.toList());
            if (null != time && time.size() > 0) {
                return DateUtil.dateToString(time.get(0));
            }
        }
        return null;
    }
}