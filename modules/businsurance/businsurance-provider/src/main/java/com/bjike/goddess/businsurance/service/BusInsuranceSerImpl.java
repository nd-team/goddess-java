package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.entity.BusInsurance;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.excel.BusInsuranceExcel;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 商业保险方案业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class BusInsuranceSerImpl extends ServiceImpl<BusInsurance, BusInsuranceDTO> implements BusInsuranceSer {
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
    public Long countBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureComapny())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureComapny", busInsuranceDTO.getInsureComapny()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureCondition())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureCondition", busInsuranceDTO.getInsureCondition()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureType())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureType", busInsuranceDTO.getInsureType()));
        }
        Long count = super.count(busInsuranceDTO);
        return count;
    }

    @Override
    public List<BusInsuranceBO> listBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        checkPermission();
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureComapny())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureComapny", busInsuranceDTO.getInsureComapny()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureCondition())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureCondition", busInsuranceDTO.getInsureCondition()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureType())) {
            busInsuranceDTO.getConditions().add(Restrict.like("insureType", busInsuranceDTO.getInsureType()));
        }
        busInsuranceDTO.getSorts().add("createTime=asc");
        List<BusInsurance> list = super.findByCis(busInsuranceDTO, true);

        return BeanTransform.copyProperties(list, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO addBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        checkPermission();
        BusInsurance busInsurance = BeanTransform.copyProperties(busInsuranceTO, BusInsurance.class, true);
        busInsurance.setCreateTime(LocalDateTime.now());
        super.save(busInsurance);
        return BeanTransform.copyProperties(busInsurance, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank( busInsuranceTO.getId())){
            throw new SerException("id不能为空");
        }
        BusInsurance busInsurance = BeanTransform.copyProperties(busInsuranceTO, BusInsurance.class, true);
        BusInsurance cusLevel = super.findById(busInsuranceTO.getId());

        BeanUtils.copyProperties(busInsurance, cusLevel, "id", "createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(busInsurance, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBusInsurance(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBuyCondition(BusInsuranceTO busInsuranceTO) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(busInsuranceTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById(busInsuranceTO.getId());

        cusLevel.setBuyQuired(busInsuranceTO.getBuyQuired());
        cusLevel.setFileRecord(busInsuranceTO.getFileRecord());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        checkModPermission();
        if (StringUtils.isBlank(busInsuranceTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById(busInsuranceTO.getId());


        cusLevel.setWarefareAdvice(busInsuranceTO.getWarefareAdvice());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBusAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        checkBussPermission();
        if (StringUtils.isBlank(busInsuranceTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById(busInsuranceTO.getId());


        cusLevel.setOperaAdvice(busInsuranceTO.getOperaAdvice());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO audit(BusInsuranceTO busInsuranceTO) throws SerException {
        checkPonsPermission();
        if (StringUtils.isBlank(busInsuranceTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById(busInsuranceTO.getId());

        if (StringUtils.isBlank(cusLevel.getWarefareAdvice()) || StringUtils.isBlank(cusLevel.getOperaAdvice())) {
            throw new SerException("运营审核意见或福利模块意见还未填写，不能给总经办审核");
        }
        cusLevel.setManageAdvice(busInsuranceTO.getManageAdvice());
        cusLevel.setRemark(busInsuranceTO.getRemark());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Override
    public BusInsuranceBO getBusInsurance(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById(id);
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Override
    public List<String> getAllInsureComapny() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureComapny = model.getInsureComapny();
            if (StringUtils.isNotBlank(model.getInsureComapny())) {
                set.add(insureComapny);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getAllInsureType() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureType = model.getInsureType();
            if (StringUtils.isNotBlank(model.getInsureType())) {
                set.add(insureType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getAllInsureCondition() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureCondition = model.getInsureCondition();
            if (StringUtils.isNotBlank(model.getInsureCondition())) {
                set.add(insureCondition);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public byte[] exportExcel(BusInsuranceDTO busInsuranceDTO) throws SerException {
       checkPermission();
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureComapny())) {
            busInsuranceDTO.getConditions().add(Restrict.eq("insureComapny", busInsuranceDTO.getInsureComapny()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureType())) {
            busInsuranceDTO.getConditions().add(Restrict.eq("insureType", busInsuranceDTO.getInsureType()));
        }
        if (StringUtils.isNotBlank(busInsuranceDTO.getInsureCondition())) {
            busInsuranceDTO.getConditions().add(Restrict.eq("insureCondition", busInsuranceDTO.getInsureCondition()));
        }
        List<BusInsurance> busInsurances = super.findByCis(busInsuranceDTO);
        List<BusInsuranceExcel> busInsuranceExcels = new ArrayList<>();
        busInsurances.stream().forEach(str -> {
            BusInsuranceExcel excel = BeanTransform.copyProperties(str, BusInsuranceExcel.class);
            busInsuranceExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(busInsuranceExcels, excel);
        return bytes;
    }

}