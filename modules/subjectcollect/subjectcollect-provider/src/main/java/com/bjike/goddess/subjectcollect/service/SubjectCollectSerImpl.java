package com.bjike.goddess.subjectcollect.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.subjectcollect.bo.AreaSubjectBO;
import com.bjike.goddess.subjectcollect.bo.DepartmentSubjectBO;
import com.bjike.goddess.subjectcollect.bo.FirstSubjectBO;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectsBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectsDTO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.excel.SubjectIntroExport;
import com.bjike.goddess.subjectcollect.to.ExportSubjectCollectTO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 科目汇总业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-10-26 02:42 ]
 * @Description: [ 科目汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "subjectcollectSerCache")
@Service
public class SubjectCollectSerImpl extends ServiceImpl<SubjectCollect, SubjectCollectsDTO> implements SubjectCollectSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Override
    public List<FirstSubjectBO> collect(SubjectCollectsDTO dto) throws SerException {
        LocalDate[] dates = null;
        if (StringUtils.isNotBlank ( dto.getStartTime () ) && StringUtils.isNotBlank ( dto.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( dto.getStartTime () );
            LocalDate endDate = DateUtil.parseDate ( dto.getEndTime () );
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isNotBlank ( dto.getStartTime () ) && StringUtils.isBlank ( dto.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( dto.getStartTime () );
            LocalDate endDate = LocalDate.now ();
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isBlank ( dto.getStartTime () ) && StringUtils.isNotBlank ( dto.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( "1901-01-01" );
            LocalDate endDate = DateUtil.parseDate ( dto.getEndTime () );
            dates = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate ( "1901-01-01" );
            LocalDate endDate = LocalDate.now ();
            dates = new LocalDate[]{startDate, endDate};
        }
        return figureCollect ( dates );
    }

    @Override
    public byte[] exportExcel(ExportSubjectCollectTO to) throws SerException {
        LocalDate[] dates = null;
        String firstSubject = to.getFirstSubject ();
        if (StringUtils.isNotBlank ( to.getStartTime () ) && StringUtils.isNotBlank ( to.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( to.getStartTime () );
            LocalDate endDate = DateUtil.parseDate ( to.getEndTime () );
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isNotBlank ( to.getStartTime () ) && StringUtils.isBlank ( to.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( to.getStartTime () );
            LocalDate endDate = LocalDate.now ();
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isBlank ( to.getStartTime () ) && StringUtils.isNotBlank ( to.getEndTime () )) {
            LocalDate startDate = DateUtil.parseDate ( "1901-01-01" );
            LocalDate endDate = DateUtil.parseDate ( to.getEndTime () );
            dates = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate ( "1901-01-01" );
            LocalDate endDate = LocalDate.now ();
            dates = new LocalDate[]{startDate, endDate};
        }
        List<FirstSubjectBO> list = figureCollect ( dates, firstSubject );
        List<SubjectIntroExport> subjectIntroExports = new ArrayList<> ();
        Map<Integer, List<Integer>> integerListMap = new HashMap<> ();
        List<Map<String, List<SubjectIntroExport>>> maps = new ArrayList<> ();
        Map<String, List<SubjectIntroExport>> listMap = new HashMap<> ();
        if (list != null && list.size () > 0) {
            for (int i = 0; i <= list.size (); i++) {
                List<Integer> departmentListNumber = new ArrayList<> ();
                List<Integer> areaListNumber = new ArrayList<> ();
                List<Integer> subjectListNumber = new ArrayList<> ();
                List<Integer> allListNumber = new ArrayList<> ();
                Integer subjectNumber = 0;
                Integer firstSubjectNumber = 0;
                Integer allNumber = 0;
                SubjectIntroExport subjectIntroExport = new SubjectIntroExport ();
                subjectIntroExport.setFirstSubject ( list.get ( i ).getFirstSubject () );

                List<AreaSubjectBO> areaSubjectBOS = list.get ( i ).getAreaSubjectList ();
                for (int a = 0; a < areaSubjectBOS.size (); a++) {
                    Integer areaNumber = 0;
                    List<DepartmentSubjectBO> departmentSubjectBOS = areaSubjectBOS.get ( a ).getDepartmentSubjectList ();
                    subjectIntroExport.setArea ( areaSubjectBOS.get ( a ).getArea () );
                    for (int b = 0; b < departmentSubjectBOS.size (); b++) {
                        Integer deartpmetnNumber = 0;
                        subjectIntroExport.setDepartment ( departmentSubjectBOS.get ( b ).getDepartment () );
                        List<SubjectCollectsBO> subjectCollectsBOS = departmentSubjectBOS.get ( b ).getSubjectCollectList ();
                        for (SubjectCollectsBO subjectCollectsBO : subjectCollectsBOS) {
                            subjectIntroExport.setProject ( subjectCollectsBO.getProject () );
                            subjectIntroExport.setBeginBorrowMoney ( subjectCollectsBO.getBeginBorrowMoney () );
                            subjectIntroExport.setBeginLoanMoney ( subjectCollectsBO.getBeginLoanMoney () );
                            subjectIntroExport.setCurrentBorrowMoney ( subjectCollectsBO.getCurrentBorrowMoney () );
                            subjectIntroExport.setCurrentLoanMoney ( subjectCollectsBO.getCurrentLoanMoney () );
                            subjectIntroExport.setEndBorrowMoney ( subjectCollectsBO.getEndBorrowMoney () );
                            subjectIntroExport.setEndLoanMoney ( subjectCollectsBO.getEndLoanMoney () );
                            subjectIntroExports.add ( subjectIntroExport );
                        }
                        firstSubjectNumber = subjectCollectsBOS.size ();
                        allNumber += firstSubjectNumber;
                        deartpmetnNumber += firstSubjectNumber;
                        departmentListNumber.add ( deartpmetnNumber );
                        areaNumber += deartpmetnNumber;
                    }
                    areaListNumber.add ( areaNumber );
                }
                subjectNumber = allNumber;
                subjectListNumber.add ( subjectNumber );
                allListNumber.addAll ( departmentListNumber );
                allListNumber.addAll ( subjectListNumber );
                allListNumber.addAll ( areaListNumber );
                integerListMap.put ( i, allListNumber );
            }

        }

        //导出
        Excel excel = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( subjectIntroExports, excel );
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream ( bytes );
            wb = new XSSFWorkbook ( is );
            XSSFSheet sheet;
            sheet = wb.getSheetAt ( 0 );
            //主表行数
            int rowSize = list.size ();
            List<Field> fields = ClazzUtils.getFields ( SubjectIntroExport.class ); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders ( fields, null );

            int index = 0;
            int lastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                int x = 0;
                //List<int> maxList所有子表的长度
                if (null != integerListMap.get ( j ) && integerListMap.get ( j ).size () > 0) {
//                    PositionWorkDetails mainPwd = list.get(j);
                    int mergeRowCount = integerListMap.get ( j ).get ( j );
                    //5
                    if (mergeRowCount != 1) {
                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        //合并主表共33列
                        for (int i = 1; i < 3; i++) {
                            //1,5
                            sheet.addMergedRegion ( new CellRangeAddress ( firstRow, lastRow, i, i ) );
                            x = 3;
                        }
                        //合并模快
                        Map<Integer, List<Integer>> mergeMLen = integerListMap;
                        //获取规划模块合并长度数据
                        List<Integer> ghMergeLen = mergeMLen.get ( j );
                        if (null != ghMergeLen && ghMergeLen.size () > 0) {
                            int mfirstRow = firstRow;
                            int mMergeRowCount = 0;
                            for (Integer mi : ghMergeLen) {
                                if (mi != 1) {
                                    int mlastRow = (firstRow - 1) + mMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion ( new CellRangeAddress ( mfirstRow, mlastRow, z, z ) );
                                    }
                                    x += 19;
                                    mfirstRow = mlastRow + 1;
                                    mMergeRowCount = mMergeRowCount + mi;
                                }
                            }
                        }

                        lastRow--;
                    }
                }
                lastRow++;
                index = lastRow + 1;
            }


            os = new ByteArrayOutputStream ();
            wb.write ( os );
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return os.toByteArray ();
    }


    private List<FirstSubjectBO> figureCollect(LocalDate[] dates) throws SerException {
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO ();
        voucherGenerateDTO.getConditions ().add ( Restrict.between ( "voucherDate", dates ) );
        voucherGenerateDTO.getSorts ().add ( "createTime=desc" );
        List<VoucherGenerateBO> boList = voucherGenerateAPI.listNoPage ( voucherGenerateDTO );
        List<FirstSubjectBO> firstSubjectBOS = new ArrayList<> ();
        if (boList != null && boList.size () > 0) {
            Set<String> firstSubjects = boList.stream ().map ( p -> p.getFirstSubject () ).collect ( Collectors.toSet () );
            Set<String> voucherDates = boList.stream ().map ( p -> p.getVoucherDate () ).collect ( Collectors.toSet () );
            for (String voucherDate : voucherDates) {
                for (String firstSubject : firstSubjects) {
                    FirstSubjectBO firstSubjectBO = getFirstSubjectBO ( voucherDate, firstSubject );
                    firstSubjectBOS.add ( firstSubjectBO );
                }
                Map<String, List<Integer>> map = new HashMap<> ();
            }
        }


        return firstSubjectBOS;
    }

    private List<FirstSubjectBO> figureCollect(LocalDate[] dates, String firstSubject) throws SerException {
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO ();
        voucherGenerateDTO.getConditions ().add ( Restrict.between ( "voucherDate", dates ) );
        voucherGenerateDTO.getSorts ().add ( "createTime=desc" );
        List<VoucherGenerateBO> boList = voucherGenerateAPI.listNoPage ( voucherGenerateDTO );
        Set<String> voucherDates = boList.stream ().map ( p -> p.getVoucherDate () ).collect ( Collectors.toSet () );
        List<FirstSubjectBO> firstSubjectBOS = new ArrayList<> ();
        if (StringUtils.isNotBlank ( firstSubject )) {
            for (String voucherDate : voucherDates) {
                FirstSubjectBO firstSubjectBO = getFirstSubjectBO ( voucherDate, firstSubject );
                firstSubjectBOS.add ( firstSubjectBO );
            }
        } else {
            firstSubjectBOS = figureCollect ( dates );
        }
        return firstSubjectBOS;
    }

    private FirstSubjectBO getFirstSubjectBO(String time, String firstSubject) throws SerException {
        LocalDate voucherDate = DateUtil.parseDate ( time );
        FirstSubjectBO firstSubjectBO = new FirstSubjectBO ();
        VoucherGenerateDTO voucherGenerateDTO1 = new VoucherGenerateDTO ();
        voucherGenerateDTO1.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
        voucherGenerateDTO1.getConditions ().add ( Restrict.eq ( "voucherDate", voucherDate ) );
        List<VoucherGenerateBO> voucherGenerateBOS = voucherGenerateAPI.listNoPage ( voucherGenerateDTO1 );
        Set<String> areas = voucherGenerateBOS.stream ().map ( p -> p.getArea () ).collect ( Collectors.toSet () );
        List<AreaSubjectBO> areaSubjectBOS = new ArrayList<> ();
        for (String area : areas) {
            AreaSubjectBO areaSubjectBO = new AreaSubjectBO ();
            VoucherGenerateDTO voucherGenerateDTO5 = new VoucherGenerateDTO ();
            voucherGenerateDTO5.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
            voucherGenerateDTO5.getConditions ().add ( Restrict.eq ( "voucherDate", voucherDate ) );
            voucherGenerateDTO5.getConditions ().add ( Restrict.eq ( "area", area ) );
            List<VoucherGenerateBO> voucherGenerateBOS4 = voucherGenerateAPI.listNoPage ( voucherGenerateDTO5 );
            Set<String> departments = voucherGenerateBOS4.stream ().map ( p -> p.getProjectGroup () ).collect ( Collectors.toSet () );
            List<DepartmentSubjectBO> departmentSubjectBOS = new ArrayList<> ();
            for (String department : departments) {
                DepartmentSubjectBO departmentSubjectBO = new DepartmentSubjectBO ();
                VoucherGenerateDTO voucherGenerateDTO2 = new VoucherGenerateDTO ();
                voucherGenerateDTO2.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
                voucherGenerateDTO2.getConditions ().add ( Restrict.eq ( "projectGroup", department ) );
                voucherGenerateDTO2.getConditions ().add ( Restrict.eq ( "voucherDate", voucherDate ) );
                List<VoucherGenerateBO> voucherGenerateBOS1 = voucherGenerateAPI.listVoucherGenerate ( voucherGenerateDTO1 );
                Set<String> projects = voucherGenerateBOS1.stream ().map ( p -> p.getProjectName () ).collect ( Collectors.toSet () );
                List<SubjectCollectsBO> subjectCollectsBOS = new ArrayList<> ();
                for (String project : projects) {
                    //期初借方余额
                    Double beginBorrowMoney = 0.0;
                    //期初贷方余额
                    Double beginLoanMoney = 0.0;
                    //本期借方余额
                    Double currentBorrowMoney = 0.0;
                    //本期贷方余额
                    Double currentLoanMoney = 0.0;
                    //期末借方发生额
                    Double endBorrowMoney = 0.0;
                    //期末贷方发生额
                    Double endLoanMoney = 0.0;
                    //本年借方统计
                    Double currentYearBorrowMoney = 0.0;
                    //本年贷方统计
                    Double currentYearLoanMoney = 0.0;

                    VoucherGenerateDTO voucherGenerateDTO3 = new VoucherGenerateDTO ();
                    voucherGenerateDTO3.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
                    voucherGenerateDTO3.getConditions ().add ( Restrict.eq ( "projectGroup", department ) );
                    voucherGenerateDTO3.getConditions ().add ( Restrict.eq ( "projectName", project ) );
                    voucherGenerateDTO3.getConditions ().add ( Restrict.eq ( "voucherDate", voucherDate ) );
                    List<VoucherGenerateBO> voucherGenerateBOS2 = voucherGenerateAPI.listNoPage ( voucherGenerateDTO3 );

                    Integer year = Integer.valueOf ( time.substring ( 0, 4 ) );
                    Integer month = Integer.valueOf ( time.substring ( 5, 7 ) );
                    LocalDate startDate = DateUtil.getStartDayOfMonth ( year, month );
                    LocalDate endDate = startDate.minusDays ( 1 );
                    VoucherGenerateDTO voucherGenerateDTO4 = new VoucherGenerateDTO ();
                    voucherGenerateDTO4.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
                    voucherGenerateDTO4.getConditions ().add ( Restrict.eq ( "projectGroup", department ) );
                    voucherGenerateDTO4.getConditions ().add ( Restrict.eq ( "projectName", project ) );
                    voucherGenerateDTO4.getConditions ().add ( Restrict.eq ( "voucherDate", endDate ) );
                    List<VoucherGenerateBO> voucherGenerateBOS3 = voucherGenerateAPI.listNoPage ( voucherGenerateDTO4 );

                    LocalDate startTime = DateUtil.parseDate ( year + "-01-01" );
                    LocalDate endTime = DateUtil.parseDate ( year + "-12-31" );
                    LocalDate[] times = new LocalDate[]{startTime, endTime};
                    VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO ();
                    voucherGenerateDTO.getConditions ().add ( Restrict.eq ( "firstSubject", firstSubject ) );
                    voucherGenerateDTO.getConditions ().add ( Restrict.eq ( "projectGroup", department ) );
                    voucherGenerateDTO.getConditions ().add ( Restrict.eq ( "projectName", project ) );
                    voucherGenerateDTO.getConditions ().add ( Restrict.between ( "voucherDate", times ) );
                    List<VoucherGenerateBO> voucherGenerateBOS5 = voucherGenerateAPI.listNoPage ( voucherGenerateDTO );

                    if (voucherGenerateBOS3 != null && voucherGenerateBOS3.size () > 0) {
                        beginBorrowMoney = voucherGenerateBOS3.stream ().filter ( p -> p.getBorrowMoney () != null ).mapToDouble ( p -> p.getBorrowMoney () ).sum ();
                        beginLoanMoney = voucherGenerateBOS3.stream ().filter ( p -> p.getLoanMoney () != null ).mapToDouble ( p -> p.getLoanMoney () ).sum ();
                    }

                    if (voucherGenerateBOS2 != null && voucherGenerateBOS2.size () > 0) {
                        currentBorrowMoney = voucherGenerateBOS2.stream ().filter ( p -> p.getBorrowMoney () != null ).mapToDouble ( p -> p.getBorrowMoney () ).sum ();
                        currentLoanMoney = voucherGenerateBOS2.stream ().filter ( p -> p.getLoanMoney () != null ).mapToDouble ( p -> p.getLoanMoney () ).sum ();
                    }

                    if (voucherGenerateBOS5 != null && voucherGenerateBOS5.size () > 0) {
                        currentYearBorrowMoney = voucherGenerateBOS5.stream ().filter ( p -> p.getBorrowMoney () != null ).mapToDouble ( p -> p.getBorrowMoney () ).sum ();
                        currentYearLoanMoney = voucherGenerateBOS5.stream ().filter ( p -> p.getLoanMoney () != null ).mapToDouble ( p -> p.getLoanMoney () ).sum ();
                    }
                    endBorrowMoney = beginBorrowMoney - currentBorrowMoney;
                    endLoanMoney = beginLoanMoney - currentLoanMoney;

                    SubjectCollectsBO subjectCollectsBO = new SubjectCollectsBO ();
                    subjectCollectsBO.setProject ( project );
                    subjectCollectsBO.setBeginBorrowMoney ( beginBorrowMoney );
                    subjectCollectsBO.setBeginLoanMoney ( beginLoanMoney );
                    subjectCollectsBO.setCurrentBorrowMoney ( currentBorrowMoney );
                    subjectCollectsBO.setCurrentLoanMoney ( currentLoanMoney );
                    subjectCollectsBO.setEndBorrowMoney ( endBorrowMoney );
                    subjectCollectsBO.setEndLoanMoney ( endLoanMoney );
                    subjectCollectsBO.setCurrentYearBorrowMoney ( currentYearBorrowMoney );
                    subjectCollectsBO.setCurrentLoanMoney ( currentLoanMoney );
                    subjectCollectsBOS.add ( subjectCollectsBO );
                }
                departmentSubjectBO.setDepartment ( department );
                departmentSubjectBO.setSubjectCollectList ( subjectCollectsBOS );
                departmentSubjectBOS.add ( departmentSubjectBO );
            }
            areaSubjectBO.setArea ( area );
            areaSubjectBO.setDepartmentSubjectList ( departmentSubjectBOS );
            areaSubjectBOS.add ( areaSubjectBO );
        }
        firstSubjectBO.setFirstSubject ( firstSubject );
        firstSubjectBO.setAreaSubjectList ( areaSubjectBOS );
        return firstSubjectBO;
    }
}