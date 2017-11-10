package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.CollectDetailBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import com.bjike.goddess.housepay.enums.GuideAddrStatus;
import com.bjike.goddess.housepay.to.CollectAreaTO;
import com.bjike.goddess.housepay.to.CollectProjectTO;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 已付款记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "housepaySerCache")
@Service
public class PayRecordSerImpl extends ServiceImpl<PayRecord, PayRecordDTO> implements PayRecordSer {

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<AreaCollectBO> collectArea(CollectAreaTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        List<AreaCollectBO> list = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)
                && null != to.getAreas() && to.getAreas().length <= 0) {
            list.add(new AreaCollectBO());
            return list;
        }
        String[] fields = new String[]{"area", "rent", "water", "energy", "fee", "otherFee", "total"};
        StringBuilder sb = new StringBuilder();

        String condition = "'".concat(String.join("','", to.getAreas())).concat("'");

        //表头为:地区/时间/房租/水费/电费/管理费/其他费用/合计
        sb.append(" SELECT a.area,sum(a.rent) AS rent,sum(a.water) AS water, ");
        sb.append(" sum(a.energy) AS energy,sum(a.fee) AS fee,sum(a.otherFee) AS otherFee, ");
        sb.append(" sum(total) AS total ");
        sb.append(" FROM (SELECT concat(a.year,'-',if(a.month<=9,concat('0',a.month),a.month))as date,a.* FROM housepay_waitpay as a) as a");
        sb.append(" WHERE a.area IN (" + condition + ") and date BETWEEN '" + startTime + "' and '" + endTime + "' and pay=0 ");
        sb.append(" GROUP BY a.area ORDER BY a.area DESC ");
        String sql = sb.toString();
        list = super.findBySql(sql, AreaCollectBO.class, fields);
        if (list != null && list.size() > 0) {
            list.stream().forEach(str -> {
                str.setYear(startTime + "至" + endTime);
            });
            AreaCollectBO bo = new AreaCollectBO();
            bo.setArea("合计");
            bo.setRent(list.stream().mapToDouble(AreaCollectBO::getRent).sum());
            bo.setWater(list.stream().mapToDouble(AreaCollectBO::getWater).sum());
            bo.setEnergy(list.stream().mapToDouble(AreaCollectBO::getEnergy).sum());
            bo.setFee(list.stream().mapToDouble(AreaCollectBO::getFee).sum());
            bo.setOtherFee(list.stream().mapToDouble(AreaCollectBO::getOtherFee).sum());
            bo.setTotal(list.stream().mapToDouble(AreaCollectBO::getTotal).sum());
            list.add(bo);
        }
        return list;
    }

    @Override
    public List<CollectDetailBO> collectAreaDetail(CollectAreaTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        List<CollectDetailBO> returnList = new ArrayList<>();
        String[] fields = new String[]{"area", "year", "month", "project", "rentAddress", "rent", "water", "energy", "fee", "otherFee",
                "total", "landlord", "contact", "taxesConfirm"};
        StringBuilder sb = new StringBuilder();
        List<CollectDetailBO> list = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)
                && null != to.getAreas() && to.getAreas().length <= 0) {
            list.add(new CollectDetailBO());
            return list;
        }
        String condition = "'".concat(String.join("','", to.getAreas())).concat("'");
        sb.append(" SELECT a.area,a.year,a.month, ");
        sb.append(" a.project,a.rentAddress, sum(a.rent) AS rent,sum(a.water) AS water, ");
        sb.append(" sum(a.energy) AS energy,sum(a.fee) AS fee,sum(a.otherFee) AS otherFee, ");
        sb.append(" sum(total) AS total,a.landlord,a.contact,a.taxesConfirm ");
        sb.append(" FROM (SELECT concat(a.year,'-',if(a.month<=9,concat('0',a.month),a.month))as date,a.* FROM housepay_waitpay as a) as a ");
        sb.append(" WHERE a.area IN (" + condition + ") and date BETWEEN '" + startTime + "' and '" + endTime + "' and pay=0 ");
        sb.append(" GROUP BY a.area,a.year,a.month,a.project,a.rentAddress,a.landlord,a.contact,a.taxesConfirm ");
        sb.append(" ORDER BY a.area DESC ");
        String sql = sb.toString();
        list = super.findBySql(sql, CollectDetailBO.class, fields);
        if (list != null && list.size() > 0) {
            String area = list.get(0).getArea();
            Double rent = 0d;
            Double water = 0d;
            Double energy = 0d;
            Double fee = 0d;
            Double otherFee = 0d;
            Double total = 0d;
            int index = -1;
            CollectDetailBO bo = new CollectDetailBO();
            for (CollectDetailBO boList : list) {
                index = index + 1;
                if (!area.equals(boList.getArea())) {
                    String temp_area = boList.getArea();
                    Double temp_rent = boList.getRent();
                    Double temp_water = boList.getWater();
                    Double temp_energy = boList.getEnergy();
                    Double temp_fee = boList.getFee();
                    Double temp_otherFee = boList.getOtherFee();
                    Double temp_total = boList.getTotal();

                    bo = new CollectDetailBO();
                    bo.setArea(area);
                    bo.setYear("");
                    bo.setMonth("");
                    bo.setProject("");
                    bo.setRentAddress("合计");
                    bo.setRent(rent);
                    bo.setWater(water);
                    bo.setEnergy(energy);
                    bo.setFee(fee);
                    bo.setOtherFee(otherFee);
                    bo.setTotal(total);
                    bo.setLandlord("");
                    bo.setContact("");
                    bo.setTaxesConfirm("");
                    returnList.add(bo);

                    //重置
                    area = temp_area;
                    rent = temp_rent;
                    water = temp_water;
                    energy = temp_energy;
                    fee = temp_fee;
                    otherFee = temp_otherFee;
                    total = temp_total;
                    returnList.add(boList);

                } else {
                    rent = rent + boList.getRent();
                    water = water + boList.getWater();
                    energy = energy + boList.getEnergy();
                    fee = fee + boList.getFee();
                    otherFee = otherFee + boList.getOtherFee();
                    total = total + boList.getTotal();
                    returnList.add(boList);
                }
                if (list.size() - 1 == index) {
                    bo = new CollectDetailBO();
                    bo.setArea(area);
                    bo.setYear("");
                    bo.setMonth("");
                    bo.setProject("");
                    bo.setRentAddress("合计");
                    bo.setRent(rent);
                    bo.setWater(water);
                    bo.setEnergy(energy);
                    bo.setFee(fee);
                    bo.setOtherFee(otherFee);
                    bo.setTotal(total);
                    bo.setLandlord("");
                    bo.setContact("");
                    bo.setTaxesConfirm("");
                    returnList.add(bo);
                }

            }
        }

        return returnList;
    }

    @Override
    public List<String> getAreas() throws SerException {
        String[] fields = new String[]{"area"};
        List<WaitPayBO> waitPayBOS = super.findBySql("select distinct area from housepay_waitpay where pay = 0 group by area order by area asc ", WaitPayBO.class, fields);

        List<String> areasList = waitPayBOS.stream().map(WaitPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }

    @Override
    public List<ProjectCollectBO> collectProject(CollectProjectTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        List<ProjectCollectBO> boList = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)
                && null != to.getProjects() && to.getProjects().length <= 0) {
            boList.add(new ProjectCollectBO());
            return boList;
        }
        String condition = "'".concat(String.join("','", to.getProjects())).concat("'");
        String[] fields = new String[]{"project", "rent", "water", "energy", "fee", "otherFee", "total"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.project, sum(a.rent) AS rent,sum(a.water) AS water, ");
        sb.append(" sum(a.energy) AS energy,sum(a.fee) AS fee,sum(a.otherFee) AS otherFee,sum(total) AS total ");
        sb.append(" FROM (SELECT concat(a.year,'-',if(a.month<=9,concat('0',a.month),a.month))as date,a.* FROM housepay_waitpay as a) as a ");
        sb.append(" WHERE a.project IN ("+condition+") and date BETWEEN '"+startTime+"' and '"+endTime+"' and pay=0 ");
        sb.append(" GROUP BY a.project ");
        sb.append(" ORDER BY a.project DESC ; ");
        String sql = sb.toString();
        boList = super.findBySql(sql, ProjectCollectBO.class, fields);
        if(boList!=null && boList.size()>0){
            boList.stream().forEach(str->{
                str.setYear(startTime+"至"+endTime);
            });
            ProjectCollectBO bo = new ProjectCollectBO();
            bo.setProject("合计");
            bo.setRent(boList.stream().mapToDouble(ProjectCollectBO::getRent).sum());
            bo.setWater(boList.stream().mapToDouble(ProjectCollectBO::getWater).sum());
            bo.setEnergy(boList.stream().mapToDouble(ProjectCollectBO::getEnergy).sum());
            bo.setOtherFee(boList.stream().mapToDouble(ProjectCollectBO::getOtherFee).sum());
            bo.setTotal(boList.stream().mapToDouble(ProjectCollectBO::getTotal).sum());
            bo.setFee(boList.stream().mapToDouble(ProjectCollectBO::getFee).sum());
            boList.add(bo);

        }
        return boList;
    }

    @Override
    public List<CollectDetailBO> collectProjectDatail(CollectProjectTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        List<CollectDetailBO> returnList = new ArrayList<>();
        String[] fields = new String[]{"area", "year", "month", "project", "rentAddress", "rent", "water", "energy", "fee", "otherFee",
                "total", "landlord", "contact", "taxesConfirm"};
        StringBuilder sb = new StringBuilder();
        List<CollectDetailBO> list = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)
                && null != to.getProjects() && to.getProjects().length <= 0) {
            list.add(new CollectDetailBO());
            return list;
        }
        String condition = "'".concat(String.join("','", to.getProjects())).concat("'");
        sb.append(" SELECT a.area,a.year,a.month, ");
        sb.append(" a.project,a.rentAddress, sum(a.rent) AS rent,sum(a.water) AS water, ");
        sb.append(" sum(a.energy) AS energy,sum(a.fee) AS fee,sum(a.otherFee) AS otherFee, ");
        sb.append(" sum(total) AS total,a.landlord,a.contact,a.taxesConfirm ");
        sb.append(" FROM (SELECT concat(a.year,'-',if(a.month<=9,concat('0',a.month),a.month))as date,a.* FROM housepay_waitpay as a) as a ");
        sb.append(" WHERE a.project IN (" + condition + ") and date BETWEEN '" + startTime + "' and '" + endTime + "' and pay=0 ");
        sb.append(" GROUP BY a.project,a.year,a.month,a.area,a.rentAddress,a.landlord,a.contact,a.taxesConfirm ");
        sb.append(" ORDER BY a.project DESC ");
        String sql = sb.toString();
        list = super.findBySql(sql, CollectDetailBO.class, fields);
        if (list != null && list.size() > 0) {
            String project = list.get(0).getProject();
            Double rent = 0d;
            Double water = 0d;
            Double energy = 0d;
            Double fee = 0d;
            Double otherFee = 0d;
            Double total = 0d;
            int index = -1;
            CollectDetailBO bo = new CollectDetailBO();
            for (CollectDetailBO boList : list) {
                index = index + 1;
                if (!project.equals(boList.getProject())) {
                    String temp_project = boList.getProject();
                    Double temp_rent = boList.getRent();
                    Double temp_water = boList.getWater();
                    Double temp_energy = boList.getEnergy();
                    Double temp_fee = boList.getFee();
                    Double temp_otherFee = boList.getOtherFee();
                    Double temp_total = boList.getTotal();

                    bo = new CollectDetailBO();
                    bo.setArea("");
                    bo.setYear("");
                    bo.setMonth("");
                    bo.setProject(project);
                    bo.setRentAddress("合计");
                    bo.setRent(rent);
                    bo.setWater(water);
                    bo.setEnergy(energy);
                    bo.setFee(fee);
                    bo.setOtherFee(otherFee);
                    bo.setTotal(total);
                    bo.setLandlord("");
                    bo.setContact("");
                    bo.setTaxesConfirm("");
                    returnList.add(bo);

                    //重置
                    project = temp_project;
                    rent = temp_rent;
                    water = temp_water;
                    energy = temp_energy;
                    fee = temp_fee;
                    otherFee = temp_otherFee;
                    total = temp_total;
                    returnList.add(boList);

                } else {
                    rent = rent + boList.getRent();
                    water = water + boList.getWater();
                    energy = energy + boList.getEnergy();
                    fee = fee + boList.getFee();
                    otherFee = otherFee + boList.getOtherFee();
                    total = total + boList.getTotal();
                    returnList.add(boList);
                }
                if (list.size() - 1 == index) {
                    bo = new CollectDetailBO();
                    bo.setArea("");
                    bo.setYear("");
                    bo.setMonth("");
                    bo.setProject(project);
                    bo.setRentAddress("合计");
                    bo.setRent(rent);
                    bo.setWater(water);
                    bo.setEnergy(energy);
                    bo.setFee(fee);
                    bo.setOtherFee(otherFee);
                    bo.setTotal(total);
                    bo.setLandlord("");
                    bo.setContact("");
                    bo.setTaxesConfirm("");
                    returnList.add(bo);
                }

            }
        }

        return returnList;
    }

    @Override
    public List<String> getProject() throws SerException {
        String[] fields = new String[]{"project"};
        List<WaitPayBO> waitPayBOS = super.findBySql("select distinct project from housepay_waitpay where pay = 0 group by project order by project asc ", WaitPayBO.class, fields);

        List<String> projectsList = waitPayBOS.stream().map(WaitPayBO::getProject)
                .filter(project -> (project != null || !"".equals(project.trim()))).distinct().collect(Collectors.toList());


        return projectsList;
    }

}