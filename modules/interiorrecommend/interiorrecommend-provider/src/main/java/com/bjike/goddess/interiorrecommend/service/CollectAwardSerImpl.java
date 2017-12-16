package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.CollectAwardBO;
import com.bjike.goddess.interiorrecommend.dto.CollectAwardDTO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.dto.SchemeImplementDTO;
import com.bjike.goddess.interiorrecommend.entity.CollectAward;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.entity.SchemeImplement;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.enums.SolutionStatus;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
* 内部推荐奖管理汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="interiorrecommendSerCache")
@Service
public class CollectAwardSerImpl extends ServiceImpl<CollectAward, CollectAwardDTO> implements CollectAwardSer {
    @Autowired
    private RecommendSchemeSer recommendSchemeSer;

    @Autowired
    private SchemeImplementSer schemeImplementSer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean collectAward = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("collectaward");
        obj.setDescribesion("内部推荐奖励信息汇总");
        if (collectAward || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSchement = schemeImplementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("schementimplement");
        obj.setDescribesion("内部推荐奖励方案实施");
        if (flagSchement) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        Boolean flagScheme = recommendSchemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendtype");
        obj.setDescribesion("内部推荐方案");
        if (flagScheme) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }
    @Override
    public CollectAwardBO collectByMonth(String year, String month) throws SerException {
        String time = new String();
        if(year !=null && month != null) {
            if(month.length() == 1){
                month = 0 + month;
            }
             time = year + month;
        }else{
            time = LocalDate.now().toString().substring(0,LocalDate.now().toString().lastIndexOf("-")).replace("-","");
        }
        String sql = "select type ,predictMoney from interiorrecommend_recommendscheme where date_format(makeTime,'%Y%m')='"+time+"'";
        List<RecommendScheme> recommendSchemes = recommendSchemeSer.findBySql(sql,RecommendScheme.class,new String[]{"type","predictMoney"});
        String sql2 = "select type from interiorrecommend_recommendscheme where date_format(makeTime,'%Y%m')='"+time+"' and status=0";
        List<RecommendScheme> recommendSchemes1 = recommendSchemeSer.findBySql(sql2,RecommendScheme.class,new String[]{"type"});
        String scheSql = "select beRecommender from interiorrecommend_schemeimplement where date_format(recommendTime,'%Y%m')='"+time+"'";
        String scheSql2 = "select beRecommender from interiorrecommend_schemeimplement where date_format(recommendTime,'%Y%m')='"+time+"' and is_isEntry=1";
        String scheSql3 = "select beRecommender from interiorrecommend_schemeimplement where date_format(recommendTime,'%Y%m')='"+time+"' and is_isRegular=1";
        List<SchemeImplement> schemeImplements = schemeImplementSer.findBySql(scheSql,SchemeImplement.class,new String[]{"beRecommender"});
        List<SchemeImplement> schemeImplements1 = schemeImplementSer.findBySql(scheSql2,SchemeImplement.class,new String[]{"beRecommender"});
        List<SchemeImplement> schemeImplements2 = schemeImplementSer.findBySql(scheSql3,SchemeImplement.class,new String[]{"beRecommender"});
        CollectAwardBO bo = new CollectAwardBO(0l,0l,0l,0l,0l,0l);
        if(recommendSchemes !=null && recommendSchemes.size() > 0){
            Long recommendAward = recommendSchemes.stream().filter(p -> null != p.getPredictMoney()).mapToLong(p -> p.getPredictMoney()).sum();
            //汇总制定内部推荐奖方案数目
            bo.setSchemeNumber(new Long((long)recommendSchemes.size()));
            //汇总预计推荐奖金额
            bo.setRecommendAward(recommendAward);
        }
        if(recommendSchemes1 !=null && recommendSchemes1.size() > 0){
            Long embodimentNumber = (long)recommendSchemes.size();
            //汇总实施方案进行中的方案数目
            bo.setEmbodimentNumber(embodimentNumber);
        }
        if(schemeImplements !=null && schemeImplements.size() > 0){
            Long beRecommender = (long)schemeImplements.size();
            //汇总推荐人数
            bo.setRecommenderNumber(beRecommender);
        }
        if(schemeImplements1 !=null && schemeImplements1.size() > 0){
            Long beRecommender = (long)schemeImplements1.size();
            //汇总推荐入职人数
            bo.setRecommendEntry(beRecommender);
        }
        if(schemeImplements2 !=null && schemeImplements2.size() >0){
            Long beRecommender = (long)schemeImplements2.size();
            //汇总推荐转正人数
            bo.setRecommendRegular(beRecommender);
        }
        return bo;
    }

