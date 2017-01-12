package com.bjike.goddess.common.jpa.service;

import com.bjike.goddess.common.api.dto.BaseDto;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.IService;
import com.bjike.goddess.common.jpa.constant.FinalCommons;
import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.common.jpa.dao.JpaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础的业务查询s实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class ServiceImpl<BE extends BaseEntity, BD extends BaseDto> extends FinalCommons implements IService<BE, BD> {

    private static final Logger CONSOLE = LoggerFactory.getLogger(ServiceImpl.class);

    @Autowired
    protected JpaRep<BE, BD> rep;
    @Autowired
    protected EntityManager entityManager;

    @Override
    public List<BE> findAll() throws SerException {
        return rep.findAll();
    }

    @Override
    public List<BE> findByPage(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        PageRequest pageRequest = JpaSpecification.getPageRequest(dto);
        return rep.findAll(JpaSpecification, pageRequest).getContent();
    }


    @Override
    public Long count(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        return rep.count(JpaSpecification);
    }

    @Override
    public BE findOne(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        List<BE> list = rep.findAll(JpaSpecification);
        return null != list && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<BE> findByCis(BD dto, Boolean pageAndSort) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        PageRequest pageRequest = JpaSpecification.getPageRequest(dto);
        return rep.findAll(JpaSpecification, pageRequest).getContent();

    }

    @Override
    public List<BE> findByCis(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        if (null != dto.getSorts() && dto.getSorts().size() > 0) { //排序
            Sort sort = null;
            Map<String, String> _sorts = dto.getSorts();
            for (Map.Entry<String, String> entry : _sorts.entrySet()) {
                Sort.Direction dct = null;
                if (entry.getValue().equalsIgnoreCase("asc")) {
                    dct = Sort.Direction.ASC;
                } else {
                    dct = Sort.Direction.DESC;
                }
                if (null == sort) {
                    sort = new Sort(dct, entry.getKey());
                } else {
                    sort = sort.and(new Sort(dct, entry.getKey()));
                }
            }
            return rep.findAll(JpaSpecification, sort);
        }

        return rep.findAll(JpaSpecification);
    }

    @Override
    public Long countByCis(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        return rep.count(JpaSpecification);
    }

    @Override
    public BE findById(String id) throws SerException {
        return rep.findById(id);
    }

    @Transactional
    @Override
    public BE save(BE entity) throws SerException {
        return rep.save(entity);
    }

    @Transactional
    @Override
    public void save(Collection<BE> entities) throws SerException {
        rep.save(entities);
    }

    @Transactional
    @Override
    public void remove(String id) throws SerException {
        rep.delete(id);
    }

    @Transactional
    @Override
    public void remove(BE entity) throws SerException {
        rep.delete(entity);
    }

    @Transactional
    @Override
    public void remove(Collection<BE> entities) {
        rep.deleteInBatch(entities);
    }

    @Transactional
    @Override
    public void update(BE entity) throws SerException {
        rep.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void update(Collection<BE> entities) throws SerException {
        Stream<BE> stream = entities.stream();
        stream.forEach(entity -> {
            rep.saveAndFlush(entity);
        });

    }

    @Override
    public Boolean exists(String id) throws SerException {
        return rep.exists(id);
    }

    private SerException repExceptionHandler(RepException e) {
        String msg = "";
        switch (e.getType()) {
            case NOT_FIND_FIELD:
                msg = "非法查询";
                break;
            case ERROR_ARGUMENTS:
                msg = "参数不匹配";
                break;
            case ERROR_PARSE_DATE:
                msg = "时间类型转换错误,字段类型不匹配";
                break;
            case ERROR_NUMBER_FORMAT:
                msg = "整形转换错误,字段类型不匹配";
                break;
            default:
                msg = e.getMessage();
        }
        return new SerException(msg);
    }

    @Override
    public String findByMaxField(String field, Class clazz) throws SerException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT MAX ( ");
        jpql.append(field);
        jpql.append(") FROM ");
        jpql.append(clazz.getSimpleName());
        Object obj = entityManager.createQuery(jpql.toString()).getSingleResult();
        return obj != null ? obj.toString() : "0";
    }

    @Override
    public String findByMinField(String field, Class clazz) throws SerException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT MIN (");
        jpql.append(field);
        jpql.append(")FROM ");
        jpql.append(clazz.getSimpleName());
        Object obj = entityManager.createQuery(jpql.toString()).getSingleResult();
        return obj != null ? obj.toString() : null;
    }

}
