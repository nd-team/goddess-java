package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.CollectData;
import com.bjike.goddess.businessinteraction.bo.CollectEmailBO;
import com.bjike.goddess.businessinteraction.dto.CollectEmailDTO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.CollectEmail;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.to.CollectEmailTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private InteractionRelationSer interactionRelationSer;
    @Autowired
    private LeavingMessageSer leavingMessageSer;
    @Autowired
    private DemandSer demandSer;
    @Autowired
    private TalkDetailSer talkDetailSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;


    @Override
    public Long countInter(CollectEmailDTO collectEmailDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        RpcTransmit.transmitUserToken( userToken);
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        Long count =  super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOneById(String id) throws SerException {


        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CollectEmail collectEmail = super.findById(id);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Cacheable
    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        RpcTransmit.transmitUserToken( userToken);
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        List<CollectEmail> list = super.findByCis(collectEmailDTO, true);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加操作");
        }


        List<String> works = Arrays.asList(collectEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }


        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }


        RpcTransmit.transmitUserToken(userToken);

        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCustomerSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());
        //设置行业
        collectEmail.setWork(String.valueOf(workBuf));

        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑操作");
        }


        List<String> works = Arrays.asList(collectEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }

        RpcTransmit.transmitUserToken(userToken);

        //先查出来
        CollectEmail getEmail = super.findById(collectEmailTO.getId());
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        BeanUtils.copyProperties(collectEmail,getEmail,"sendNumAndUnit",
                "sendObject","work","createTime","id","status","lastSendTime");
        getEmail.setModifyTime(LocalDateTime.now());
        getEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCustomerSendUnit().getCode());
        getEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        getEmail.setSendObject(String.valueOf(emails));
        //设置行业
        getEmail.setWork(String.valueOf(workBuf));

        super.update(getEmail);
        return BeanTransform.copyProperties(getEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        //商务模块冻结权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }


        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        //商务模块解冻权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    
    @Override
    public List<CollectEmailBO> collectCollectEmail(String[] works) throws SerException {
        if(works == null || works.length<=0 ){
            throw new SerException("汇总失败，请选择地区");
        }
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> areas = Arrays.asList(works);
        StringBuffer sb = new StringBuffer("");
        for(String str : areas ) {
            collectDataList = new ArrayList<>();
            sb.append( str.replaceAll(str,"'"+str+"'") ).append(",");

            String[] fields = new String[]{"counts","remark"};
            //互动联系信息数量
            InteractionRelationDTO interDTO = new InteractionRelationDTO();
            interDTO.getConditions().add(Restrict.eq("area",str));
            Long interCount = interactionRelationSer.count( interDTO );
            CollectData collectData = new CollectData();
            collectData.setName("互动联系信息数量");
            collectData.setCounts( interCount!=null  ? interCount:0 );
            collectDataList.add( collectData );

            //留言数量
            String sql = "select count(*) as counts , '留言数量' as remark from businessinteraction_leavingmessage  bl " +
                    "  INNER JOIN  businessinteraction_interactionrelation bi  on bl.interactionRelation_id = bi.id where bi.area = '"+str+"'  ";
            List<CollectEmailBO> collectEmailBOS = leavingMessageSer.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("留言数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                    ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
            collectDataList.add( collectData );

            //需求记录数量
            DemandDTO demandDTO = new DemandDTO();
            demandDTO.getConditions().add(Restrict.eq("area",str ) );
            Long demandCount = demandSer.count(demandDTO );
            collectData = new CollectData();
            collectData.setName("需求记录数量");
            collectData.setCounts( demandCount !=null  ? demandCount:0 );
            collectDataList.add( collectData );

            //直接合作方数量
            TalkDetailDTO talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area",str ) );
            talkDetailDTO.getConditions().add(Restrict.eq("cooperWay","直接合作"));
            Long talkCount = talkDetailSer.count( talkDetailDTO );
            collectData = new CollectData();
            collectData.setName("直接合作方数量");
            collectData.setCounts( talkCount!=null ? talkCount:0 );
            collectDataList.add( collectData );

            //中介合作方数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area",str ) );
            talkDetailDTO.getConditions().add(Restrict.eq("cooperWay","中介"));
            talkCount = talkDetailSer.count( talkDetailDTO );
            collectData = new CollectData();
            collectData.setName("中介合作方数量");
            collectData.setCounts( talkCount!=null ? talkCount:0 );
            collectDataList.add( collectData );

            //达成合作数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area",str ) );
            talkDetailDTO.getConditions().add(Restrict.eq("cooperCondition","已达成"));
            talkCount = talkDetailSer.count( talkDetailDTO );
            collectData = new CollectData();
            collectData.setName("达成合作数量");
            collectData.setCounts( talkCount!=null ? talkCount:0 );
            collectDataList.add( collectData );

            //未达成合作数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area",str ) );
            talkDetailDTO.getConditions().add(Restrict.eq("cooperCondition","未达成"));
            talkCount = talkDetailSer.count( talkDetailDTO );
            collectData = new CollectData();
            collectData.setName("未达成合作数量");
            collectData.setCounts( talkCount!=null ? talkCount:0 );
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }

        //合计
        collectDataList = new ArrayList<>();
        calculateTotal(sb.substring(0,sb.lastIndexOf(",")) , collectDataList , collectEmailBOList );
        return collectEmailBOList;
    }

    public void calculateTotal(String area ,List<CollectData> collectDataList, List<CollectEmailBO> collectEmailBOList ) throws SerException {
        String[] fields = new String[]{"counts","remark"};
        //互动联系信息数量
        String sql = " select count(*) as counts , '互动联系信息数量' as remark from businessinteraction_interactionrelation where area in("+area+")";
        List<CollectEmailBO> collectEmailBOS = interactionRelationSer.findBySql(sql, CollectEmailBO.class, fields);
        CollectData collectData = new CollectData();
        collectData.setName("互动联系信息数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );


        //留言数量
        sql = " select count(*) as counts , '留言数量' as remark  " +
                " from businessinteraction_leavingmessage  bl " +
                " INNER JOIN  businessinteraction_interactionrelation bi  on bl.interactionRelation_id = bi.id where bi.area in ( "+area+")";
        collectEmailBOS = leavingMessageSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("留言数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        //需求记录数量
        sql = " select count(*) as counts , '需求记录数量' as remark from businessinteraction_demand where area in("+area+")";
        collectEmailBOS = demandSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("需求记录数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        //直接合作方数量
        sql = " select count(*) as counts , '直接合作方数量' as remark from businessinteraction_talkdetail where cooperWay ='直接合作' and area in("+area+")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("直接合作方数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        //中介合作方数量
        sql = " select count(*) as counts , '中介合作方数量' as remark from businessinteraction_talkdetail where cooperWay = '中介' and  area in("+area+")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("中介合作方数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        //达成合作数量
        sql = " select count(*) as counts , '达成合作数量' as remark from businessinteraction_talkdetail where cooperCondition = '已达成' and  area in("+area+")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("达成合作数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        //未达成合作数量
        sql = " select count(*) as counts , '未达成合作数量' as remark from businessinteraction_talkdetail where cooperCondition = '未达成' and  area in("+area+")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("未达成合作数量");
        collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())):0 );
        collectDataList.add( collectData );

        CollectEmailBO cbo = new CollectEmailBO();
        cbo.setRemark( "合计" );
        cbo.setCollectDataList( collectDataList );
        collectEmailBOList.add( cbo );
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

    @Override
    public List<String> areaList() throws SerException {
        String [] field = new String[]{"area"};
        String sql = "select area from businessinteraction_interactionrelation group by area";

        List<InteractionRelation> list = interactionRelationSer.findBySql(sql , InteractionRelation.class , field );
        List<String> areaList = list.stream().map(InteractionRelation::getArea).collect(Collectors.toList());
        return areaList;
    }
}