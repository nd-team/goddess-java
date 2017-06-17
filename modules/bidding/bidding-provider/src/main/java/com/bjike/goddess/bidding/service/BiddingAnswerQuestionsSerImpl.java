package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.excel.BiddingAnswerQuestionsExport;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private CusPermissionSer cusPermissionSer;

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
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<BiddingAnswerQuestions> biddingAnswerQuestionss = super.findByCis(biddingAnswerQuestionsDTO, true);
        List<BiddingAnswerQuestionsBO> biddingAnswerQuestionsBOS = BeanTransform.copyProperties(biddingAnswerQuestionss, BiddingAnswerQuestionsBO.class);
        return biddingAnswerQuestionsBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO insertBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        checkDate(biddingAnswerQuestionsTO);
        BiddingAnswerQuestions biddingAnswerQuestions = BeanTransform.copyProperties(biddingAnswerQuestionsTO, BiddingAnswerQuestions.class, true);
        biddingAnswerQuestions.setCreateTime(LocalDateTime.now());
        super.save(biddingAnswerQuestions);
        return BeanTransform.copyProperties(biddingAnswerQuestions, BiddingAnswerQuestionsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO editBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
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
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
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