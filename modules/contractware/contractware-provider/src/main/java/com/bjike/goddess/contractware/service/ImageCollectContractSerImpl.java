package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.contractware.bo.*;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.dto.ImageCollectContractDTO;
import com.bjike.goddess.contractware.entity.ContractManagement;
import com.bjike.goddess.contractware.entity.ImageCollectContract;
import com.bjike.goddess.contractware.enums.ContractCharacter;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* 合同管理图形化业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="contractwareSerCache")
@Service
public class ImageCollectContractSerImpl extends ServiceImpl<ImageCollectContract, ImageCollectContractDTO> implements ImageCollectContractSer {
    @Autowired
    private ContractManagementSer contractManagementSer;

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
    public OptionContractBO figureShow() throws SerException {
        ContractManagementDTO contractManagementDTO = new ContractManagementDTO();
        List<ContractManagement> contractManagements = contractManagementSer.findByCis(contractManagementDTO);
        Integer business = (int)contractManagements.stream().filter(p -> p.getContractCharacter() != null).map(p -> p.getContractCharacter() == ContractCharacter.BUSINESS).count();
        Integer financial = (int)contractManagements.stream().filter(p -> p.getContractCharacter() != null).map(p -> p.getContractCharacter() == ContractCharacter.FINANCIAL).count();
        Integer outsourcing = (int)contractManagements.stream().filter(p -> p.getContractCharacter() != null).map(p -> p.getContractCharacter() == ContractCharacter.OUTSOURCING).count();

        OptionContractBO optionBO = new OptionContractBO();

        TitleBO titleBO = new TitleBO();
        String text_1 = "合同分布图";
        String x = "center";
        titleBO.setX(x);
        titleBO.setText(text_1);

        ToolTipBO toolTipBO = new ToolTipBO();
        String trigger = "item";
        String formatter = "{a} <br/>{b} : {c} ({d}%)";
        toolTipBO.setTrigger(trigger);
        toolTipBO.setFormatter(formatter);

        LegendBO legendBO = new LegendBO();
        String orient = "vertical";
        String left = "left";
        legendBO.setOrient(orient);
        legendBO.setLeft(left);

        SeriesContractBO seriesContractBO = new SeriesContractBO();
        seriesContractBO.setName("合同");
        seriesContractBO.setType("pie");
        seriesContractBO.setRadius("55%");
        String[] center = new String[]{"50%","60%"};
        seriesContractBO.setCenter(center);

        List<DataBO> dataBOS = new ArrayList<>();
        DataBO dataBO = new DataBO();
        dataBO.setName("业务合同");
        dataBO.setValue(business);
        DataBO dataBO1 = new DataBO();
        dataBO1.setName("财务合同");
        dataBO1.setValue(financial);
        DataBO dataBO2 = new DataBO();
        dataBO2.setName("外包合同");
        dataBO2.setValue(outsourcing);

        dataBOS.add(dataBO);
        dataBOS.add(dataBO1);
        dataBOS.add(dataBO2);

        optionBO.setLegend(legendBO);
        optionBO.setSeries(seriesContractBO);
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(toolTipBO);

        return optionBO;
    }
}