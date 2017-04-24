package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CollectData;
import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.dto.CollectEmailDTO;
import com.bjike.goddess.capability.entity.CollectEmail;
import com.bjike.goddess.capability.to.CollectEmailTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 商务项目合同邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 商务项目合同邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CompanyCapabilitySer companyCapabilityAPI;
    @Autowired
    private CooperCapabilitySer cooperCapabilityAPI;
    @Autowired
    private SelfCapabilitySer selfCapabilityAPI;

    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空哦");
        }
        CollectEmail selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability,CollectEmailBO.class);

    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByPage(collectEmailDTO);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());
//        collectEmail.setCreatePersion("汪如意");

        //设置汇总公司名和人名
        String companyOrName ="";
        for(String str : collectEmailTO.getCompanyOrNames()){
            companyOrName += str+",";
        }
        collectEmail.setCompanyOrName( companyOrName );

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());

        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        if (StringUtils.isBlank(collectEmailTO.getId() )) {
            throw new SerException("id不能为空");
        }
        CollectEmail temp = super.findById(collectEmailTO.getId());

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        BeanUtils.copyProperties( collectEmail,temp ,"id","createTime","createPersion","lastSendTime","status");
        temp.setModifyTime(LocalDateTime.now());
//        collectEmail.setCreatePersion(us
// ike
// erAPI.currentUser().getUsername());

        //设置汇总公司名和人名
        String companyOrName ="";
        for(String str : collectEmailTO.getCompanyOrNames()){
            companyOrName += str+",";
        }
        temp.setCompanyOrName( companyOrName );

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    @Override
    public List<CollectEmailBO> collectCompanyEmail(String[] companyOrName) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> companys = Arrays.asList(companyOrName);
        for(String str : companys ) {
            String[] fields = new String[]{"counts","remark"};
            //专业资质认证数量
            String sql = "select count(professionAuthen) as counts , '专业资质认证数量' as remark from capability_companycapability where company = '"+str+"'  ";
            List<CollectEmailBO> collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("专业资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            //管理资质认证数量
            sql = "select count(manageAuthen) as counts , '管理资质认证数量' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("管理资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            //公司荣誉证书数量
            sql = "select count(companyCertificate) as counts , '公司荣誉证书数量' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司荣誉证书数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            //公司人员组成
            sql = "select count(personForm) as counts , '公司人员组成' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司人员组成");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            //公司已完成项目数量
            sql = "select count(completePro) as counts , '公司已完成项目数量' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司已完成项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            //公司正在进行中项目数量
            sql = "select count(inProjct) as counts , '公司正在进行中项目数量' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司正在进行中项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectSelfEmail(String[] name) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> names = Arrays.asList(name);
        for(String str : names ) {
            String[] fields = new String[]{"counts", "remark"};
            //个人资质数量
            String sql = "select count(capacity) as counts , '个人资质数量' as remark from capability_selfcapability where name = '"+str+"'  ";
            List<CollectEmailBO> collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("个人资质数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );
            //个人经手项目数量
            sql = "select count(selfProject) as counts , '个人经手项目数量' as remark from capability_selfcapability where name = '"+str+"'  ";
            collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("个人经手项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );
            //个人社交资源数量
            sql = "select count(contactName) as counts , '个人社交资源数量' as remark from capability_selfcapability where name = '"+str+"'  ";
            collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("个人社交资源数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectCooperEmail(String[] companyOrName) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> companys = Arrays.asList(companyOrName);
        for(String str : companys ) {
            String[] fields = new String[]{"counts", "remark"};
            //专业资质认证数量
            String sql = "select count(professionAuthen) as counts , '专业资质认证数量' as remark from capability_coopercapability where companyName = '"+str+"'  ";
            List<CollectEmailBO> collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("专业资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );
            //管理资质认证数量
            sql = "select count(manageAuthen) as counts , '管理资质认证数量' as remark from capability_coopercapability where companyName = '"+str+"'  ";
            collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("管理资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );
            //公司参与项目数量
            sql = "select count(companyProject) as counts , '公司参与项目数量' as remark from capability_coopercapability where companyName = '"+str+"'  ";
            collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司参与项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? collectEmailBOS.get(0).getCounts():0 );
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<String> listName(String type) throws SerException {
        List<String> list = new ArrayList<>();
        //商业能力
        if( "0".equals(type)){
            list = companyCapabilityAPI.listAllCompanyName();
        }else if( "1".equals(type)){
            //个人能力
            list = selfCapabilityAPI.listAllSelfName();
        }else if( "2".equals(type)){
            //合作对象
            list = cooperCapabilityAPI.listAllCompanyName();
        }
        return list;
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }


}