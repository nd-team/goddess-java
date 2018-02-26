package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
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
 * 设置币别业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CurrencySerImpl extends ServiceImpl<Currency, CurrencyDTO> implements CurrencySer {

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
        if (flagSee || flagAdd ||flagFinanSup) {
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
    public Long countCurren(CurrencyDTO currencyDTO) throws SerException {
        Long count = super.count(currencyDTO);
        return count;
    }

    @Override
    public CurrencyBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        Currency currency = super.findById(id);
        return BeanTransform.copyProperties(currency, CurrencyBO.class);
    }

    @Override
    public List<CurrencyBO> listCurren(CurrencyDTO currencyDTO) throws SerException {
        checkSeeIdentity();
        List<Currency> list = super.findByCis(currencyDTO, true);
        return BeanTransform.copyProperties(list, CurrencyBO.class);
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public CurrencyBO addCurren(CurrencyTO currencyTO) throws SerException {
        checkFinanDepartSup();
        if(currencyTO.getStandardMoney()){
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.getConditions().add(Restrict.eq("standardMoney",true));
            Currency currency1 = super.findOne(currencyDTO);
            if(null!=currency1){
                throw new SerException("只能有一条是否为本位币的数据");
            }
        }
        Currency currency = BeanTransform.copyProperties(currencyTO,Currency.class,true);
        currency.setCreateTime(LocalDateTime.now());
        currency.setCode(autoCode());
        super.save(currency);
        return BeanTransform.copyProperties(currency,CurrencyBO.class);
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public CurrencyBO editCurren(CurrencyTO currencyTO) throws SerException {
        checkFinanDepartSup();
        Currency currency = super.findById(currencyTO.getId());
        LocalDateTime date = currency.getCreateTime();
        currency = BeanTransform.copyProperties(currencyTO,Currency.class);
        currency.setCreateTime(date);
        currency.setModifyTime(LocalDateTime.now());
        super.update(currency);
        return BeanTransform.copyProperties(currency,CurrencyBO.class);
    }
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteCurren(String id) throws SerException {
        checkFinanDepartSup();
        Currency currency = super.findById(id);
        if(currency.getStandardMoney()){
            throw new SerException("是本位币的数据不能被删除");
        }
        super.remove(id);
    }

    /**
     * 自动生成编码
     * @throws SerException
     */
    public String autoCode() throws SerException {
        String code = "01";
        String sql = "SELECT max(code) AS code FROM financeinit_currency";
        List<Object> objectList = super.findBySql(sql);
        if(objectList!=null && objectList.size()>0){
            Integer maxCode = Integer.parseInt(String.valueOf(objectList.get(0)));
            code = (maxCode+1)<10?("0"+(maxCode+1)):(maxCode+1)+"";
        }
        return code;
    }

}