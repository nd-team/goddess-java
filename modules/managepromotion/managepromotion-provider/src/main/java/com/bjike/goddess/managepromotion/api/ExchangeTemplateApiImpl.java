package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.ExchangeTemplateBO;
import com.bjike.goddess.managepromotion.dto.ExchangeTemplateDTO;
import com.bjike.goddess.managepromotion.service.ExchangeTemplateSer;
import com.bjike.goddess.managepromotion.to.ExchangeTemplateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各类交流沟通模板业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("exchangeTemplateApiImpl")
public class ExchangeTemplateApiImpl implements ExchangeTemplateAPI {
    @Autowired
    private ExchangeTemplateSer exchangeTemplateSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return exchangeTemplateSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return exchangeTemplateSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ExchangeTemplateBO getOne(String id) throws SerException {
        return exchangeTemplateSer.getOne(id);
    }

    @Override
    public List<ExchangeTemplateBO> list(ExchangeTemplateDTO dto) throws SerException {
        return exchangeTemplateSer.list(dto);
    }

    @Override
    public ExchangeTemplateBO save(ExchangeTemplateTO to) throws SerException {
        return exchangeTemplateSer.save(to);
    }

    @Override
    public ExchangeTemplateBO edit(ExchangeTemplateTO to) throws SerException {
        return exchangeTemplateSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        exchangeTemplateSer.delete(id);

    }
    @Override
    public ExchangeTemplateBO getContent(String title) throws SerException {
        return exchangeTemplateSer.getContent(title);
    }
    @Override
    public List<String> getTitle() throws SerException {
        return exchangeTemplateSer.getTitle();
    }
}