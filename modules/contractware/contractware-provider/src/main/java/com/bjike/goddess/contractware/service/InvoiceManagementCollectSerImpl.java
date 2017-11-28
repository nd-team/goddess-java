package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contractware.bo.*;
import com.bjike.goddess.contractware.dto.InvoiceManagementCollectDTO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.entity.InvoiceManagement;
import com.bjike.goddess.contractware.entity.InvoiceManagementCollect;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.enums.InvoiceType;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.organize.bo.AxisLabelBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.type.SetType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 发票管理汇总业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-11-02 09:12 ]
 * @Description: [ 发票管理汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class InvoiceManagementCollectSerImpl extends ServiceImpl<InvoiceManagementCollect, InvoiceManagementCollectDTO> implements InvoiceManagementCollectSer {
    @Autowired
    private InvoiceManagementSer invoiceManagementSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
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
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        Boolean flagSee = guideSeeIdentity ();
        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagAdd = guideAddIdentity ();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus ();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity ();
                break;
            case ADD:
                flag = guideAddIdentity ();
                break;
            case EDIT:
                flag = guideAddIdentity ();
                break;
            case AUDIT:
                flag = guideAddIdentity ();
                break;
            case DELETE:
                flag = guideAddIdentity ();
                break;
            case CONGEL:
                flag = guideAddIdentity ();
                break;
            case THAW:
                flag = guideAddIdentity ();
                break;
            case COLLECT:
                flag = guideAddIdentity ();
                break;
            case IMPORT:
                flag = guideAddIdentity ();
                break;
            case EXPORT:
                flag = guideAddIdentity ();
                break;
            case UPLOAD:
                flag = guideAddIdentity ();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity ();
                break;
            case SEE:
                flag = guideSeeIdentity ();
                break;
            case SEEFILE:
                flag = guideSeeIdentity ();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken ( userToken );
        return flag;
    }

    @Override
    public InvoiceCollectBO monthCollect(InvoiceManagementCollectDTO dto) throws SerException {
        InvoiceManagementDTO invoiceManagementDTO = new InvoiceManagementDTO ();
        InvoiceCollectBO invoiceCollectBO = new InvoiceCollectBO ();
        if (dto.getYear () != null && dto.getMonth () != null) {
            LocalDate startDate = DateUtil.getStartDayOfMonth ( dto.getYear (), dto.getMonth () );
            LocalDate endDate = DateUtil.getEndDaYOfMonth ( dto.getYear (), dto.getMonth () );
            LocalDate[] dates = new LocalDate[]{startDate, endDate};
            invoiceManagementDTO.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        } else {
            LocalDate startDate = DateUtil.getStartMonth ();
            LocalDate endDate = DateUtil.getEndMonth ();
            LocalDate[] dates = new LocalDate[]{startDate, endDate};
            invoiceManagementDTO.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        }
        if (dto.getInvoiceType () != null) {
            invoiceManagementDTO.getConditions ().add ( Restrict.eq ( "invoiceType", dto.getInvoiceType () ) );
        }
        if (StringUtils.isNotBlank ( dto.getMakeInvoiceCompany () )) {
            invoiceManagementDTO.getConditions ().add ( Restrict.eq ( "makeInvoiceCompany", dto.getMakeInvoiceCompany () ) );
        }
        Double invoiceMoneyTotal = 0.0;
        List<InvoiceManagement> invoiceManagements = invoiceManagementSer.findByCis ( invoiceManagementDTO );
        if (invoiceManagements != null) {
            invoiceMoneyTotal = invoiceManagements.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();
        }
        List<InvoiceManagementCollectBO> invoiceManagementCollectBOS = BeanTransform.copyProperties ( invoiceManagements, InvoiceManagementCollectBO.class );
        invoiceCollectBO.setInvoiceMoneyTotal ( invoiceMoneyTotal );
        invoiceCollectBO.setInvoiceManagementCollectList ( invoiceManagementCollectBOS );
        return invoiceCollectBO;
    }

    @Override
    public InvoiceYearCollectBO yearCollect(InvoiceManagementCollectDTO dto) throws SerException {
        InvoiceManagementDTO invoiceManagementDTO = new InvoiceManagementDTO ();
        InvoiceYearCollectBO invoiceYearCollectBO = new InvoiceYearCollectBO ();
        if (dto.getYear () != null) {
            if (StringUtils.isNotBlank ( dto.getMakeInvoiceCompany () )) {
                invoiceManagementDTO.getConditions ().add ( Restrict.eq ( "makeInvoiceCompany", dto.getMakeInvoiceCompany () ) );
            }
            invoiceYearCollectBO = collectYear ( dto.getYear (), dto );
        } else {
            if (StringUtils.isNotBlank ( dto.getMakeInvoiceCompany () )) {
                invoiceManagementDTO.getConditions ().add ( Restrict.eq ( "makeInvoiceCompany", dto.getMakeInvoiceCompany () ) );
            }
            invoiceYearCollectBO = collectYear ( LocalDate.now ().getYear (), dto );
        }
        return invoiceYearCollectBO;
    }

    public static void main(String[] args) {
        System.out.println ( DateUtil.getStartYear () );
        System.out.println ( DateUtil.getEndYear () );
    }

    private InvoiceYearCollectBO collectYear(Integer year, InvoiceManagementCollectDTO dto1) throws SerException {
        InvoiceYearCollectBO invoiceYearCollectBO = new InvoiceYearCollectBO ();
        for (int i = 1; i <= 12; i++) {
            InvoiceManagementDTO dto = new InvoiceManagementDTO ();
            if (StringUtils.isNotBlank ( dto1.getMakeInvoiceCompany () )) {
                dto.getConditions ().add ( Restrict.eq ( "makeInvoiceCompany", dto1.getMakeInvoiceCompany () ) );
            }
            Integer month = i;
            switch (month) {
                case 1:
                    Double januaryInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setJanuaryInvoiceMoneyTotal ( januaryInvoiceMoneyTotal );
                    break;
                case 2:
                    Double februaryInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setFebruaryInvoiceMoneyTotal ( februaryInvoiceMoneyTotal );
                    break;
                case 3:
                    Double marchInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setMarchInvoiceMoneyTotal ( marchInvoiceMoneyTotal );
                    break;
                case 4:
                    Double aprilInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setAprilInvoiceMoneyTotal ( aprilInvoiceMoneyTotal );
                    break;
                case 5:
                    Double mayInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setMayInvoiceMoneyTotal ( mayInvoiceMoneyTotal );
                    break;
                case 6:
                    Double juneInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setJuneInvoiceMoneyTotal ( juneInvoiceMoneyTotal );
                    break;
                case 7:
                    Double julyInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setJulyInvoiceMoneyTotal ( julyInvoiceMoneyTotal );
                    break;
                case 8:
                    Double augustInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setAugustInvoiceMoneyTotal ( augustInvoiceMoneyTotal );
                    break;
                case 9:
                    Double septemberInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setSeptemberInvoiceMoneyTotal ( septemberInvoiceMoneyTotal );
                    break;
                case 10:
                    Double octoberInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setOctoberInvoiceMoneyTotal ( octoberInvoiceMoneyTotal );
                    break;
                case 11:
                    Double novemberInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setNovemberInvoiceMoneyTotal ( novemberInvoiceMoneyTotal );
                    break;
                case 12:
                    Double decemberInvoiceMoneyTotal = collectByMonth ( year, month, dto );
                    invoiceYearCollectBO.setDecemberInvoiceMoneyTotal ( decemberInvoiceMoneyTotal );
                    break;
            }
        }
        return invoiceYearCollectBO;
    }

    private Double collectByMonth(Integer year, Integer month, InvoiceManagementDTO dto) throws SerException {
        LocalDate startDate = DateUtil.getStartDayOfMonth ( year, month );
        LocalDate endDate = DateUtil.getEndDaYOfMonth ( year, month );
        LocalDate[] dates = new LocalDate[]{startDate, endDate};
        dto.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        List<InvoiceManagement> invoiceManagements = invoiceManagementSer.findByCis ( dto );
        Double moneyTotal = 0.0;
        if (invoiceManagements != null && invoiceManagements.size () > 0) {
            moneyTotal = invoiceManagements.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();
        }
        return moneyTotal;
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        if (year != null) {

        } else {
            year = LocalDate.now ().getYear ();
        }
        String text_1 = "发票管理图形化";
        return imageCollect ( year, text_1 );
    }

    private OptionBO imageCollect(Integer year, String text_1) throws SerException {
        //标题
        TitleBO titleBO = new TitleBO ();
        titleBO.setText ( text_1 );

        //横坐标描述
        LegendBO legendBO = new LegendBO ();
        List<String> text_list2 = new ArrayList<> ();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO ();
        yAxisBO.setType ( "value" );


        text_list2.add ( "增值税专用发票" );
        text_list2.add ( "增值税普通发票" );
        text_list2.add ( "增值税电子发票" );
        text_list2.add ( "普通发票" );
        String[] text_2 = new String[text_list2.size ()];
        text_2 = text_list2.toArray ( text_2 );

        legendBO.setData ( text_2 );

        //横坐标
        XAxisBO xAxisBO = new XAxisBO ();
        String[] text_3 = new String[]{"１月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        xAxisBO.setData ( text_3 );
        AxisLabelBO axisLabelBO = new AxisLabelBO ();
        axisLabelBO.setInterval ( 0 );
        List<SeriesBO> seriesBOList = new ArrayList<> ();
        ImageInvoiceCollectBO imageInvoiceCollectBO = new ImageInvoiceCollectBO ();
        List<ImageInvoiceCollectBO> imageInvoiceCollectBOS = new ArrayList<> ();
        for (int i = 1; i <= 12; i++) {
            Integer month = i;
            switch (month) {
                case 1:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 2:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 3:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 4:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 5:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 6:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 7:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 8:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 9:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 10:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 11:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
                case 12:
                    imageInvoiceCollectBO = collectImageInvoice ( year, month );
                    break;
            }
            imageInvoiceCollectBOS.add ( imageInvoiceCollectBO );
        }
        //增值税专用发票每个月的发票金额
        List<Double> number = new ArrayList<> ();
        //增值税普通发票金额每个月的发票金额
        List<Double> number1 = new ArrayList<> ();
        //增值税电子发票每个月的发票金额
        List<Double> number2 = new ArrayList<> ();
        //普通发票每个月的发票金额
        List<Double> number3 = new ArrayList<> ();
        if (imageInvoiceCollectBOS != null && imageInvoiceCollectBOS.size () > 0) {
            for (ImageInvoiceCollectBO imageInvoiceCollectBO1 : imageInvoiceCollectBOS) {
                number.add ( imageInvoiceCollectBO1.getAppreciationDedicated () );
                number1.add ( imageInvoiceCollectBO1.getAppreciationCommon () );
                number2.add ( imageInvoiceCollectBO1.getAppreciationElectronic () );
                number3.add ( imageInvoiceCollectBO1.getCommon () );
            }
        }
        Double[] dedicated = new Double[number.size ()];
        dedicated = number.toArray ( dedicated );
        Double[] common = new Double[number1.size ()];
        common = number1.toArray ( common );
        Double[] electronic = new Double[number2.size ()];
        electronic = number2.toArray ( electronic );
        Double[] commonNumber = new Double[number3.size ()];
        commonNumber = number3.toArray ( commonNumber );

        NormalBO normalBO = new NormalBO ();
        normalBO.setPosition ( "insideRight" );
        normalBO.setShow ( true );
        LabelBO labelBO = new LabelBO ();
        labelBO.setNormal ( normalBO );

        SeriesBO seriesBO = new SeriesBO ();
        seriesBO.setName ( "增值税专用发票" );
        seriesBO.setLabel ( labelBO );
        seriesBO.setType ( "bar" );
        seriesBO.setData ( dedicated );

        SeriesBO seriesBO1 = new SeriesBO ();
        seriesBO1.setName ( "增值税普通发票" );
        seriesBO1.setLabel ( labelBO );
        seriesBO1.setType ( "bar" );
        seriesBO1.setData ( common );

        SeriesBO seriesBO2 = new SeriesBO ();
        seriesBO2.setName ( "增值税电子发票" );
        seriesBO2.setLabel ( labelBO );
        seriesBO2.setType ( "bar" );
        seriesBO2.setData ( electronic );

        SeriesBO seriesBO3 = new SeriesBO ();
        seriesBO3.setName ( "普通发票" );
        seriesBO3.setLabel ( labelBO );
        seriesBO3.setType ( "bar" );
        seriesBO3.setData ( commonNumber );

        seriesBOList.add ( seriesBO );
        seriesBOList.add ( seriesBO1 );
        seriesBOList.add ( seriesBO2 );
        seriesBOList.add ( seriesBO3 );

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size ()];
        text_4 = seriesBOList.toArray ( text_4 );

        OptionBO optionBO = new OptionBO ();
        optionBO.setTitle ( titleBO );
        optionBO.setLegend ( legendBO );
        optionBO.setLegend ( legendBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setyAxis ( yAxisBO );

        optionBO.setSeries ( text_4 );
        return optionBO;
    }

    private ImageInvoiceCollectBO collectImageInvoice(Integer year, Integer month) throws SerException {
        /**
         * 增值税专用发票金额
         */
        Double appreciationDedicated = 0.0;

        /**
         * 增值税普通发票金额
         */
        Double appreciationCommon = 0.0;

        /**
         * 增值税电子发票
         */
        Double appreciationElectronic = 0.0;

        /**
         * 普通发票
         */
        Double common = 0.0;

        InvoiceManagementDTO dto = new InvoiceManagementDTO ();
        ImageInvoiceCollectBO imageInvoiceCollectBO = new ImageInvoiceCollectBO ();
        LocalDate startDate = DateUtil.getStartDayOfMonth ( year, month );
        LocalDate endDate = DateUtil.getEndDaYOfMonth ( year, month );
        LocalDate[] dates = new LocalDate[]{startDate, endDate};
        dto.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        dto.getConditions ().add ( Restrict.eq ( "invoiceType", InvoiceType.APPRECIATIONDEDICATED ) );
        List<InvoiceManagement> invoiceManagements = invoiceManagementSer.findByCis ( dto );
        appreciationDedicated = invoiceManagements.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();


        InvoiceManagementDTO dto2 = new InvoiceManagementDTO ();
        dto2.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        dto2.getConditions ().add ( Restrict.eq ( "invoiceType", InvoiceType.APPRECIATIONCONMON ) );
        List<InvoiceManagement> invoiceManagements2 = invoiceManagementSer.findByCis ( dto );
        appreciationCommon = invoiceManagements2.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();

        InvoiceManagementDTO dto3 = new InvoiceManagementDTO ();
        dto3.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        dto3.getConditions ().add ( Restrict.eq ( "invoiceType", InvoiceType.APPRECIATIONELECTRONIC ) );
        List<InvoiceManagement> invoiceManagements3 = invoiceManagementSer.findByCis ( dto );
        appreciationElectronic = invoiceManagements3.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();

        InvoiceManagementDTO dto4 = new InvoiceManagementDTO ();
        dto4.getConditions ().add ( Restrict.between ( "makeInvoiceDate", dates ) );
        dto4.getConditions ().add ( Restrict.eq ( "invoiceType", InvoiceType.COMMON ) );
        List<InvoiceManagement> invoiceManagements4 = invoiceManagementSer.findByCis ( dto );
        common = invoiceManagements4.stream ().filter ( p -> p.getInvoiceMoney () != null ).mapToDouble ( p -> p.getInvoiceMoney () ).sum ();

        imageInvoiceCollectBO.setAppreciationCommon ( appreciationCommon );
        imageInvoiceCollectBO.setAppreciationDedicated ( appreciationDedicated );
        imageInvoiceCollectBO.setAppreciationElectronic ( appreciationElectronic );
        imageInvoiceCollectBO.setCommon ( common );

        return imageInvoiceCollectBO;

    }
}