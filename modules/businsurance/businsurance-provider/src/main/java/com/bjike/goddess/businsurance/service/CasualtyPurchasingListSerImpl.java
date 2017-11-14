package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.api.CasualtyPurchasingDetailAPI;
import com.bjike.goddess.businsurance.bo.CasualtyPurchasingListBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingListDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingDetail;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingList;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingListImport;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingListImportTemple;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingListTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 团体意外险购买名单业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class CasualtyPurchasingListSerImpl extends ServiceImpl<CasualtyPurchasingList, CasualtyPurchasingListDTO> implements CasualtyPurchasingListSer {
   @Autowired
   private CasualtyPurchasingDetailSer casualtyPurchasingDetailSer;

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
    public Long countCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        Long count = super.count(casualtyPurchasingListDTO);
        return count;
    }

    @Override
    public CasualtyPurchasingListBO getOneCasualty(String id) throws SerException {
        CasualtyPurchasingList casualtyPurchasingList = super.findById(id);
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }

    @Override
    public List<CasualtyPurchasingListBO> listCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        checkPermission();
        casualtyPurchasingListDTO.getSorts().add("createTime=desc");
        List<CasualtyPurchasingList> list = super.findByCis(casualtyPurchasingListDTO,true);
        return BeanTransform.copyProperties(list, CasualtyPurchasingListBO.class );
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CasualtyPurchasingListBO addCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        checkPermission();
        CasualtyPurchasingList casualtyPurchasingList = BeanTransform.copyProperties(casualtyPurchasingListTO,CasualtyPurchasingList.class,true);
        casualtyPurchasingList.setCreateTime(LocalDateTime.now());
        super.save(casualtyPurchasingList);
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("beApplicantName",casualtyPurchasingListTO.getBeApplicantName()));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = casualtyPurchasingDetailSer.findByCis(casualtyPurchasingDetailDTO);
        if(casualtyPurchasingDetails!=null && casualtyPurchasingDetails.size()>0){
            CasualtyPurchasingDetail casualtyPurchasingDetail = casualtyPurchasingDetails.get(0);
            casualtyPurchasingDetail.setBuySocialSecurity(true);
            casualtyPurchasingDetailSer.update(casualtyPurchasingDetail);
        }
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CasualtyPurchasingListBO editCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        checkPermission();
        CasualtyPurchasingList casualtyPurchasingList = super.findById(casualtyPurchasingListTO.getId());
        LocalDateTime date = casualtyPurchasingList.getCreateTime();
        casualtyPurchasingList = BeanTransform.copyProperties(casualtyPurchasingListTO,CasualtyPurchasingList.class,true);
        casualtyPurchasingList.setCreateTime(date);
        casualtyPurchasingList.setModifyTime(LocalDateTime.now());
        super.update(casualtyPurchasingList);
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCasualty(String id) throws SerException {
       checkPermission();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
       checkPermission();
        List<CasualtyPurchasingList> list = super.findAll();
        List<CasualtyPurchasingListImport> casualtyPurchasingListImports = new ArrayList<>();
        list.stream().forEach(str -> {
            CasualtyPurchasingListImport excel = BeanTransform.copyProperties(str, CasualtyPurchasingListImport.class);
            casualtyPurchasingListImports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingListImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {

        List<CasualtyPurchasingListImportTemple> casualtyPurchasingListImportTemples = new ArrayList<>();
        CasualtyPurchasingListImportTemple casualtyPurchasingListImportTemple = new CasualtyPurchasingListImportTemple();
        casualtyPurchasingListImportTemple.setInsurancePolicyNo("695465552654456225");
        casualtyPurchasingListImportTemple.setUnitNo("2342");
        casualtyPurchasingListImportTemple.setSingleNo("321856954685468");
        casualtyPurchasingListImportTemple.setSingleStatus("已分单");
        casualtyPurchasingListImportTemple.setEffectiveDate("2017-12-12");
        casualtyPurchasingListImportTemple.setSurrInsurApplyDate("2018-03-12");
        casualtyPurchasingListImportTemple.setSecurityLeve("一级");
        casualtyPurchasingListImportTemple.setClientPhone("15596598745");
        casualtyPurchasingListImportTemple.setBeApplicantName("小朋友");
        casualtyPurchasingListImportTemple.setDocumentsType("身份证");
        casualtyPurchasingListImportTemple.setDocumentsPhone("6958469255369875462");
        casualtyPurchasingListImportTemple.setGender("女");
        casualtyPurchasingListImportTemple.setBirthDate("1987-12-12");
        casualtyPurchasingListImportTemple.setInsuredAge(20);
        casualtyPurchasingListImportTemple.setBeApplicantType("人");
        casualtyPurchasingListImportTemple.setMainWithBeAppliRela("test");
        casualtyPurchasingListImportTemple.setMainBeAppliName("小小朋友");
        casualtyPurchasingListImportTemple.setOccupationCode("012");
        casualtyPurchasingListImportTemple.setOccupationName("程序员");
        casualtyPurchasingListImportTemple.setDepartment("研发部");
        casualtyPurchasingListImportTemple.setSalaryMonth("100万");
        casualtyPurchasingListImportTemple.setEmail("xiaopengyou_aj@163.com");
        casualtyPurchasingListImportTemple.setMobilePhone("13698756482");
        casualtyPurchasingListImportTemple.setContactPhone("02296584652");
        casualtyPurchasingListImportTemple.setContactAddress("广州天河区");
        casualtyPurchasingListImportTemple.setContactZipcode("023695");
        casualtyPurchasingListImportTemple.setSocialSecurityArea("北京");
        casualtyPurchasingListImportTemple.setSocialSecurityCard("156985236989");
        casualtyPurchasingListImportTemple.setInsuranceCard("36957456698456625");
        casualtyPurchasingListImportTemple.setBankAccount("存钱");
        casualtyPurchasingListImportTemple.setOpenAccountName("小朋友");
        casualtyPurchasingListImportTemple.setOpenIdType("身份证");
        casualtyPurchasingListImportTemple.setOpenIdNo("695465552654456225");
        casualtyPurchasingListImportTemple.setOpenBankCode("6597");
        casualtyPurchasingListImportTemple.setOpenFullName("太平洋保险");
        casualtyPurchasingListImportTemple.setOpenBankAccountNum("695465552654456225");
        casualtyPurchasingListImportTemples.add(casualtyPurchasingListImportTemple);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingListImportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<CasualtyPurchasingListTO> casualtyPurchasingListTOS) throws SerException {
        checkPermission();
        List<CasualtyPurchasingList> casualtyPurchasingLists = BeanTransform.copyProperties(casualtyPurchasingListTOS, CasualtyPurchasingList.class, true);
        casualtyPurchasingLists.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(casualtyPurchasingLists);
    }

    @Override
    public Integer findCasualtyIncreaseNum(String[] date) throws SerException {
        Integer num = 0;
        CasualtyPurchasingListDTO casualtyPurchasingListDTO = new CasualtyPurchasingListDTO();
        casualtyPurchasingListDTO.getConditions().add(Restrict.between("effectiveDate",date));
        List<CasualtyPurchasingList> casualtyPurchasingLists = super.findByCis(casualtyPurchasingListDTO);
        if(casualtyPurchasingLists!=null && casualtyPurchasingLists.size()>0){
            num = casualtyPurchasingLists.size();
        }
        return num;
    }

    @Override
    public Integer findCasualtyIncreaseNum(String endDate) throws SerException {
        Integer num = 0;
        CasualtyPurchasingListDTO casualtyPurchasingListDTO = new CasualtyPurchasingListDTO();
        casualtyPurchasingListDTO.getConditions().add(Restrict.lt_eq("effectiveDate",endDate));
        List<CasualtyPurchasingList> casualtyPurchasingLists = super.findByCis(casualtyPurchasingListDTO);
        if(casualtyPurchasingLists!=null && casualtyPurchasingLists.size()>0){
            num = casualtyPurchasingLists.size();
        }
        return num;
    }
}