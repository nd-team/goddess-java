package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.entity.TowerInsure;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.excel.SonPermissionObject;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.entity.TowerInsure;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 塔工意外险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class TowerInsureSerImpl extends ServiceImpl<TowerInsure, TowerInsureDTO> implements TowerInsureSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private BusInsuranceSer busInsuranceSer;
    @Autowired
    private CarInsureSer carInsureSer;
    @Autowired
    private GroupByInsurerSer groupByInsurerSer;
    @Autowired
    private GroupInsureSer groupInsureSer;
    @Autowired
    private InsureRecordSer insureRecordSer;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagSee = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagMond = guideMondIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagPosin = guidePosinIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagBuss = guideBussIdentity();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("towerinsure");
            obj.setDescribesion("塔工意外险信息管理");
            if (flagSee || flagMond || flagPosin ||flagBuss) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagBusInsur = busInsuranceSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("busInsurance");
            obj.setDescribesion("商业保险方案");
            if (flagBusInsur) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagCarInsure = carInsureSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("carinsure");
            obj.setDescribesion("车险信息管理");
            if (flagCarInsure) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagGroupBy = groupByInsurerSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("groupbyinsurer");
            obj.setDescribesion("团体意外险被保险人信息管理");
            if (flagGroupBy) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagGroupInsure = groupInsureSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("groupinsure");
            obj.setDescribesion("团体意外险信息管理");
            if (flagGroupInsure) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagInsureTeco = insureRecordSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("insurerecord");
            obj.setDescribesion("意外险记录");
            if (flagInsureTeco) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            return list;
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
    public Long countTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureNumber())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureNumber", towerInsureDTO.getInsureNumber()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsurer())) {
            towerInsureDTO.getConditions().add(Restrict.like("insurer", towerInsureDTO.getInsurer()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureByPerson())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureByPerson", towerInsureDTO.getInsureByPerson()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getReSign())) {
            towerInsureDTO.getConditions().add(Restrict.eq("reSign", towerInsureDTO.getReSign()));
        }
        Long count = super.count(towerInsureDTO);
        return count;
    }

    @Override
    public List<TowerInsureBO> listTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        checkPermission();
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureNumber())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureNumber", towerInsureDTO.getInsureNumber()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsurer())) {
            towerInsureDTO.getConditions().add(Restrict.like("insurer", towerInsureDTO.getInsurer()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureByPerson())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureByPerson", towerInsureDTO.getInsureByPerson()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getReSign())) {
            towerInsureDTO.getConditions().add(Restrict.eq("reSign", towerInsureDTO.getReSign()));
        }
        towerInsureDTO.getSorts().add("createTime=desc");
        List<TowerInsure> list = super.findByCis(towerInsureDTO, true);

        List<TowerInsureBO> bolist = BeanTransform.copyProperties(list, TowerInsureBO.class);

        return bolist;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO addTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure towerInsure = BeanTransform.copyProperties(towerInsureTO, TowerInsure.class, true);
        towerInsure.setCreateTime(LocalDateTime.now());
        super.save(towerInsure);
        return BeanTransform.copyProperties(towerInsure, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure towerInsure = BeanTransform.copyProperties(towerInsureTO, TowerInsure.class, true);
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        BeanUtils.copyProperties(towerInsure, cusLevel, "id", "createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(towerInsure, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTowerInsure(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editAccount(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setAccountType(towerInsureTO.getAccountType());
        cusLevel.setBank(towerInsureTO.getBank());
        cusLevel.setAccount(towerInsureTO.getAccount());
        cusLevel.setAccountOwner(towerInsureTO.getAccountOwner());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editContext(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setInsureCode(towerInsureTO.getInsureCode());
        cusLevel.setInsureName(towerInsureTO.getInsureName());
        cusLevel.setInsureRespon(towerInsureTO.getInsureRespon());
        cusLevel.setInsureMoney(towerInsureTO.getInsureMoney());
        cusLevel.setInsureFee(towerInsureTO.getInsureFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO applicant(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setPersonName(towerInsureTO.getPersonName());
        cusLevel.setPersonSex(towerInsureTO.getPersonSex());
        cusLevel.setPersonBorn(LocalDate.parse(towerInsureTO.getPersonBorn()));
        cusLevel.setPersonCountry(towerInsureTO.getPersonCountry());
        cusLevel.setPersonFileType(towerInsureTO.getPersonFileType());
        cusLevel.setPersonFileNum(towerInsureTO.getPersonFileNum());
        cusLevel.setPersonFileEffTerm(towerInsureTO.getPersonFileEffTerm());
        cusLevel.setPersonJob(towerInsureTO.getPersonJob());
        cusLevel.setPersonJobCode(towerInsureTO.getPersonJobCode());
        cusLevel.setPersonAddr(towerInsureTO.getPersonAddr());
        cusLevel.setPersonContact(towerInsureTO.getPersonContact());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editInsurePerson(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setPersonByRelation(towerInsureTO.getPersonByRelation());
        cusLevel.setPersonByName(towerInsureTO.getPersonByName());
        cusLevel.setPersonBySex(towerInsureTO.getPersonBySex());
        cusLevel.setPersonByBorn(LocalDate.parse(towerInsureTO.getPersonByBorn()));
        cusLevel.setPersonByCountry(towerInsureTO.getPersonByCountry());
        cusLevel.setPersonByFileType(towerInsureTO.getPersonByFileType());
        cusLevel.setPersonByFileNum(towerInsureTO.getPersonByFileNum());
        cusLevel.setPersonByFileEffTerm(towerInsureTO.getPersonByFileEffTerm());
        cusLevel.setPersonByJob(towerInsureTO.getPersonByJob());
        cusLevel.setPersonByJobCode(towerInsureTO.getPersonByJobCode());
        cusLevel.setPersonByAddr(towerInsureTO.getPersonByAddr());
        cusLevel.setPersonByContact(towerInsureTO.getPersonByContact());
        cusLevel.setPersonBySocial(towerInsureTO.getPersonBySocial());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editBenefit(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setBenefitOrder(towerInsureTO.getBenefitOrder());
        cusLevel.setBenefiter(towerInsureTO.getBenefiter());
        cusLevel.setBenefitSex(towerInsureTO.getBenefitSex());
        cusLevel.setBenefitBorn(LocalDate.parse(towerInsureTO.getBenefitBorn()));
        cusLevel.setBenefitFileType(towerInsureTO.getBenefitFileType());
        cusLevel.setBenefitFileNum(towerInsureTO.getBenefitFileNum());
        cusLevel.setBenefitBy(towerInsureTO.getBenefitBy());
        cusLevel.setBenefitShare(towerInsureTO.getBenefitShare());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editSaleOrgan(TowerInsureTO towerInsureTO) throws SerException {
        checkPermission();
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setOrganName(towerInsureTO.getOrganName());
        cusLevel.setOrganCode(towerInsureTO.getOrganCode());
        cusLevel.setSaleName(towerInsureTO.getSaleName());
        cusLevel.setSalerCode(towerInsureTO.getSalerCode());
        cusLevel.setOrganContact(towerInsureTO.getOrganContact());
        cusLevel.setOrganAddr(towerInsureTO.getOrganAddr());
        cusLevel.setPostCode(towerInsureTO.getPostCode());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Override
    public TowerInsureBO getTowerInsure(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        TowerInsure cusLevel = super.findById(id);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }


}