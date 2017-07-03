package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProjectAmountBO;
import com.bjike.goddess.businessevaluate.bo.ProjectAmountInfoBO;
import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProjectAmount;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.enums.GuideAddrStatus;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.businessevaluate.to.ProjectAmountTO;
import com.bjike.goddess.common.api.dto.Restrict;
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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目金额业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class ProjectAmountSerImpl extends ServiceImpl<ProjectAmount, ProjectAmountDTO> implements ProjectAmountSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;
    @Autowired
    private ProjectCostSer projectCostSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountInfoBO findInfoById(String id) throws SerException {

        getCusPermission();

        ProjectAmountInfoBO bo = new ProjectAmountInfoBO();
        EvaluateProjectInfo info = evaluateProjectInfoSer.findById(id);
        if(info==null){
            throw new SerException("项目不存在");
        }
        bo.setCost(info.getCost());
        bo.setManageFee(info.getManageCost());
        bo.setTaxes(info.getTaxes());

        //查询设置费用
        ProjectCostDTO projectCostDTO = new ProjectCostDTO();
        projectCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        ProjectCost projectCost = projectCostSer.findOne(projectCostDTO);
        Double fee = 0.0;
        if (projectCost != null) {
            fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission();
        }

        bo.setFee(fee);
        //利润 = 项目总金额 - 成本 -管理费 -税金 - 费用
        Double profit = info.getTotalAmount() - info.getCost() - info.getManageCost() - info.getTaxes() - fee;
        bo.setProfit(profit);
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountBO insertModel(ProjectAmountTO to) throws SerException {

        getCusPermission();

        ProjectAmount model = BeanTransform.copyProperties(to, ProjectAmount.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectAmountBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountBO updateModel(ProjectAmountTO to) throws SerException {

        getCusPermission();

        if (!StringUtils.isEmpty(to.getId())) {
            ProjectAmount model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, ProjectAmountBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectAmountBO> pageList(ProjectAmountDTO dto) throws SerException {

        getCusPermission();

        dto.getSorts().add("createTime=desc");
        List<ProjectAmount> list = super.findByPage(dto);
        List<ProjectAmountBO> boList = BeanTransform.copyProperties(list, ProjectAmountBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            //设置差额
            for (ProjectAmountBO bo : boList) {
                bo.setCostSubtract(bo.getCost() - bo.getBudgetCost());
                bo.setFeeSubtract(bo.getFee() - bo.getBudgetFee());
                bo.setManageFeeSubtract(bo.getManageFee() - bo.getBudgetManageFee());
                bo.setTaxesSubtract(bo.getTaxes() - bo.getBudgetTaxes());
                bo.setProfitSubtract(bo.getProfit() - bo.getBudgetProfit());
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
                if (info != null) {
                    bo.setArea(info.getArea());
                    bo.setProject(info.getProject());
                    bo.setStartTime(info.getStartTime().toString());
                    bo.setEndTime(info.getEndTime().toString());
                }
            }
        }
        return boList;
    }

    @Override
    public Boolean sonPermission() throws SerException {
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
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = sonPermission();
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
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
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

    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该功能只有商务部可操作，您的帐号尚无权限");
        }
    }

}