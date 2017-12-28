package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.socialinsurance.bo.*;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceVoucherDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import com.bjike.goddess.socialinsurance.enums.GuideAddrStatus;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 18:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@CacheConfig(cacheNames = "socialinsuranceSerCache")
@Service
public class SocialInsuranceCollectSerImpl extends ServiceImpl<SocialInsurance, SocialInsuranceCollectDTO> implements SocialInsuranceCollectSer{

    @Autowired
    CusPermissionSer cusPermissionSer;
    @Autowired
    UserAPI userAPI;

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
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("2", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("2", null);
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
        if (flagSee || flagAdd) {
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
            case MANAGER:
                flag = guideManageIdentity();
                break;
            case PLAN:
                flag = guidePlanIdentity();
                break;
            case BUDGET:
                flag = guideBudgetIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public List<SocialInsuranceCollectBO> personalCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String name = null;
        if (dto != null && StringUtils.isNotBlank(dto.getName())){
            name = dto.getName();
        }
        String[] fields = new String[]{"name", "companyTotal", "personalTotal", "amountDue"};
        String sql = "select name, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "'";
        if (name != null) {
            sql += " and name like '%"+ name +"%'";
        }
        sql += " group by name";
        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate().substring(0, 7));
            bo.setEndDate(dto.getEndDate().substring(0, 7));
        }
        return bos;
    }

    @Override
    public List<SocialInsuranceCollectBO> departmentCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String department = null;
        if (dto != null && StringUtils.isNotBlank(dto.getDepartment())){
            department = dto.getDepartment();
        }
        String[] fields = new String[]{"department", "companyTotal", "personalTotal", "amountDue"};
        String sql = "select department, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "'";
        if (department != null) {
            sql += " and department = '"+ department +"'";
        }
        sql += " group by department";


        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate().substring(0, 7));
            bo.setEndDate(dto.getEndDate().substring(0, 7));
        }
        return bos;
    }

    @Override
    public List<SocialInsuranceCollectBO> voucherCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String department = null;
        if (dto != null && StringUtils.isNotBlank(dto.getDepartment())){
            department = dto.getDepartment();
        }
        String[] fields = new String[]{"department", "companyTotal", "personalTotal", "amountDue"};
        String sql = "select department, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and pay = 0";
        if (department != null) {
            sql += " and department = '"+ department +"'";
        }
        sql += " group by department";


        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate().substring(0, 7));
            bo.setEndDate(dto.getEndDate().substring(0, 7));
        }
        return bos;
    }

    @Override
    public List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String area = null;
        if (dto != null && StringUtils.isNotBlank(dto.getArea())){
            area = dto.getArea();
        }
        String[] fields = new String[]{"area", "companyTotal", "personalTotal", "amountDue"};
        String sql = "select area, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "'";
        if (area != null) {
            sql += " and area = '"+ area +"'";
        }
        sql += " group by area";
        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate().substring(0, 7));
            bo.setEndDate(dto.getEndDate().substring(0, 7));
        }
        return bos;
    }

    @Override
    public SICollectEchartBO personalCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getName() +"员工社保缴费情况汇总";
        if (StringUtils.isBlank(dto.getName())){
            bos = personalCollect(dto);
            return getSICollectEchart(bos, title, "personal");
        } else {
            String[] fields = new String[]{"name", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select name, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and name = '"+ dto.getName() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectEchart2(bos, title, "personal");
        }

    }

    @Override
    public SICollectEchartBO departmentCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getDepartment() +"部门社保缴费情况汇总";
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        if (StringUtils.isBlank(dto.getDepartment())){
            bos = departmentCollect(dto);
            return getSICollectEchart(bos, title, "department");
        } else {
            String[] fields = new String[]{"department", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select department, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and department = '"+ dto.getDepartment() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectEchart2(bos, title, "department");
        }

    }

    @Override
    public SICollectEchartBO areaCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getArea() +"地区社保缴费情况汇总";
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        if (StringUtils.isBlank(dto.getArea())){
            bos = areaCollect(dto);
            return getSICollectEchart(bos, title, "area");
        } else {
            String[] fields = new String[]{"area", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select area, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and area ='"+ dto.getArea() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectEchart2(bos, title, "area");
        }

    }

    @Override
    public SICollectEchartBO personalCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getName() +"员工社保缴费情况汇总";
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())){
            bos = personalCollect(dto);
            return getSICollectPieEchart(bos, title, "personal");
        } else {
            String[] fields = new String[]{"name", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select name, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and name = '"+ dto.getName() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectPieEchart2(bos, title, "personal");
        }
    }

    @Override
    public SICollectEchartBO departmentCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getDepartment() +"部门社保缴费情况汇总";
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        if (StringUtils.isBlank(dto.getDepartment())){
            bos = departmentCollect(dto);
            return getSICollectPieEchart(bos, title, "department");
        } else {
            String[] fields = new String[]{"department", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select department, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and department = '"+ dto.getDepartment() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectPieEchart2(bos, title, "department");
        }
    }

    @Override
    public SICollectEchartBO areaCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        List<SocialInsuranceCollectBO> bos = new ArrayList<>();
        String title = dto.getStartDate().substring(0, 4) + "年" + dto.getStartDate().substring(5, 7) + "月-" + dto.getEndDate().substring(0, 4) + "年" + dto.getEndDate().substring(5, 7) + "月公司"+ dto.getArea() +"地区社保缴费情况汇总";
        if (StringUtils.isBlank(dto.getArea())){
            bos = areaCollect(dto);
            return getSICollectPieEchart(bos, title, "area");
        } else {
            String[] fields = new String[]{"area", "taxDate", "companyTotal", "personalTotal", "amountDue"};
            String sql = "select area, taxDate, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ dto.getStartDate() +"' and '" + dto.getEndDate() + "' and area ='"+ dto.getArea() +"' group by taxDate";
            bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);

            return getSICollectPieEchart2(bos, title, "area");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateSocialInsurancePayStatus(String department, String startDate, String endDate) throws SerException {
        String sql = "update socialinsurance_socialinsurance set pay = 1 where DATE_FORMAT(taxDate, '%Y-%m-%d') between '"+ startDate +"' and '" + endDate + "' and department = '"+ department +"'";
        super.executeSql(sql);
    }


    /**
     * 汇总柱状图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private SICollectEchartBO getSICollectEchart(List<SocialInsuranceCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[2];
        String[] data1 = new String[list.size()];
        String[] data2 = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            switch (type) {
                case "personal":
                    xText[i] = list.get(i).getName();
                    break;
                case "department":
                    xText[i] = list.get(i).getDepartment();
                    break;
                case "area":
                    xText[i] = list.get(i).getArea();
                    break;
            }


            String companyTotal = list.get(i).getCompanyTotal() == null ? "" : String.valueOf(list.get(i).getCompanyTotal());
            String personalTotal = list.get(i).getPersonalTotal() == null ? "" : String.valueOf(list.get(i).getPersonalTotal());

            data1[i] = companyTotal;
            data2[i] = personalTotal;


        }
//        String[] data = new String[2];

        SeriesBO seriesBO1 = new SeriesBO("单位", "bar", data1, "0");
        SeriesBO seriesBO2 = new SeriesBO("个人", "bar", data2, "0");

        series[0] = seriesBO1;
        series[1] = seriesBO2;

        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(new String[]{"单位", "个人"}, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(xText);
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        SICollectEchartBO bcCollectEchartBO = new SICollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }

    /**
     * 汇总柱状图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private SICollectEchartBO getSICollectEchart2(List<SocialInsuranceCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[2];
        String[] data1 = new String[list.size()];
        String[] data2 = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            xText[i] = list.get(i).getTaxDate().substring(0, 4) + "年" + list.get(i).getTaxDate().substring(4, 6) + "月";


            String companyTotal = list.get(i).getCompanyTotal() == null ? "" : String.valueOf(list.get(i).getCompanyTotal());
            String personalTotal = list.get(i).getPersonalTotal() == null ? "" : String.valueOf(list.get(i).getPersonalTotal());

            data1[i] = companyTotal;
            data2[i] = personalTotal;


        }
//        String[] data = new String[2];

        SeriesBO seriesBO1 = new SeriesBO("单位", "bar", data1, "0");
        SeriesBO seriesBO2 = new SeriesBO("个人", "bar", data2, "0");

        series[0] = seriesBO1;
        series[1] = seriesBO2;

        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO(new String[]{"单位", "个人"}, "vertical", "left");
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(xText);
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        SICollectEchartBO bcCollectEchartBO = new SICollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }

    /**
     * 汇总饼型图图表
     *
     * @param
     * @return class
     * @version v1
     */
    private SICollectEchartBO getSICollectPieEchart(List<SocialInsuranceCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[1];
        List<Map<String, Object>> data1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            switch (type) {
                case "personal":
                    xText[i] = list.get(i).getName();
                    break;
                case "department":
                    xText[i] = list.get(i).getDepartment();
                    break;
                case "area":
                    xText[i] = list.get(i).getArea();
                    break;
            }


            String amountDue = list.get(i).getAmountDue() == null ? "" : String.valueOf(list.get(i).getAmountDue());


            Map map = new HashMap<>();
            map.put("value", amountDue);
            map.put("name", xText[i]);
            data1.add(map);
        }
        SeriesBO seriesBO1 = new SeriesBO(null, "pie", data1.toArray(), null);

        series[0] = seriesBO1;

        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO("item", "{a} <br/>{b} : {c} ({d}%)");

        //横坐标描述
        LegendBO legendBO = new LegendBO(xText, "vertical", "left");


        SICollectEchartBO bcCollectEchartBO = new SICollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }


    /**
     * 汇总饼型图图表2
     *
     * @param
     * @return class
     * @version v1
     */
    private SICollectEchartBO getSICollectPieEchart2(List<SocialInsuranceCollectBO> list, String titleText, String type) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[1];
        List<Map<String, Object>> data1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            xText[i] = list.get(i).getTaxDate().substring(0, 4) + "年" + list.get(i).getTaxDate().substring(4, 6) + "月";

            String amountDue = list.get(i).getAmountDue() == null ? "" : String.valueOf(list.get(i).getAmountDue());


            Map map = new HashMap<>();
            map.put("value", amountDue);
            map.put("name", xText[i]);
            data1.add(map);
        }
        SeriesBO seriesBO1 = new SeriesBO(null, "pie", data1.toArray(), null);

        series[0] = seriesBO1;

        //标题
        TitleBO titleBO = new TitleBO(titleText, "center");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO("item", "{a} <br/>{b} : {c} ({d}%)");

        //横坐标描述
        LegendBO legendBO = new LegendBO(xText, "vertical", "left");


        SICollectEchartBO bcCollectEchartBO = new SICollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltip(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }
}
