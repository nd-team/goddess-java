package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.CommunicationTemplateBO;
import com.bjike.goddess.employeecontract.dto.CommunicationTemplateDTO;
import com.bjike.goddess.employeecontract.entity.CommunicationTemplate;
import com.bjike.goddess.employeecontract.enums.GuideAddStatus;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.to.CommunicationTemplateTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 各类交流沟通模板业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="employeecontractSerCache")
@Service
public class CommunicationTemplateSerImpl extends ServiceImpl<CommunicationTemplate, CommunicationTemplateDTO> implements CommunicationTemplateSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private ContractChangeInformationSer contractChangeInformationSer;

    @Autowired
    private ContractCollectSer contractCollectSer;

    @Autowired
    private ContractInformationSer contractInformationSer;


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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("communicationtemplate");
        obj.setDescribesion("交流模板");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagContractChange = contractChangeInformationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contractchangeinformation");
        obj.setDescribesion("合同变更信息");
        if (flagContractChange) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagContractCollect = contractCollectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contractcollect");
        obj.setDescribesion("合同汇总");
        if (flagContractCollect) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagContractInformation = contractInformationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contractinformation");
        obj.setDescribesion("合同信息");
        if (flagContractInformation) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }


    //功能导航权限
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = true;
                break;
            case ADD:
                flag = true;
                break;
            case EDIT:
                flag = true;
                break;
            case AUDIT:
                flag = true;
                break;
            case DELETE:
                flag = true;
                break;
            case IMPORT:
                flag = true;
                break;
            case EXPORT:
                flag = true;
                break;
            case UPLOAD:
                flag = true;
                break;
            case DOWNLOAD:
                flag = true;
                break;
            case SEE:
                flag = true;
                break;
            case SEEFILE:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    /**
     * 出车管理导航栏核对查看权限（部门级别）
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
     * 出车管理导航栏核对删除添加编辑..审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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
     * 财务出车管理导航栏核对查看权限（部门级别）
     */
    private Boolean financeGuideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 财务出车管理导航栏核对功能审核权限（部门级别）
     */
    private Boolean financeGuideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
    @Override
    public void add(CommunicationTemplateTO communicationTemplateTO) throws SerException {
        CommunicationTemplate model = BeanTransform.copyProperties(communicationTemplateTO,CommunicationTemplate.class,true);
        super.save(model);
    }

    @Override
    public void modify(CommunicationTemplateTO communicationTemplateTO) throws SerException {
        CommunicationTemplate model = super.findById(communicationTemplateTO.getId());
        CommunicationTemplate communicationTemplate = new CommunicationTemplate();
        BeanTransform.copyProperties(communicationTemplateTO,communicationTemplate,true,"createTime","modifyTime");
        communicationTemplate.setCreateTime(model.getCreateTime());
        communicationTemplate.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public List<CommunicationTemplateBO> pageList(CommunicationTemplateDTO communicationTemplateDTO) throws SerException {
        List<CommunicationTemplate> communicationTemplates = super.findByPage(communicationTemplateDTO);
        List<CommunicationTemplateBO> communicationTemplateBOS = BeanTransform.copyProperties(communicationTemplates,CommunicationTemplateBO.class);
        return communicationTemplateBOS;
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            CommunicationTemplate model = super.findById(id);
            if (model != null){
                super.remove(model);
            }else {
                throw new SerException("数据库中没有该条数据");
            }
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public CommunicationTemplateBO findOne(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            CommunicationTemplate model = super.findById(id);
            CommunicationTemplateBO communicationTemplateBO = BeanTransform.copyProperties(model,CommunicationTemplateBO.class,false);
            return communicationTemplateBO;
        }else {
            throw new SerException("id不能为空");
        }
    }

}