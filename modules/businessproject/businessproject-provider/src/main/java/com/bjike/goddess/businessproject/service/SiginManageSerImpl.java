package com.bjike.goddess.businessproject.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.entity.SiginManage;
import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.excel.SiginManageExport;
import com.bjike.goddess.businessproject.excel.SiginManageTemplateExport;
import com.bjike.goddess.businessproject.excel.SonPermissionObject;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
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
import java.util.stream.Collectors;

/**
 * 商务项目合同签订与立项管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.303 ]
 * @Description: [ 商务项目合同签订与立项管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class SiginManageSerImpl extends ServiceImpl<SiginManage, SiginManageDTO> implements SiginManageSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DispatchSheetSer dispatchSheetSer;
    @Autowired
    private ContractCategorySer contractCategorySer;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private BaseInfoManageSer baseInfoManageSer;


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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("siginmanage");
        obj.setDescribesion("商务项目合同签订与立项");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = dispatchSheetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("dispatchsheet");
        obj.setDescribesion("商务项目派工单信息管理");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = contractCategorySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contractcategory");
        obj.setDescribesion("商务项目合同类型");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("商务项目合同邮件发送");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = baseInfoManageSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("baseinfomanage");
        obj.setDescribesion("商务项目合同基本信息");
        if (flagSeeBase) {
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
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long countSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        searchCondition(siginManageDTO);
        Long count = super.count(siginManageDTO);
        return count;
    }

    @Override
    public SiginManageBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }

        SiginManage siginManage = super.findById(id);
        return BeanTransform.copyProperties(siginManage, SiginManageBO.class);
    }

    @Override
    public List<SiginManageBO> listSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        checkSeeIdentity();

        searchCondition(siginManageDTO);
        List<SiginManage> list = super.findByPage(siginManageDTO);
        List<SiginManageBO> siginManageBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            SiginManageBO bo = BeanTransform.copyProperties(str, SiginManageBO.class, "businessType", "businessCooperate", "contractProperty");
            bo.setBusinessType(str.getBusinessType());
            bo.setBusinessCooperate(str.getBusinessCooperate());
            bo.setContractProperty(str.getContractProperty());
            siginManageBOS.add(bo);
        });
        return siginManageBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO addSiginManage(SiginManageTO siginManageTO) throws SerException {
        checkAddIdentity();
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(siginManageTO.getStartProjectTime(),format);
        try {
            DateUtil.parseDate(siginManageTO.getStartProjectTime());
            DateUtil.parseDate(siginManageTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);

        siginManage.setCreateTime(LocalDateTime.now());
        super.save(siginManage);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(siginManage, SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO editSiginManage(SiginManageTO siginManageTO) throws SerException {
        checkAddIdentity();

        SiginManage temp = super.findById(siginManageTO.getId());

        try {
            DateUtil.parseDate(siginManageTO.getStartProjectTime());
            DateUtil.parseDate(siginManageTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }

        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);
        BeanUtils.copyProperties(siginManage, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp, SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSiginManage(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO auditSiginManage(SiginManageTO siginManageTO) throws SerException {
        if (StringUtils.isBlank(siginManageTO.getId())) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SiginManage temp = super.findById(siginManageTO.getId());

        temp.setManager(userAPI.currentUser().getUsername());
        temp.setAuditAdvice(siginManageTO.getAuditAdvice());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp, SiginManageBO.class);
        return siginManageBO;
    }


    public void searchCondition(SiginManageDTO siginManageDTO) throws SerException {
        /**
         * 业务类型
         */
        if (siginManageDTO.getBusinessType() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("businessType", siginManageDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(siginManageDTO.getBusinessSubject())) {
            siginManageDTO.getConditions().add(Restrict.like("businessSubject", siginManageDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (siginManageDTO.getBusinessCooperate() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("businessCooperate", siginManageDTO.getBusinessCooperate()));
        }
        /**
         * 甲方公司
         */
        if (StringUtils.isNotBlank(siginManageDTO.getFirstCompany())) {
            siginManageDTO.getConditions().add(Restrict.like("firstCompany", siginManageDTO.getFirstCompany()));
        }
        /**
         * 乙方公司
         */
        if (StringUtils.isNotBlank(siginManageDTO.getSecondCompany())) {
            siginManageDTO.getConditions().add(Restrict.like("secondCompany", siginManageDTO.getSecondCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(siginManageDTO.getArea())) {
            siginManageDTO.getConditions().add(Restrict.like("area", siginManageDTO.getArea()));
        }
        /**
         * 合同属性
         */
        if (siginManageDTO.getContractProperty() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("contractProperty", siginManageDTO.getContractProperty()));
        }
        /**
         * 立项情况
         */
        if (StringUtils.isNotBlank(siginManageDTO.getMakeProject())) {
            siginManageDTO.getConditions().add(Restrict.eq("makeProject", siginManageDTO.getMakeProject()));
        }


    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<SiginManageBO> siginManageBOS = super.findBySql("select area from businessproject_siginmanage group by area order by area asc ", SiginManageBO.class, fields);

        List<String> areaList = siginManageBOS.stream().map(SiginManageBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Transactional(rollbackFor = SerException.class )
    @Override
    public SiginManageBO importExcel(List<SiginManageTO> siginManageTO) throws SerException {

        List<SiginManage> siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);
        siginManage.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(siginManage);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(new SiginManage(), SiginManageBO.class);
        return siginManageBO;
    }

    @Override
    public byte[] exportExcel(SiginManageDTO dto) throws SerException {
//        getCusPermission();

        if (StringUtils.isNotBlank(dto.getInnerProject())) {
            dto.getConditions().add(Restrict.eq("innerProject", dto.getInnerProject()));
        }

        List<SiginManage> list = super.findByCis(dto);

        List<SiginManageExport> siginManageExports = new ArrayList<>();
        list.stream().forEach(str -> {
            SiginManageExport excel = BeanTransform.copyProperties(str, SiginManageExport.class, "businessType", "businessCooperate", "contractProperty");
            excel.setBusinessType(BusinessType.exportStrConvert(str.getBusinessType()));
            excel.setBusinessCooperate(BusinessCooperate.exportStrConvert(str.getBusinessCooperate()));
            excel.setContractProperty(ContractProperty.exportStrConvert(str.getContractProperty()));
            siginManageExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, excel);
        return bytes;
    }


    @Override
    public byte[] templateExport() throws SerException {
//        getCusPermission();

        List<SiginManageTemplateExport> siginManageExports = new ArrayList<>();

        SiginManageTemplateExport excel = new SiginManageTemplateExport();
        excel.setBusinessType("移动通信类");
        excel.setBusinessSubject( "test" );
        excel.setBusinessCooperate("租赁合同");
        excel.setOuterProject("test");
        excel.setFirstCompany("test");
        excel.setSecondCompany( "test");
        excel.setArea("test");
        excel.setMoney( 12.0d );
        excel.setStartProjectTime(LocalDate.now() );
        excel.setEndProjectTime( LocalDate.now() );
        excel.setSiginStatus("已签订" );
        excel.setContractProperty( "框架合同");
        excel.setMakeProject( "已立项");
        excel.setInnerProject("test");
        excel.setProjectGroup( "test");
        excel.setProjectCharge("test");
        excel.setRemark("");
        siginManageExports.add( excel );

        SiginManageTemplateExport excel2 = new SiginManageTemplateExport();
        BeanUtils.copyProperties( excel , excel2);
        excel.setSiginStatus("未签订" );
        excel.setMakeProject( "未立项");
        siginManageExports.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, exce);
        return bytes;
    }



    @Override
    public List<String> listInnerProject() throws SerException {
        String[] fields = new String[]{"innerProject"};
        List<SiginManageBO> siginManageBOS = super.findBySql("select innerProject from businessproject_siginmanage group by innerProject order by innerProject asc ", SiginManageBO.class, fields);

        List<String> innerProjectList = siginManageBOS.stream().map(SiginManageBO::getInnerProject)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());


        return innerProjectList;
    }
}