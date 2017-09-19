package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.ExchangeTemplateBO;
import com.bjike.goddess.managepromotion.dto.ExchangeTemplateDTO;
import com.bjike.goddess.managepromotion.entity.ExchangeTemplate;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.ExchangeTemplateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 各类交流沟通模板业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class ExchangeTemplateSerImpl extends ServiceImpl<ExchangeTemplate, ExchangeTemplateDTO> implements ExchangeTemplateSer {
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public ExchangeTemplateBO getOne(String id) throws SerException {
        ExchangeTemplate exchangeTemplate = super.findById(id);
        return BeanTransform.copyProperties(exchangeTemplate, ExchangeTemplateBO.class);
    }

    @Override
    public List<ExchangeTemplateBO> list(ExchangeTemplateDTO dto) throws SerException {
        List<ExchangeTemplate> exchangeTemplates = super.findByCis(dto);
        List<ExchangeTemplateBO> exchangeTemplateBOS = BeanTransform.copyProperties(exchangeTemplates, ExchangeTemplateBO.class);
        return exchangeTemplateBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExchangeTemplateBO save(ExchangeTemplateTO to) throws SerException {
        ExchangeTemplate exchangeTemplate = BeanTransform.copyProperties(to, ExchangeTemplate.class, true);
        super.save(exchangeTemplate);
        ExchangeTemplateBO bo = BeanTransform.copyProperties(exchangeTemplate, ExchangeTemplateBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExchangeTemplateBO edit(ExchangeTemplateTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            ExchangeTemplate exchangeTemplate = super.findById(to.getId());
            LocalDateTime createTime = exchangeTemplate.getCreateTime();
            exchangeTemplate = BeanTransform.copyProperties(to,ExchangeTemplate.class,true);
            exchangeTemplate.setCreateTime(createTime);
            exchangeTemplate.setModifyTime(LocalDateTime.now());
            super.update(exchangeTemplate);
            ExchangeTemplateBO bo = BeanTransform.copyProperties(exchangeTemplate,ExchangeTemplateBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }
    @Override
    public ExchangeTemplateBO getContent(String title) throws SerException {
        ExchangeTemplate exchangeTemplate = new ExchangeTemplate();
        if(StringUtils.isNotBlank(title)){
            ExchangeTemplateDTO dto  = new ExchangeTemplateDTO();
            dto.getConditions().add(Restrict.eq("title",title));
            exchangeTemplate = super.findOne(dto);
        }
        ExchangeTemplateBO bo = BeanTransform.copyProperties(exchangeTemplate,ExchangeTemplateBO.class);
        return bo;
    }

    @Override
    public List<String> getTitle() throws SerException {
        String[] fields = new String[]{"title"};
        String sql = " SELECT title AS title FROM managepromotion_exchangetemplate GROUP BY title ";
        List<ExchangeTemplate> exchangeTemplates = super.findBySql(sql,ExchangeTemplate.class,fields);
        List<String> titleList = exchangeTemplates.stream().map(ExchangeTemplate::getTitle).collect(Collectors.toList());
        return titleList;
    }
}