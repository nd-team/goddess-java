package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.entity.SurveyActualize;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研实施记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyActualizeSerImpl extends ServiceImpl<SurveyActualize, SurveyActualizeDTO> implements SurveyActualizeSer {

    @Autowired
    private SurveyPlanSer surveyPlanSer;
    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;
    @Autowired
    private SurveyQuestionnaireUserSer surveyQuestionnaireUserSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    private SurveyActualizeBO transformBO(SurveyActualize entity) throws SerException {
        SurveyActualizeBO bo = BeanTransform.copyProperties(surveyPlanSer.findBOById(entity.getPlan().getId()), SurveyActualizeBO.class);
        bo.setPlanId(entity.getPlan().getId());
        bo.setStart(bo.getStartTime());
        bo.setEnd(bo.getEndTime());
        bo.setFinish(bo.getFinishTime());
        bo = BeanTransform.copyProperties(entity, SurveyActualizeBO.class);
        return bo;
    }

    private List<SurveyActualizeBO> transformBOList(List<SurveyActualize> list) throws SerException {
        List<SurveyActualizeBO> bos = new ArrayList<>(0);
        for (SurveyActualize entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        SurveyActualize entity = BeanTransform.copyProperties(to, SurveyActualize.class, true);
        entity.setPlan(surveyPlanSer.findById(to.getPlanId()));
        if (null == entity.getPlan())
            throw new SerException("调研计划不存在,无法保存");
        entity.setStartTime(LocalDateTime.now());
        entity.setSurvey(SurveyStatus.UNDERWAY);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        SurveyActualize entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setPlan(surveyPlanSer.findById(to.getPlanId()));
        if (null == entity.getPlan())
            throw new SerException("调研计划不存在,无法保存");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO delete(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if (surveyQuestionnaireSer.findByActualize(entity.getId()).size() != 0 || surveyQuestionnaireUserSer.findByActualize(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyActualizeBO over(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        entity.setSurvey(SurveyStatus.FINISH);
        entity.setEndTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyActualizeBO> maps(SurveyActualizeDTO dto) throws SerException {
        dto.getSorts().add("startTime=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public SurveyActualizeBO getById(String id) throws SerException {
        SurveyActualize entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        SurveyActualizeDTO dto = new SurveyActualizeDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getName() throws SerException {
        List<SurveyActualize> list = super.findAll();
        List<String> stringList = new ArrayList<>();
        if(null !=  list && list.size() > 0){
            for(SurveyActualize entity : list){
                stringList.add(entity.getQuestionnaire());
            }
        }
        return null;
    }

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
            flag = cusPermissionSer.busCusPermission("2");
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

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
}