    @Override
    public CollectAwardBO allCollect(String today) throws SerException {
        CollectAwardBO bo = new CollectAwardBO(0l,0l,0l,0l,0l,0l);
        String startTime = "1900-01-01";
        if("".equals(today) || today == null) {
            today = LocalDate.now().toString();
        }
        LocalDate startDate = DateUtil.parseDate(startTime);
        LocalDate endDate = DateUtil.parseDate(today);
        LocalDate[] collectDate = new LocalDate[]{startDate,endDate};
        RecommendSchemeDTO dto = new RecommendSchemeDTO();
        dto.getConditions().add(Restrict.between("makeTime",collectDate));
        List<RecommendScheme> recommendSchemes = recommendSchemeSer.findByCis(dto);
        if(recommendSchemes !=null && recommendSchemes.size() >0) {
            Long recommendAward = recommendSchemes.stream().filter(p -> null != p.getPredictMoney()).mapToLong(p -> p.getPredictMoney()).sum();
            //汇总累计制定内部推荐奖方案数目
            bo.setSchemeNumber((long)recommendSchemes.size());
            //汇总累计推荐奖预算费用
            bo.setRecommendAward(recommendAward);
        }
        RecommendSchemeDTO dto1 = new RecommendSchemeDTO();
        dto1.getConditions().add(Restrict.between("makeTime",collectDate));
        dto1.getConditions().add(Restrict.eq("status",SolutionStatus.UNDERWAY));
        List<RecommendScheme> recommendSchemes1 = recommendSchemeSer.findByCis(dto1);
        if(recommendSchemes1 !=null && recommendSchemes1.size() >0){
            //汇总累计实施方案数
            bo.setEmbodimentNumber((long)recommendSchemes1.size());
        }
        SchemeImplementDTO schemeImplementDTO = new SchemeImplementDTO();
        schemeImplementDTO.getConditions().add(Restrict.between("recommendTime",collectDate));
        List<SchemeImplement> schemeImplements2 = schemeImplementSer.findByCis(schemeImplementDTO);
        if(schemeImplements2 !=null && schemeImplements2.size() > 0){
            bo.setRecommenderNumber((long)schemeImplements2.size());
        }
        SchemeImplementDTO schemeImplementDTO1 = new SchemeImplementDTO();
        schemeImplementDTO1.getConditions().add(Restrict.between("recommendTime",collectDate));
        schemeImplementDTO1.getConditions().add(Restrict.eq("isEntry",1));
        List<SchemeImplement> schemeImplements = schemeImplementSer.findByCis(schemeImplementDTO1);
        if(schemeImplements !=null && schemeImplements.size() >0){
            //汇总累计推荐入职人数
            bo.setRecommendEntry((long)schemeImplements.size());
        }
        SchemeImplementDTO schemeImplementDTO2 = new SchemeImplementDTO();
        schemeImplementDTO2.getConditions().add(Restrict.between("recommendTime",collectDate));
        schemeImplementDTO2.getConditions().add(Restrict.eq("isRegular",1));
        List<SchemeImplement> schemeImplements1 = schemeImplementSer.findByCis(schemeImplementDTO2);
        if(schemeImplements1 !=null && schemeImplements1.size() > 0){
            //汇总累计推荐转正人数
            bo.setRecommendRegular((long)schemeImplements1.size());
        }
        return bo;
    }
}