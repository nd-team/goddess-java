package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.*;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.CompanyBasicInfoExport;
import com.bjike.goddess.financeinit.to.CompanyBasicInfoTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司基本信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CompanyBasicInfoSerImpl extends ServiceImpl<CompanyBasicInfo, CompanyBasicInfoDTO> implements CompanyBasicInfoSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountSer accountSer;
    @Autowired
    private AccountanCourseSer accountanCourseSer;
    @Autowired
    private AccountDepartmentSer accountDepartmentSer;
    @Autowired
    private BaseParameterSer baseParameterSer;
    @Autowired
    private InitDateEntrySer initDateEntrySer;
    @Autowired
    private ProofWordsSer proofWordsSer;
    @Autowired
    private UseCommonlySer useCommonlySer;
    @Autowired
    private WithUnitSer withUnitSer;
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
        RpcTransmit.transmitUserToken(userToken);
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
    public Long countBasicInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        Long count = super.count(companyBasicInfoDTO);
        return count;
    }

    @Override
    public CompanyBasicInfoBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        CompanyBasicInfo companyBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoBO.class);
    }

    @Override
    public List<CompanyBasicInfoBO> listBaseInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        checkSeeIdentity();
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        //TODO: 由于还不知道怎么获取当前用户属于那个公司,所以这里先固定当前用户为"北京艾佳天城信息技术有限公司"
        String companyName = "北京艾佳天城信息技术有限公司";
        Boolean bool = checkExist(companyName);
        if(!bool){
            if("IKE009999".equalsIgnoreCase(userBO.getEmployeeNumber()) || "admin".equals(userBO.getUsername())){
                throw new SerException("公司的基本信息未完善，请财务主管进行完善");
            }else{
                throw new SerException("财务主管请进行完善信息");
            }
        }

        List<CompanyBasicInfo> list = super.findByCis(companyBasicInfoDTO, true);
        return BeanTransform.copyProperties(list, CompanyBasicInfoBO.class);
    }

    private Boolean checkExist(String companyName)throws SerException{
        Boolean bool = false;
        if(StringUtils.isNotBlank(companyName)){
            CompanyBasicInfoDTO companyBasicInfoDTO = new CompanyBasicInfoDTO();
            companyBasicInfoDTO.getConditions().add(Restrict.eq("companyName",companyName));
            CompanyBasicInfo companyBasicInfo = super.findOne(companyBasicInfoDTO);
            if(null != companyBasicInfo){
               bool = true;
            }
        }
        return bool;
    }


    @Transactional(rollbackFor = {SerException.class})
    @Override
    public CompanyBasicInfoBO addBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        checkFinanDepartSup();
        CompanyBasicInfo companyBasicInfo = BeanTransform.copyProperties(companyBasicInfoTO, CompanyBasicInfo.class, true);
        companyBasicInfo.setCreateTime(LocalDateTime.now());
        super.save(companyBasicInfo);
        return BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public CompanyBasicInfoBO editBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        checkFinanDepartSup();
        CompanyBasicInfo companyBasicInfo = super.findById(companyBasicInfoTO.getId());
        LocalDateTime date = companyBasicInfo.getCreateTime();
        companyBasicInfo = BeanTransform.copyProperties(companyBasicInfoTO, CompanyBasicInfo.class);
        companyBasicInfo.setCreateTime(date);
        companyBasicInfo.setModifyTime(LocalDateTime.now());
        super.update(companyBasicInfo);
        return BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteBaseInfo(String id) throws SerException {
        checkFinanDepartSup();
        //只有当【公司初始化】【财务初始化】里面的数据都清空的时候，财务主管才能进行【删除】操作 这个需求为实现,需要问清楚需求
        if(accountSer.countAccount(new AccountDTO())!=0){
            throw new SerException("请先清空账户来源的数据");
        }
        if(accountanCourseSer.count(new AccountanCourseDTO())!=0){
            throw new SerException("请先清空会计科目的数据");
        }
        if(baseParameterSer.countBasicPara(new BaseParameterDTO())!=0){
            throw new SerException("请先清空公司初始化的数据");
        }
        if(currencySer.countCurren(new CurrencyDTO())!=0){
            throw new SerException("请先清空币别的数据");
        }
        if(initDateEntrySer.countInit(new InitDateEntryDTO())!=0){
            throw new SerException("请先清空财务初始化的数据");
        }
        if(proofWordsSer.countProof(new ProofWordsDTO())>1){
            throw new SerException("请先清空凭证字的数据");
        }
        if(useCommonlySer.countUse(new UseCommonlyDTO())>1){
            throw new SerException("请先清空常用摘要的数据");
        }
        if(withUnitSer.countWith(new WithUnitDTO())!=0){
            throw new SerException("请先清空往来单位的数据");
        }
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkAddIdentity();
        List<CompanyBasicInfo> list = super.findAll();
        List<CompanyBasicInfoExport> companyBasicInfoExports = new ArrayList<>();

        for (CompanyBasicInfo companyBasicInfo : list) {
            CompanyBasicInfoExport excel = BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoExport.class);
            companyBasicInfoExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(companyBasicInfoExports, excel);
        return bytes;
    }

    @Override
    public List<String> findCompanyName() throws SerException {
        List<CompanyBasicInfo> companyBasicInfos = super.findAll();
        List<String> CompanyNames = new ArrayList<>();
        if (companyBasicInfos != null && companyBasicInfos.size() > 0) {
            for (CompanyBasicInfo companyBasicInfo : companyBasicInfos) {
                CompanyNames.add(companyBasicInfo.getCompanyName());
            }
        }
        return CompanyNames;
    }

    @Override
    public CompanyBasicInfoBO findByCompanyName(String companyName) throws SerException {
        CompanyBasicInfoDTO companyBasicInfoDTO = new CompanyBasicInfoDTO();
        companyBasicInfoDTO.getConditions().add(Restrict.eq("companyName", companyName));
        CompanyBasicInfo companyBasicInfo = super.findOne(companyBasicInfoDTO);
        return BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoBO.class);
    }

}