package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import com.bjike.goddess.housepay.entity.WaitPay;
import com.bjike.goddess.housepay.enums.GuideAddrStatus;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
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
    public List<AreaCollectBO> collectArea(String[] areas) throws SerException {
        if(areas == null || areas.length <= 0){
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for(int i = 0;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areasStr = StringUtils.join(areasTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  area,CONCAT(DATE_FORMAT(payTime,'%Y-%m-%d %H:%i:%s'),'') AS payTime,sum(rent) AS rent,sum(water) AS water, ");
        sb.append(" sum(energy) AS energy,sum(fee) AS fee,sum(otherFee) AS otherFee, ");
        sb.append(" sum(rent+water+energy+fee+otherFee) AS total FROM housepay_waitpay a ");
        sb.append(" WHERE area IN (");
        sb.append(areasStr);
        sb.append(")GROUP BY payTime,area ORDER BY area ");
        String sql = sb.toString();
        String [] fields = new String[]{"area","payTime","rent","water","energy","fee","otherFee","total"};
        List<AreaCollectBO> areaCollectBOS = super.findBySql(sql,AreaCollectBO.class,fields);
        return areaCollectBOS;
    }

    @Override
    public List<String> getAreas() throws SerException {
        String [] fields = new String[]{"area"};
        List<WaitPayBO> waitPayBOS = super.findBySql("select distinct area from housepay_waitpay group by area order by area asc ",WaitPayBO.class,fields);

        List<String> areasList = waitPayBOS.stream().map(WaitPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }
    @Override
    public List<ProjectCollectBO> collectProject(String[] projects) throws SerException {
        if(projects == null || projects.length <= 0){
            throw new SerException("汇总失败，请选择项目");
        }
        String[] projectsTemp = new String[projects.length];
        for(int i = 0;i<projects.length;i++){
            projectsTemp[i] = "'"+projects[i]+"'";
        }
        String projectsStr = StringUtils.join(projectsTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT project,CONCAT(DATE_FORMAT(payTime,'%Y-%m-%d %H:%i:%s'),'') AS payTime,sum(rent) AS rent,sum(water) AS water, ");
        sb.append(" sum(energy) AS energy,sum(fee) AS fee,sum(otherFee) AS otherFee, ");
        sb.append(" sum(rent+water+energy+fee+otherFee) AS total FROM housepay_waitpay a ");
        sb.append(" WHERE project IN ( ");
        sb.append(projectsStr);
        sb.append(" )GROUP BY payTime,project ORDER BY project ");
        String sql = sb.toString();
        System.out.println(sql);
        String [] fields = new String[]{"project","payTime","rent","water","energy","fee","otherFee","total"};
        List<ProjectCollectBO> projectCollectBOS = super.findBySql(sql,ProjectCollectBO.class,fields);
        return projectCollectBOS;
    }

    @Override
    public List<String> getProject() throws SerException {
        String[] fields = new String[]{"project"};
        List<WaitPayBO> waitPayBOS = super.findBySql("select distinct project from housepay_waitpay group by project order by project asc ", WaitPayBO.class, fields);

        List<String> projectsList = waitPayBOS.stream().map(WaitPayBO::getProject)
                .filter(project -> (project != null || !"".equals(project.trim()))).distinct().collect(Collectors.toList());


        return projectsList;
    }

}