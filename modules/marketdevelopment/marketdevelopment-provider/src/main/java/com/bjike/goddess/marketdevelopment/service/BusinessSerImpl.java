package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.BusinessBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessCompanyBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessCompanySubBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessReturnBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.Business;
import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.BusinessExportExcel;
import com.bjike.goddess.marketdevelopment.excel.BusinessImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务对象业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class BusinessSerImpl extends ServiceImpl<Business, BusinessDTO> implements BusinessSer {

    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BusinessCourseSer businessCourseSer;
    @Autowired
    private CustomerSer customerSer;
    @Autowired
    private PlanYearSubjectSer planYearSubjectSer;
    @Autowired
    private PlanYearTypeSer planYearTypeSer;
    @Autowired
    private MonthSubjectProvincialSer monthSubjectProvincialSer;
    @Autowired
    private MonthSubjectSer monthSubjectSer;
    @Autowired
    private WeekCycleSer weekCycleSer;
    @Autowired
    private PlanDaySer planDaySer;
    @Autowired
    private OutBillSer outBillSer;
    @Autowired
    private DateDataSer dateDataSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("business");
        obj.setDescribesion("业务对象");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSee = businessCourseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("businesscourse");
        obj.setDescribesion("业务方向");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = customerSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("customer");
        obj.setDescribesion("客户接触阶段");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = planYearSubjectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("planyearsubject");
        obj.setDescribesion("年计划(科目方向)");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = planYearTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("planyeartype");
        obj.setDescribesion("年计划");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = monthSubjectProvincialSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("monthsubjectprovincial");
        obj.setDescribesion("月计划省市方向");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = monthSubjectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("monthSubject");
        obj.setDescribesion("月计划");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = weekCycleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("weekcycle");
        obj.setDescribesion("周计划");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = dateDataSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("datedata");
        obj.setDescribesion("日汇总");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = planDaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("planday");
        obj.setDescribesion("日计划");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = outBillSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outbill");
        obj.setDescribesion("外出单");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            flag = marPermissionSer.getMarPermission(planCheck);
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
            flag = marPermissionSer.getMarPermission(planManage);
        } else {
            flag = true;
        }
        return flag;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public List<BusinessBO> maps(BusinessDTO dto) throws SerException {
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("companyNum=asc");
        List<Business> businesses = super.findByPage(dto);
        if (null == businesses || businesses.size() < 1) {
            return null;
        }
        List<String> stringList = businesses.stream().map(Business::getBusinessNum).distinct().collect(Collectors.toList());
        List<BusinessBO> bos = new ArrayList<>(0);
        for (String str : stringList) {
            BusinessDTO businessDTO = new BusinessDTO();
            businessDTO.getConditions().add(Restrict.eq("businessNum", str));
            List<Business> businesses1 = super.findByCis(businessDTO);
            List<BusinessCompanyBO> businessCompanyBOs = new ArrayList<>(0);
            if (null != businesses1 && businesses1.size() > 0) {
                BusinessBO bo = BeanTransform.copyProperties(businesses1.get(0), BusinessBO.class);
//                for (Business business : businesses1) {
                List<String> stringList1 = businesses1.stream().map(Business::getCompanyNum).distinct().collect(Collectors.toList());
                List<BusinessCompanyBO> businessCompanyBOs1 = new ArrayList<>(0);
                for (String string : stringList1) {
                    BusinessDTO businessDTO2 = new BusinessDTO();
                    businessDTO2.getConditions().add(Restrict.eq("companyNum", string));
                    List<Business> businesses2 = super.findByCis(businessDTO2);
                    if (null != businesses2 && businesses2.size() > 0) {
                        BusinessCompanyBO businessCompanyBO = BeanTransform.copyProperties(businesses2.get(0), BusinessCompanyBO.class);
                        List<BusinessCompanySubBO> businessCompanySubBOs = BeanTransform.copyProperties(businesses2, BusinessCompanySubBO.class);
                        businessCompanyBO.setBusinessCompanySubVOs(businessCompanySubBOs);
                        businessCompanyBOs1.add(businessCompanyBO);
                    }
                }
                bo.setBusinessCompanyVOs(businessCompanyBOs1);

                bos.add(bo);
            }
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(BusinessTO to) throws SerException {
        int total = 1;
        char businessNum = 'A';
        int companyNum = 1;
        int num = 1;
        Business entity = BeanTransform.copyProperties(to, Business.class);
        List<Business> businessess = super.findAll();
        if (null != businessess && businessess.size() > 0) {
            BusinessDTO dto1 = new BusinessDTO();
            dto1.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
            List<Business> businessess1 = super.findByCis(dto1);
            if (null != businessess1 && businessess1.size() > 0) {
                businessNum = (char) businessess1.get(0).getBusinessNum().toCharArray()[0];
                //相同的公司名称,公司名称编号不变
                BusinessDTO dto2 = new BusinessDTO();
                dto2.getConditions().add(Restrict.eq("company", to.getCompany()));
                dto2.getConditions().add(Restrict.eq("businessType", to.getBusinessType()));
                List<Business> businessess2 = super.findByCis(dto2);
                if (null != businessess2 && businessess2.size() > 0) {
                    entity.setCompanyNum(businessess2.get(0).getCompanyNum());
                } else {
                    companyNum = businessess1.stream().map(Business::getCompany).distinct().collect(Collectors.toList()).size() + 1;
                }

            } else {
                List<String> strList = businessess.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());
                businessNum = (char) (businessNum + strList.size());
            }
        }
        entity.setBusinessNum(String.valueOf(businessNum));
        if (StringUtils.isBlank(entity.getCompanyNum())) {
            entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
        }

        //查出所有的公司名称编号
        BusinessDTO dto3 = new BusinessDTO();
        dto3.getConditions().add(Restrict.eq("companyNum", entity.getCompanyNum()));
        List<Business> businessess3 = super.findByCis(dto3);
        if (null != businessess3 && businessess3.size() > 0) {
            total += businessess3.size();
            for (Business business : businessess3) {
                business.setSum(total);
                super.update(business);
            }
        }
        entity.setSum(total);

        super.save(entity);
    }

    @Override
    public void update(BusinessTO to) throws SerException {
        char businessNum = 'A';
        int companyNum = 1;
        Business entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity);
        List<Business> businesses = super.findAll();
        if (businesses.size() == 1) {
            entity.setSum(companyNum);
            entity.setBusinessNum(String.valueOf(businessNum));
            entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            BusinessDTO dto = new BusinessDTO();
            dto.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
            List<Business> businesses1 = super.findByCis(dto);
            if (null != businesses1 && businesses1.size() > 0) {
                businessNum = (char) businesses1.get(0).getBusinessNum().toCharArray()[0];
                //相同的公司名称,公司名称编号不变
                BusinessDTO dto2 = new BusinessDTO();
                dto2.getConditions().add(Restrict.eq("company", to.getCompany()));
                dto2.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
                List<Business> businessess2 = super.findByCis(dto2);
                if (null != businessess2 && businessess2.size() > 0) {
                    entity.setCompanyNum(businessess2.get(0).getCompanyNum());
                } else {
                    companyNum = businesses1.stream().map(Business::getCompany).distinct().collect(Collectors.toList()).size() + 1;
                }
            } else {
                List<String> stringList = businesses.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());
                businessNum = (char) (stringList.size() + businessNum);
                companyNum = 1;
            }

            entity.setBusinessNum(String.valueOf(businessNum));
            entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
            //查出所有的公司名称编号
            int total = 1;
            entity.setSum(total);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            BusinessDTO dto4 = new BusinessDTO();
//            dto4.getConditions().add(Restrict.eq("companyNum", entity.getCompanyNum()));
            List<Business> businessess4 = super.findByCis(dto4);
            if (null != businessess4 && businessess4.size() > 0) {
                for (Business business : businessess4) {
                    total = businessess4.stream().filter(obj -> obj.getCompanyNum().equals(business.getCompanyNum())).collect(Collectors.toList()).size();
                    business.setSum(total);
                    super.update(business);
                }
            }
        }
    }

    @Override
    public void congeal(BusinessTO to) throws SerException {
        Business entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(BusinessTO to) throws SerException {
        Business entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(BusinessTO to) throws SerException {
        Business entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);

        //修改科目合计
        BusinessDTO dto = new BusinessDTO();
//        dto.getSorts().add("businessNum=asc");
        List<Business> businesses = super.findByCis(dto);
        if (null != businesses && businesses.size() > 0) {
            for (Business business : businesses) {
                int total = businesses.stream().filter(obj -> obj.getCompanyNum().equals(business.getCompanyNum())).collect(Collectors.toList()).size();
                business.setSum(total);
                super.update(business);
            }
        }
        //修改业务对类型编号
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.getConditions().add(Restrict.eq("businessNum", entity.getBusinessNum()));
        List<Business> businesses1 = super.findByCis(businessDTO);
        if (null == businesses1 || businesses1.size() < 1) {
            int code = entity.getBusinessNum().toCharArray()[0];
            //修改所有的业务类型编号
            if (null != businesses && businesses.size() > 0) {
                List<Business> businesses2 = businesses.stream().filter(obj -> obj.getBusinessNum().toCharArray()[0] > code).sorted(Comparator.comparing(Business::getBusinessNum)).collect(Collectors.toList());
                for (Business business : businesses2) {
                    int busiNum = business.getBusinessNum().toCharArray()[0];
                    char businessNum = (char) (business.getBusinessNum().toCharArray()[0] - 1);
                    business.setBusinessNum(String.valueOf(businessNum));
                    String temp = business.getCompanyNum().substring(business.getCompanyNum().indexOf("-"), business.getCompanyNum().length());
                    business.setCompanyNum(business.getBusinessNum() + temp);
                    super.update(business);
                }
            }
        } else {
            //需改公司编号
            List<Business> businesses2 = businesses1.stream().filter(obj -> Integer.valueOf(obj.getCompanyNum().substring(obj.getCompanyNum().indexOf("-") + 1, obj.getCompanyNum().length())) > Integer.valueOf(entity.getCompanyNum().substring(entity.getCompanyNum().indexOf("-") + 1, entity.getCompanyNum().length()))).collect(Collectors.toList());

            for (Business business : businesses2) {
                int companyNum = Integer.valueOf(business.getCompanyNum().substring(business.getCompanyNum().indexOf("-") + 1, business.getCompanyNum().length()));
                business.setCompanyNum(business.getBusinessNum() + "-" + (companyNum - 1));
                super.update(business);
            }
        }
    }

    public static void main(String args[]) {
        char q = 'A';
        int w = q;
        System.out.print(w);

    }

    @Override
    public BusinessReturnBO getById(String id) throws SerException {
        Business entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BusinessReturnBO bo = BeanTransform.copyProperties(entity, BusinessReturnBO.class);
        return bo;
    }

    @Override
    public Long getTotal(BusinessDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        BusinessImportExcel businessImportExcel = new BusinessImportExcel();
        List<BusinessImportExcel> businessImportExcels = new ArrayList<>(0);
        businessImportExcels.add(businessImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessImportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<BusinessImportExcel> tos) throws SerException {
        for (BusinessImportExcel excel : tos) {
            BusinessTO to = BeanTransform.copyProperties(excel, BusinessTO.class);
            save(to);
        }
    }

    @Override
    public byte[] exportExcel(BusinessDTO dto) throws SerException {
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("companyNum=asc");
        List<Business> businesses = super.findByCis(dto);
        List<BusinessExportExcel> businessExportExcels = BeanTransform.copyProperties(businesses, BusinessExportExcel.class);


        List<Integer> mainTableLen = new ArrayList<>(0);
        List<Integer> mainTableLen1 = new ArrayList<>(0);
        List<String> stringList = businesses.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());
        if (null != stringList && stringList.size() > 0) {
            for (String str : stringList) {
                BusinessDTO dto1 = new BusinessDTO();
                dto1.getConditions().add(Restrict.eq("businessType", str));
                List<Business> businessCourseList = super.findByCis(dto1);
                mainTableLen.add(businessCourseList.size());

                List<String> companyList = businessCourseList.stream().map(Business::getCompany).distinct().collect(Collectors.toList());
                for (String string : companyList) {
                    BusinessDTO dto2 = new BusinessDTO();
                    dto2.getConditions().add(Restrict.eq("company", string));
                    List<Business> businesses1 = super.findByCis(dto2);
                    mainTableLen1.add(businesses1.size());
                }
            }
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessExportExcels, excel);

        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = businesses.stream().map(Business::getBusinessNum).distinct().collect(Collectors.toList()).size();


            int index = 0;
            int lastRow = 0;

            int minIndex = 0;
            int minLastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                //List<int> maxList所有子表的长度
                if (null != mainTableLen && mainTableLen.size() > 0) {
                    int mergeRowCount = mainTableLen.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            lastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 0; i < 2; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                        }
                        index = ++lastRow;

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }else {
                        index += 1;
                    }
                }
            }
            for (int j = 0; j < mainTableLen1.size(); j++) {
                if (null != mainTableLen1 && mainTableLen1.size() > 0) {
                    int mergeRowCount = mainTableLen1.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = minIndex;
                        minLastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            minLastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 2; i < 4; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, minLastRow, i, i));
                        }
                        minIndex = ++minLastRow;
//                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, 5, 5));

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    } else {
                        minIndex += 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

}