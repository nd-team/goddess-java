package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.BusinessBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessCompanySubBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessReturnBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.Business;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.BusinessExportExcel;
import com.bjike.goddess.marketdevelopment.excel.BusinessImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessTO;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public List<BusinessBO> maps(BusinessDTO dto) throws SerException {
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("companyNum=asc");
        List<Business> businesses = super.findByPage(dto);
        if (null == businesses || businesses.size() < 1) {
            return null;
        }
        List<String> stringList = businesses.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());

        for (String str : stringList) {
            BusinessDTO dto1 = new BusinessDTO();
            dto1.getConditions().add(Restrict.eq("businessType", str));
            List<Business> businesses1 = super.findByCis(dto1, Boolean.TRUE);

            List<BusinessCompanySubBO> businessCompanySubBOs = BeanTransform.copyProperties(businesses1, BusinessCompanySubBO.class);

        }
        return null;

    }

    @Override
    public void save(BusinessTO to) throws SerException {
        char businessNum = 'A';
        int companyNum = 1;
        int num = 1;
        Business entity = BeanTransform.copyProperties(to, Business.class);
        List<Business> Businessess = super.findAll();
        if (null != Businessess && Businessess.size() > 0) {
            BusinessDTO dto1 = new BusinessDTO();
            dto1.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
            List<Business> businessess1 = super.findByCis(dto1);
            if (null != businessess1 && businessess1.size() > 0) {
                businessNum = (char) businessess1.get(0).getBusinessNum().toCharArray()[0];
                companyNum = businessess1.size() + 1;
            } else {
                List<String> strList = Businessess.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());
                businessNum = (char) (strList.size() + 1);
            }
        }
        entity.setBusinessNum(String.valueOf(businessNum));
        entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
        entity.setSum(companyNum);

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
//        List<Business> businesses = super.findAll();
//        if (businesses.size() == 1) {
//            entity.setSum(companyNum);
//            entity.setBusinessNum(String.valueOf(businessNum));
//            entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
//            entity.setModifyTime(LocalDateTime.now());
//            super.update(entity);
//        } else {
//            BusinessDTO dto = new BusinessDTO();
//            dto.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
//            List<Business> businesses1 = super.findByCis(dto);
//            if (null != businesses1 && businesses1.size() > 0) {
//                businessNum = (char) businesses1.get(0).getBusinessNum().toCharArray()[0];
//                companyNum = businesses1.size() + 1;
//            } else {
//                List<String> stringList = businesses.stream().map(Business::getBusinessType).distinct().collect(Collectors.toList());
//                businessNum = (char) (stringList.size() + 1);
//                companyNum = 1;
//            }
//
//            entity.setBusinessNum(String.valueOf(businessNum));
//            entity.setCompanyNum(String.valueOf(businessNum) + "-" + companyNum);
            entity.setSum(companyNum);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
//        }
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

    @Override
    public void delete(BusinessTO to) throws SerException {
        Business entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
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
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessImportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<BusinessImportExcel> tos) throws SerException {
        for(BusinessImportExcel excel : tos){
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
                for(String string : companyList){
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
            int rowSize = businesses.size();


            int index = 0;
            int lastRow = 0;
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

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }
                }

                if (null != mainTableLen1 && mainTableLen1.size() > 0) {
                    int mergeRowCount = mainTableLen1.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            lastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 2; i < 4; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                        }
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, 5, 5));

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }


}