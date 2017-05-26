package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.entity.YearIndexSet;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class PositionIndexSetSerImpl extends ServiceImpl<PositionIndexSet, PositionIndexSetDTO> implements PositionIndexSetSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartMonIndexSetSer departMonIndexSetSer;
    @Autowired
    private DepartYearIndexSetSer departYearIndexSetSer;
    @Autowired
    private YearIndexSetSer yearIndexSetSer;

    @Override
    public Long countPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        Long count = super.count(positionIndexSetDTO);
        return count;
    }

    @Override
    public PositionIndexSetBO getOneById(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(id);
        PositionIndexSetBO bo = BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
        return bo;
    }

    @Override
    public List<PositionIndexSetBO> listPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }

        List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
        List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
        return listBO;
    }

    @Override
    public PositionIndexSetBO addPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        PositionIndexSet temp = BeanTransform.copyProperties(positionIndexSetTO, PositionIndexSet.class);
        //添加
        temp.setWeightSum(0d);
        temp.setWritePerson(userBO.getUsername());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setPosionIndexPersion(userBO.getUsername());
        temp.setPosionIndexTime(LocalDate.now());
        temp.setSeperateComeStatus(null);
        temp.setDepartMonIndexSetId(null);
        super.save(temp);
        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    @Override
    public PositionIndexSetBO editPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        if (StringUtils.isBlank(positionIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(positionIndexSetTO.getId());
        Double oldComplete = temp.getComplete();

        temp.setIndexName(positionIndexSetTO.getIndexName());
        temp.setYear(positionIndexSetTO.getYear());
        temp.setMonth(positionIndexSetTO.getMonth());
        temp.setIndexType(positionIndexSetTO.getIndexType());
        temp.setDimension(positionIndexSetTO.getDimension());
        temp.setDescribtion(positionIndexSetTO.getDescribtion());
        temp.setDepartment(positionIndexSetTO.getDepartment());
        temp.setDepartYearWeight(positionIndexSetTO.getDepartYearWeight());
        temp.setDepartYearWager(positionIndexSetTO.getDepartYearWager());
        temp.setPosition(positionIndexSetTO.getPosition());
        temp.setPositioner(positionIndexSetTO.getPositioner());
        temp.setWeight(positionIndexSetTO.getWeight());
        temp.setWeightSum(positionIndexSetTO.getWeightSum());
        temp.setTarget(positionIndexSetTO.getTarget());
        temp.setWager(positionIndexSetTO.getWager());
        temp.setComplete(positionIndexSetTO.getComplete());
        temp.setExamWay(positionIndexSetTO.getExamWay());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setExamDepart(positionIndexSetTO.getExamDepart());
        temp.setDataOrigin(positionIndexSetTO.getDataOrigin());
        temp.setExamDuring(positionIndexSetTO.getExamDuring());
        temp.setModifyTime(LocalDateTime.now());

        //修改上面的完成值
        if (temp.getSeperateComeStatus().equals(SeperateComeStatus.DEPARTMONTH)) {
            //TODO:未做
            //修改部门月指标完成值
            //修改部门年指标完成值
            //修改年指标完成值
            editDepartMonComplete(oldComplete, temp);


        }

        super.update(temp);

        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    private void editDepartMonComplete(Double oldComplete, PositionIndexSet temp) throws SerException {
        //新的岗位完成值
        Double newComplete = temp.getComplete();
        //部门月度id
        String monId = temp.getDepartMonIndexSetId();
        DepartMonIndexSet dMon = departMonIndexSetSer.findById(monId);
        //部门月度旧完成值
        Double oldMonComplete = dMon.getComplete();

        dMon.setComplete(dMon.getComplete() - oldComplete + newComplete);
        dMon.setWhetherStandar(dMon.getComplete() > dMon.getTarget() ? "是" : "否");
        dMon.setStandardRate(dMon.getComplete() / dMon.getWager());
        dMon.setExamScore(dMon.getStandardRate() * dMon.getWeight());
        dMon.setModifyTime(LocalDateTime.now());

        //修改部门年度完成指标
        editDepartYearComplete(oldMonComplete, dMon);

        departMonIndexSetSer.update(dMon);
    }

    private void editDepartYearComplete(Double oldMonComplete, DepartMonIndexSet dMon) throws SerException {
        //新的部门月度完成值
        Double newComplete = dMon.getComplete();
        //部门年度id
        String yearId = dMon.getDepartYearIndexSetId();
        DepartYearIndexSet dYear = departYearIndexSetSer.findById(yearId);
        //部门月度旧完成值
        Double oldYearComplete = dYear.getComplete();

        dYear.setComplete(dYear.getComplete() - oldMonComplete + newComplete);
        dYear.setWhetherStandar(dYear.getComplete() > dYear.getTarget() ? "是" : "否");
        dYear.setStandardRate(dYear.getComplete() / dYear.getWager());
        dYear.setExamScore(dYear.getStandardRate() * dYear.getDepartYearWeight());
        dYear.setModifyTime(LocalDateTime.now());

        //修改年指标完成值
        editYearComplete(oldYearComplete, dYear);

        departYearIndexSetSer.update(dYear);

    }

    private void editYearComplete(Double oldYearComplete, DepartYearIndexSet dYear) throws SerException {
        //新的部门年度完成值
        Double newComplete = dYear.getComplete();
        //年度id
        String yearId = dYear.getYearIndexSetId();
        YearIndexSet year = yearIndexSetSer.findById(yearId);

        year.setComplete(year.getComplete() - oldYearComplete + newComplete);
        year.setModifyTime(LocalDateTime.now());

        yearIndexSetSer.update(year);

    }

    @Override

    public void deletePositionIndexSet(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }


    @Override
    public Long countNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {

        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        positionIndexSetDTO.getConditions().add(Restrict.eq("year", year));
        positionIndexSetDTO.getConditions().add(Restrict.eq("month", month));
        Long count = super.count(positionIndexSetDTO);
        return count;
    }


    @Override
    public List<PositionIndexSetBO> listNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        positionIndexSetDTO.getConditions().add(Restrict.eq("year", year));
        positionIndexSetDTO.getConditions().add(Restrict.eq("month", month));

        List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
        List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
        return listBO;
    }


    @Override
    public Long countSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        //当前责任人只可以查看自己的
        positionIndexSetDTO.getConditions().add(Restrict.eq("positioner", userBO.getUsername()));
        Long count = super.count(positionIndexSetDTO);
        return count;
    }

    @Override
    public List<PositionIndexSetBO> listSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (StringUtils.isNotBlank(positionIndexSetDTO.getIndexName())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("indexName", positionIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getYear())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("year", positionIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getMonth())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("month", positionIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(positionIndexSetDTO.getPositioner())) {
            positionIndexSetDTO.getConditions().add(Restrict.like("positioner", positionIndexSetDTO.getPositioner()));
        }
        //当前责任人只可以查看自己的
        positionIndexSetDTO.getConditions().add(Restrict.eq("positioner", userBO.getUsername()));

        List<PositionIndexSet> list = super.findByCis(positionIndexSetDTO, true);
        List<PositionIndexSetBO> listBO = BeanTransform.copyProperties(list, PositionIndexSetBO.class);
        return listBO;
    }

    @Override
    public PositionIndexSetBO addSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        PositionIndexSet temp = BeanTransform.copyProperties(positionIndexSetTO, PositionIndexSet.class);
        //添加
        temp.setPositioner(userBO.getUsername());
        temp.setWeightSum(0d);
        temp.setWritePerson(userBO.getUsername());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setPosionIndexPersion(userBO.getUsername());
        temp.setPosionIndexTime(LocalDate.now());
        temp.setSeperateComeStatus(null);
        temp.setDepartMonIndexSetId(null);
        super.save(temp);
        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

    @Override
    public PositionIndexSetBO editSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        if (StringUtils.isBlank(positionIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        PositionIndexSet temp = super.findById(positionIndexSetTO.getId());
        Double oldComplete = temp.getComplete();


        temp.setIndexName(positionIndexSetTO.getIndexName());
        temp.setYear(positionIndexSetTO.getYear());
        temp.setMonth(positionIndexSetTO.getMonth());
        temp.setIndexType(positionIndexSetTO.getIndexType());
        temp.setDimension(positionIndexSetTO.getDimension());
        temp.setDescribtion(positionIndexSetTO.getDescribtion());
        temp.setDepartment(positionIndexSetTO.getDepartment());
        temp.setDepartYearWeight(positionIndexSetTO.getDepartYearWeight());
        temp.setDepartYearWager(positionIndexSetTO.getDepartYearWager());
        temp.setPosition(positionIndexSetTO.getPosition());
        temp.setWeight(positionIndexSetTO.getWeight());
        temp.setWeightSum(positionIndexSetTO.getWeightSum());
        temp.setTarget(positionIndexSetTO.getTarget());
        temp.setWager(positionIndexSetTO.getWager());
        temp.setComplete(positionIndexSetTO.getComplete());
        temp.setExamWay(positionIndexSetTO.getExamWay());
        temp.setWhetherStandar(positionIndexSetTO.getComplete() > positionIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(positionIndexSetTO.getComplete() / positionIndexSetTO.getWager());
        temp.setExamScore(positionIndexSetTO.getComplete() * positionIndexSetTO.getWeight());
        temp.setExamDepart(positionIndexSetTO.getExamDepart());
        temp.setDataOrigin(positionIndexSetTO.getDataOrigin());
        temp.setExamDuring(positionIndexSetTO.getExamDuring());
        temp.setModifyTime(LocalDateTime.now());


        //修改上面的完成值
        if (temp.getSeperateComeStatus().equals(SeperateComeStatus.DEPARTMONTH)) {
            //TODO:未做
            //修改部门月指标完成值
            //修改部门年指标完成值
            //修改年指标完成值
            editDepartMonComplete(oldComplete, temp);


        }

        super.update(temp);

        return BeanTransform.copyProperties(temp, PositionIndexSetBO.class);
    }

}