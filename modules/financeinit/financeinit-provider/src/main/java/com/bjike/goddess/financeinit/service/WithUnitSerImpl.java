package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.entity.WithUnit;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.excel.WithUnitExport;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.WithUnitTO;
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
 * 往来单位业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class WithUnitSerImpl extends ServiceImpl<WithUnit, WithUnitDTO> implements WithUnitSer {
    @Autowired
    private AccountanCourseSer accountanCourseSer;
    @Autowired
    private AccountDepartmentSer accountDepartmentSer;
    @Autowired
    private AccountSer accountSer;
    @Autowired
    private BaseParameterSer baseParameterSer;
    @Autowired
    private CompanyBasicInfoSer companyBasicInfoSer;
    @Autowired
    private CurrencySer currencySer;
    @Autowired
    private InitDateEntrySer initDateEntrySer;
    @Autowired
    private ProofWordsSer proofWordsSer;
    @Autowired
    private UseCommonlySer useCommonlySer;
    @Autowired
    private WithUnitSer withUnitSer;

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
     * 核对添加修改删除审核权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（部门级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFinanSup = guideFinanDepartSup();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("withunit");
        obj.setDescribesion("往来单位");
        if (flagSeeSign || flagAddSign || flagFinanSup) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeCourse = accountanCourseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountancourse");
        obj.setDescribesion("会计科目");
        if (flagSeeCourse) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDepart = accountDepartmentSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountdepartment");
        obj.setDescribesion("核算部门");
        if (flagSeeDepart) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeCate = accountSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("account");
        obj.setDescribesion("账户来源");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeParam = baseParameterSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("baseparameter");
        obj.setDescribesion("财务基本参数");
        if (flagSeeParam) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeComInfo = companyBasicInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("companybasicinfo");
        obj.setDescribesion("公司基本信息");
        if (flagSeeComInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeCurren = currencySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("currency");
        obj.setDescribesion("设置币别");
        if (flagSeeCurren) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDateEn = initDateEntrySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("initdateentry");
        obj.setDescribesion("财务初始化");
        if (flagSeeDateEn) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeProof = proofWordsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("proofwords");
        obj.setDescribesion("凭证字");
        if (flagSeeProof) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeUse = useCommonlySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("usecommonly");
        obj.setDescribesion("常用摘要");
        if (flagSeeUse) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
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
        return flag;
    }

    @Override
    public Long countWith(WithUnitDTO withUnitDTO) throws SerException {
        Long count = super.count(withUnitDTO);
        return count;
    }

    @Override
    public WithUnitBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        WithUnit withUnit = super.findById(id);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Override
    public List<WithUnitBO> listWith(WithUnitDTO withUnitDTO) throws SerException {
        checkSeeIdentity();
        List<WithUnit> list = super.findByCis(withUnitDTO, true);
        return BeanTransform.copyProperties(list, WithUnitBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public WithUnitBO addWith(WithUnitTO withUnitTO) throws SerException {
        checkFinanDepartSup();
        WithUnit withUnit = BeanTransform.copyProperties(withUnitTO, WithUnit.class, true);
        withUnit.setCreateTime(LocalDateTime.now());
        super.save(withUnit);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public WithUnitBO editWith(WithUnitTO withUnitTO) throws SerException {
        checkFinanDepartSup();
        WithUnit withUnit = super.findById(withUnitTO.getId());
        LocalDateTime date = withUnit.getCreateTime();
        withUnit = BeanTransform.copyProperties(withUnitTO, WithUnit.class);
        withUnit.setCreateTime(date);
        withUnit.setModifyTime(LocalDateTime.now());
        super.update(withUnit);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteWith(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkAddIdentity();
        List<WithUnit> list = super.findAll();
        List<WithUnitExport> withUnitExports = new ArrayList<>();

        for (WithUnit withUnit : list) {
            WithUnitExport excel = BeanTransform.copyProperties(withUnit, WithUnitExport.class);
            withUnitExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(withUnitExports, excel);
        return bytes;
    }
}