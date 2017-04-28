package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.businessinteraction.bo.CollectEmailBO;
import com.bjike.goddess.businessinteraction.dto.CollectEmailDTO;
import com.bjike.goddess.businessinteraction.service.CollectEmailSer;
import com.bjike.goddess.businessinteraction.to.CollectEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送定制业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 邮件发送定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectEmailApiImpl")
public class CollectEmailApiImpl implements CollectEmailAPI {

    @Autowired
    private CollectEmailSer collectEmailSer;

    @Override
    public Long countInter(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.countInter(collectEmailDTO);
    }

    @Override
    public CollectEmailBO getOneById(String id) throws SerException {
        return collectEmailSer.getOneById(id);
    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.listCollectEmail( collectEmailDTO );
    }

    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.addCollectEmail( collectEmailTO);
    }

    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.editCollectEmail(collectEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        collectEmailSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        collectEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        collectEmailSer.thawCollectEmail(id);
    }

    @Override
    public List<CollectEmailBO> collectCollectEmail(String[] works) throws SerException {
        return collectEmailSer.collectCollectEmail(works);
    }

    @Override
    public List<String> areaList() throws SerException {
        return collectEmailSer.areaList();
    }
}