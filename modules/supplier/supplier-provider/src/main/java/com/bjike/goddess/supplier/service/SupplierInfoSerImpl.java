package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.bo.SummationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.bo.SupplierInfoRegistraDataBO;
import com.bjike.goddess.supplier.dto.*;
import com.bjike.goddess.supplier.entity.*;
import com.bjike.goddess.supplier.excel.SupplierInfoImport;
import com.bjike.goddess.supplier.excel.SupplierInfoImportTemple;
import com.bjike.goddess.supplier.to.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商信息管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierInfoSerImpl extends ServiceImpl<SupplierInfo, SupplierInfoDTO> implements SupplierInfoSer {
    @Autowired
    private SupplierInfoRegistraSer supplierInfoRegistraSer;
    @Autowired
    private EnterpriseQualificaSer enterpriseQualificaSer;
    @Autowired
    private AwardsSer awardsSer;
    @Autowired
    private ContactSituationSer contactSituationSer;
    @Autowired
    private ProvideProductSer provideProductSer;

    @Override
    public Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        seachCondi(supplierInfoDTO);
        Long count = super.count(supplierInfoDTO);
        return count;
    }

    @Override
    public SupplierInfoBO getOneById(String id) throws SerException {
        SupplierInfo supplierInfo = super.findById(id);
        return BeanTransform.copyProperties(supplierInfo, SupplierInfoBO.class);
    }

    @Override
    public List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        seachCondi(supplierInfoDTO);
        List<SupplierInfo> supplierInfoList = super.findByCis(supplierInfoDTO, true);
        return BeanTransform.copyProperties(supplierInfoList, SupplierInfoBO.class);
    }

    private void seachCondi(SupplierInfoDTO supplierInfoDTO) throws SerException {
        if (StringUtils.isNotBlank(supplierInfoDTO.getSupplierName())) {
            supplierInfoDTO.getConditions().add(Restrict.eq("supplierName", supplierInfoDTO.getSupplierName()));
        }
    }

    @Override
    public SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        SupplierInfo supplierInfo = BeanTransform.copyProperties(supplierInfoTO, SupplierInfo.class, true);
        supplierInfo.setCreateTime(LocalDateTime.now());
        super.save(supplierInfo);
        return BeanTransform.copyProperties(supplierInfo, SupplierInfoBO.class);
    }

    @Override
    public SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        SupplierInfo supplierInfo = super.findById(supplierInfoTO.getId());
        LocalDateTime dateTime = supplierInfo.getCreateTime();
        supplierInfo = BeanTransform.copyProperties(supplierInfoTO, SupplierInfo.class, true);
        supplierInfo.setModifyTime(LocalDateTime.now());
        supplierInfo.setCreateTime(dateTime);
        super.update(supplierInfo);
        return BeanTransform.copyProperties(supplierInfo, SupplierInfoBO.class);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<SupplierInfo> list = super.findAll();
        List<SupplierInfoImport> supplierInfoImports = new ArrayList<>();
        for (SupplierInfo supplierInfo : list) {
            SupplierInfoImport excel = BeanTransform.copyProperties(supplierInfo, SupplierInfoImport.class,
                    "uploadBusinLicense", "uploadQualifition", "deterCooper", "payComplete", "infoPerfecting");
            excel.setUploadBusinLicense(boolToString(supplierInfo.getUploadBusinLicense()));
            excel.setUploadQualifition(boolToString(supplierInfo.getUploadQualifition()));
            excel.setDeterCooper(boolToString(supplierInfo.getDeterCooper()));
            excel.setPayComplete(boolToString(supplierInfo.getPayComplete()));
            excel.setInfoPerfecting(boolToString(supplierInfo.getInfoPerfecting()));
            supplierInfoImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(supplierInfoImports, excel);
        return bytes;
    }

    private String boolToString(Boolean bool) throws SerException {
        String str = "";
        if (bool != null) {
            if (bool) {

                str = "是";
            } else {

                str = "否";
            }
        }
        return str;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SupplierInfoImportTemple> supplierInfoImportTemples = new ArrayList<>();
        SupplierInfoImportTemple excel = new SupplierInfoImportTemple();
        excel.setInfoCollectDate("text");
        excel.setInfoSource("text");
        excel.setMarketNum("text");
        excel.setBussType("text");
        excel.setSupplierNum("text");
        excel.setSupplierArea("text");
        excel.setSupplierName("text");
        excel.setSupplierType("text");
        excel.setBussScope("text");
        excel.setMajorProducts("text");
        excel.setBussLiaison("text");
        excel.setDuty("text");
        excel.setContactNum("text");
        excel.setMailBox("text");
        excel.setFax("text");
        excel.setInfoCompletion("text");
        excel.setUploadBusinLicense("text");
        excel.setUploadQualifition("text");
        excel.setLevelQualifition("text");
        excel.setDeterCooper("text");
        excel.setNewSigningDate("text");
        excel.setNewCutoffDate("text");
        excel.setPayComplete("text");
        excel.setSettlementCompleteDate("text");
        excel.setStatus("text");
        excel.setCooperationNum(100);
        excel.setInfoPerfecting("text");
        supplierInfoImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(supplierInfoImportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException {
        List<SupplierInfo> supplierInfoList = BeanTransform.copyProperties(supplierInfoTOList, SupplierInfo.class, true);
        supplierInfoList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(supplierInfoList);
    }

    @Override
    public SupplierInfoRegistraDataBO linkSupplierData(String id) throws SerException {
        SupplierInfo supplierInfo = super.findById(id);
        List<ContactSituationBO> contactSituationBOList = new ArrayList<>();
        ContactSituationBO contactSituationBO = new ContactSituationBO();
        contactSituationBO.setBussLiaison(supplierInfo.getBussLiaison());
        contactSituationBO.setContactNum(supplierInfo.getContactNum());
        contactSituationBO.setDuty(supplierInfo.getDuty());
        contactSituationBO.setEmail(supplierInfo.getMailBox());
        contactSituationBO.setFacsimile(supplierInfo.getFax());
        contactSituationBOList.add(contactSituationBO);
        SupplierInfoRegistraDataBO supplierInfoRegistraDataBO = new SupplierInfoRegistraDataBO();
        supplierInfoRegistraDataBO.setSupplierName(supplierInfo.getSupplierName());
        supplierInfoRegistraDataBO.setSupplierType(supplierInfo.getSupplierType());
        supplierInfoRegistraDataBO.setBussScope(supplierInfo.getBussScope());
        supplierInfoRegistraDataBO.setMainProtducts(supplierInfo.getMajorProducts());
        supplierInfoRegistraDataBO.setArea(supplierInfo.getSupplierArea());
        supplierInfoRegistraDataBO.setContactSituationBOList(contactSituationBOList);
        return supplierInfoRegistraDataBO;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addSupplierDetail(SupplierInfoRegistraDataTO supplierInfoRegistraDataTO) throws SerException {
        SupplierInfoRegistra supplierInfoRegistra = new SupplierInfoRegistra();
        if (StringUtils.isNotBlank(supplierInfoRegistraDataTO.getId())) {
            supplierInfoRegistra = supplierInfoRegistraSer.findById(supplierInfoRegistraDataTO.getId());
            LocalDateTime dateTime = supplierInfoRegistra.getCreateTime();
            supplierInfoRegistra = BeanTransform.copyProperties(supplierInfoRegistraDataTO, SupplierInfoRegistra.class, true);
            supplierInfoRegistra.setCreateTime(dateTime);
            supplierInfoRegistra.setModifyTime(LocalDateTime.now());
            supplierInfoRegistraSer.update(supplierInfoRegistra);
            //企业资质
            EnterpriseQualificaDTO enterpriseQualificaDTO = new EnterpriseQualificaDTO();
            enterpriseQualificaDTO.getConditions().add(Restrict.eq("supplierInfoRegiId", supplierInfoRegistra.getId()));
            List<EnterpriseQualifica> enterpriseQualificas = enterpriseQualificaSer.findByCis(enterpriseQualificaDTO);
            enterpriseQualificaSer.remove(enterpriseQualificas);
            //获奖情况
            AwardsDTO awardsDTO = new AwardsDTO();
            awardsDTO.getConditions().add(Restrict.eq("supplierInfoRegiId", supplierInfoRegistra.getId()));
            List<Awards> awards = awardsSer.findByCis(awardsDTO);
            awardsSer.remove(awards);
            //联络情况
            ContactSituationDTO contactSituationDTO = new ContactSituationDTO();
            contactSituationDTO.getConditions().add(Restrict.eq("supplierInfoRegiId", supplierInfoRegistra.getId()));
            List<ContactSituation> contactSituations = contactSituationSer.findByCis(contactSituationDTO);
            contactSituationSer.remove(contactSituations);
            //针对拟为我公司提供产品
            ProvideProductDTO provideProductDTO = new ProvideProductDTO();
            provideProductDTO.getConditions().add(Restrict.eq("supplierInfoRegiId", supplierInfoRegistra.getId()));
            List<ProvideProduct> provideProductList = provideProductSer.findByCis(provideProductDTO);
            provideProductSer.remove(provideProductList);
        } else {
            supplierInfoRegistra = BeanTransform.copyProperties(supplierInfoRegistraDataTO, SupplierInfoRegistra.class, true);
            supplierInfoRegistra.setCreateTime(LocalDateTime.now());
            supplierInfoRegistraSer.save(supplierInfoRegistra);
        }
        List<EnterpriseQualificaTO> enterpriseQualificaTOList = supplierInfoRegistraDataTO.getEnterpriseQualificaTOList();//企业资质
        List<AwardsTO> awardsTOList = supplierInfoRegistraDataTO.getAwardsTOList();//获奖情况
        List<ContactSituationTO> contactSituationTOList = supplierInfoRegistraDataTO.getContactSituationTOList(); //联络情况
        List<ProvideProductTO> provideProductTOList = supplierInfoRegistraDataTO.getProvideProductTOList();//针对拟为我公司提供产品
        if (enterpriseQualificaTOList != null && enterpriseQualificaTOList.size() > 0) {
            for (EnterpriseQualificaTO enterpriseQualificaTO : enterpriseQualificaTOList) {
                EnterpriseQualifica enterpriseQualifica = BeanTransform.copyProperties(enterpriseQualificaTO, EnterpriseQualifica.class, true);
                enterpriseQualifica.setCreateTime(LocalDateTime.now());
                enterpriseQualifica.setSupplierInfoRegiId(supplierInfoRegistra.getId());
                enterpriseQualificaSer.save(enterpriseQualifica);
            }
        }
        if (awardsTOList != null && awardsTOList.size() > 0) {
            for (AwardsTO awardsTO : awardsTOList) {
                Awards awards = BeanTransform.copyProperties(awardsTO, Awards.class, true);
                awards.setCreateTime(LocalDateTime.now());
                awards.setSupplierInfoRegiId(supplierInfoRegistra.getId());
                awardsSer.save(awards);
            }
        }
        if (contactSituationTOList != null && contactSituationTOList.size() > 0) {
            for (ContactSituationTO contactSituationTO : contactSituationTOList) {
                ContactSituation contactSituation = BeanTransform.copyProperties(contactSituationTO, ContactSituation.class, true);
                contactSituation.setCreateTime(LocalDateTime.now());
                contactSituation.setSupplierInfoRegiId(supplierInfoRegistra.getId());
                contactSituationSer.save(contactSituation);
            }
        }
        if (provideProductTOList != null && provideProductTOList.size() > 0) {
            for (ProvideProductTO provideProductTO : provideProductTOList) {
                ProvideProduct provideProduct = BeanTransform.copyProperties(provideProductTO, ProvideProduct.class, true);
                provideProduct.setSupplierInfoRegiId(supplierInfoRegistra.getId());
                provideProduct.setCreateTime(LocalDateTime.now());
                provideProductSer.save(provideProduct);
            }
        }
    }

    private List<String> findBussType() throws SerException {
        List<SupplierInfo> supplierInfoList = super.findAll();
        if (CollectionUtils.isEmpty(supplierInfoList)) {
            Collections.emptyList();
        }
        return supplierInfoList.stream().map(SupplierInfo::getBussType).distinct().collect(Collectors.toList());
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;

        return totalMethod(startDate,endDate);
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }


    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];

        return totalMethod(startDate,endDate);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));


        return totalMethod(startDate,endDate);
    }

    public String[] quarterChange(Integer year, Integer quarter) throws SerException {
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().toString();
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = LocalDate.now().toString();
                endDate = LocalDate.now().toString();
                break;
        }
        return new String[]{startDate, endDate};
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);

        return totalMethod(date[0],date[1]);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));

        return totalMethod(startDate,endDate);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        if (StringUtils.isBlank(endDate)) {
            endDate = LocalDate.now().toString();
        }
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) AS supplierMarketInfoNum FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' AND marketNum IS NOT NULL )a, ");
                sql.append(" (SELECT count(*) AS supplierNum FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' )b, ");
                sql.append(" (SELECT count(*) AS supplierImprovedNum FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' AND is_infoPerfecting=1)c, ");
                sql.append(" (SELECT count(*) AS supplierNoImprovedNum FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' AND is_infoPerfecting=0)d, ");
                sql.append(" (SELECT count(*) AS cooperatingSupplier FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' AND is_deterCooper=1)e, ");
                sql.append(" (SELECT count(*) AS noCooperationSupplier FROM supplier_supplierinfo WHERE infoCollectDate < '"+endDate+"' and bussType='"+bussType+"' AND is_deterCooper=0)f ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer supplierMarketInfoNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer supplierNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer supplierImprovedNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer supplierNoImprovedNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer cooperatingSupplier = Integer.parseInt(String.valueOf(objects[4]));
                Integer noCooperationSupplier = Integer.parseInt(String.valueOf(objects[5]));


                SummationBO summationBO = new SummationBO();
                summationBO.setBussType(bussType);
                summationBO.setSupplierMarketInfoNum(supplierMarketInfoNum);
                summationBO.setSupplierNum(supplierNum);
                summationBO.setSupplierImprovedNum(supplierImprovedNum);
                summationBO.setSupplierNoImprovedNum(supplierNoImprovedNum);
                summationBO.setCooperatingSupplier(cooperatingSupplier);
                summationBO.setNoCooperationSupplier(noCooperationSupplier);
                summationBOList.add(summationBO);
            }
        }
        return summationBOList;
    }

    private List<SummationBO> totalMethod(String startDate,String endDate) throws SerException{
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) AS supplierMarketInfoNum FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' AND marketNum IS NOT NULL )a, ");
                sql.append(" (SELECT count(*) AS supplierNum FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' )b, ");
                sql.append(" (SELECT count(*) AS supplierImprovedNum FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' AND is_infoPerfecting=1)c, ");
                sql.append(" (SELECT count(*) AS supplierNoImprovedNum FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' AND is_infoPerfecting=0)d, ");
                sql.append(" (SELECT count(*) AS cooperatingSupplier FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' AND is_deterCooper=1)e, ");
                sql.append(" (SELECT count(*) AS noCooperationSupplier FROM supplier_supplierinfo WHERE infoCollectDate BETWEEN '"+startDate+"' AND '"+endDate+"' and bussType='"+bussType+"' AND is_deterCooper=0)f ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer supplierMarketInfoNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer supplierNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer supplierImprovedNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer supplierNoImprovedNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer cooperatingSupplier = Integer.parseInt(String.valueOf(objects[4]));
                Integer noCooperationSupplier = Integer.parseInt(String.valueOf(objects[5]));


                SummationBO summationBO = new SummationBO();
                summationBO.setBussType(bussType);
                summationBO.setSupplierMarketInfoNum(supplierMarketInfoNum);
                summationBO.setSupplierNum(supplierNum);
                summationBO.setSupplierImprovedNum(supplierImprovedNum);
                summationBO.setSupplierNoImprovedNum(supplierNoImprovedNum);
                summationBO.setCooperatingSupplier(cooperatingSupplier);
                summationBO.setNoCooperationSupplier(noCooperationSupplier);
                summationBOList.add(summationBO);
            }
        }
        return summationBOList;
    }
}