package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.excel.BiddingAnswerQuestionsExport;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 投标答疑问题记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.913 ]
 * @Description: [ 投标答疑问题记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingAnswerQuestionsSerImpl extends ServiceImpl<BiddingAnswerQuestions, BiddingAnswerQuestionsDTO> implements BiddingAnswerQuestionsSer {
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

    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        try {
            DateUtil.parseDate(biddingAnswerQuestionsTO.getOfficeHour());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        biddingAnswerQuestionsDTO.getSorts().add("createTime=desc");
        Long count = super.count(biddingAnswerQuestionsDTO);
        return count;
    }

    @Override
    public BiddingAnswerQuestionsBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BiddingAnswerQuestions biddingAnswerQuestions = super.findById(id);
        return BeanTransform.copyProperties(biddingAnswerQuestions, BiddingAnswerQuestionsBO.class);
    }

    @Override
    public List<BiddingAnswerQuestionsBO> findListBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        checkSeeIdentity();
        biddingAnswerQuestionsDTO.getSorts().add("createTime=desc");
        List<BiddingAnswerQuestions> biddingAnswerQuestionss = super.findByCis(biddingAnswerQuestionsDTO, true);
        List<BiddingAnswerQuestionsBO> biddingAnswerQuestionsBOS = BeanTransform.copyProperties(biddingAnswerQuestionss, BiddingAnswerQuestionsBO.class);
        return biddingAnswerQuestionsBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO insertBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        checkAddIdentity();
        checkDate(biddingAnswerQuestionsTO);
        BiddingAnswerQuestions biddingAnswerQuestions = BeanTransform.copyProperties(biddingAnswerQuestionsTO, BiddingAnswerQuestions.class, true);
        biddingAnswerQuestions.setCreateTime(LocalDateTime.now());
        super.save(biddingAnswerQuestions);
        return BeanTransform.copyProperties(biddingAnswerQuestions, BiddingAnswerQuestionsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO editBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(biddingAnswerQuestionsTO.getId())) {
            throw new SerException("id不能为空");
        }
        BiddingAnswerQuestions biddingAnswerQuestions = super.findById(biddingAnswerQuestionsTO.getId());
        BeanTransform.copyProperties(biddingAnswerQuestionsTO, biddingAnswerQuestions, true);
        checkDate(biddingAnswerQuestionsTO);
        biddingAnswerQuestions.setModifyTime(LocalDateTime.now());
        super.update(biddingAnswerQuestions);
        return BeanTransform.copyProperties(biddingAnswerQuestionsTO, BiddingAnswerQuestionsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingAnswerQuestions(String id) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public byte[] exportExcel(BiddingAnswerQuestionsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        List<BiddingAnswerQuestions> list = super.findByCis(dto);
        List<BiddingAnswerQuestionsExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            BiddingAnswerQuestionsExport export = BeanTransform.copyProperties(str, BiddingAnswerQuestionsExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

}