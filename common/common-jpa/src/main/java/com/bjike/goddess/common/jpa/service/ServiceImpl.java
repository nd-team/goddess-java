package com.bjike.goddess.common.jpa.service;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
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
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础的业务查询s实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ServiceImpl<BE extends BaseEntity, BD extends BaseDTO> extends FinalCommons implements Ser<BE, BD>, Serializable {

    private static final Logger CONSOLE = LoggerFactory.getLogger(ServiceImpl.class);
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired(required = false)
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
        if (null != list && list.size() > 1) {
            throw new SerException("find two and more data!");
        }
        return null != list && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<BE> findByCis(BD dto, Boolean page) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        PageRequest pageRequest = JpaSpecification.getPageRequest(dto);
        return rep.findAll(JpaSpecification, pageRequest).getContent();

    }

    @Override
    public List<BE> findByCis(BD dto) throws SerException {
        JpaSpecification JpaSpecification = new JpaSpecification<BE, BD>(dto);
        if (null != dto.getSorts() && dto.getSorts().size() > 0) { //排序
            Sort sort = null;
            String field;
            String order = null;
            List<String> _sorts = dto.getSorts();
            for (String sorts : _sorts) {
                String[] _sort = sorts.split("=");
                field = _sort[0];
                if (_sort.length > 1) {
                    order = _sort[1];
                }
                Sort.Direction dct;
                if (null != order && order.equalsIgnoreCase("asc")) {
                    dct = Sort.Direction.ASC;
                } else {
                    dct = Sort.Direction.DESC;
                }
                if (null == sort) {
                    sort = new Sort(dct, field);
                } else {
                    sort = sort.and(new Sort(dct, field));
                }
            }
            return rep.findAll(JpaSpecification, sort);
        }

        return rep.findAll(JpaSpecification);
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


    @Override
    public String findByMaxField(String field, Class clazz) throws SerException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT MAX ( ");
        jpql.append(field);
        jpql.append(") FROM ");
        jpql.append(clazz.getSimpleName());
        Object obj = entityManager.createQuery(jpql.toString()).getSingleResult();
        return obj != null ? obj.toString() : null;
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

    @Override
    public <T> List<T> findBySql(String sql, Class clazz, String[] fields) throws SerException {

        List<Field> all_fields = new ArrayList<>(); //源类属性列表
        Class temp_clazz = clazz;
        while (null != temp_clazz) { //数据源类所有属性（包括父类）
            all_fields.addAll(Arrays.asList(temp_clazz.getDeclaredFields()));
            temp_clazz = temp_clazz.getSuperclass();
            if (Object.class.equals(temp_clazz) || null == temp_clazz) {
                break;
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object> resultList = nativeQuery.getResultList();
        List<T> list = new ArrayList<>(resultList.size());

        //解析查询结果
        try {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] arr_obj = null;
                if (fields.length > 1) {
                    arr_obj = (Object[]) resultList.get(i);
                } else {
                    arr_obj = new Object[]{resultList.get(i)};
                }
                Object obj = clazz.newInstance();
                for (int j = 0; j < fields.length; j++) {
                    for (Field field : all_fields) {
                        if (field.getName().equals(fields[j])) {
                            field.setAccessible(true);
                            if (!field.getType().isEnum()) { //忽略枚举类型
                                field.set(obj, convertDataType(field.getType().getSimpleName(), arr_obj[j]));
                            } else {
                                Field[] enumFields = field.getType().getFields();
                                for (int k = 0; k < enumFields.length; k++) {
                                    int val = Integer.parseInt(arr_obj[j].toString());
                                    String name = enumFields[k].getName();
                                    if (val == k) {
                                        field.set(obj, field.getType().getField(name).get(name));
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                list.add((T) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void executeSql(String sql) throws SerException {
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public String getTableName(Class clazz) throws SerException {
        try {
            if (clazz.isAnnotationPresent(Table.class)) {
                Annotation annotation = clazz.getAnnotation(Table.class);
                Method[] methods = annotation.annotationType().getMethods();
                for (Method method : methods) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    if ("name".equals(method.getName())) {
                        Object invoke = method.invoke(annotation);
                        return invoke.toString();
                    }
                }
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

        throw new SerException("解析表名错误!");
    }

    /**
     * 数据库类型转换
     *
     * @param obj
     * @return
     */
    private Object convertDataType(String type, Object obj) {
        if (null != obj) {
            String val = obj.toString();
            switch (type) {
                case "Float":
                    obj = Float.parseFloat(val);
                    break;
                case "Double":
                    obj = Double.parseDouble(val);
                    break;
                case "Long":
                    obj = Long.parseLong(val);
                    break;

                case "BigDecimal":
                    obj = Double.parseDouble(val);
                    break;
                case "Integer":
                    obj = Integer.parseInt(val);
                    break;
                case "LocalDateTime":
                    obj = LocalDateTime.parse(val, DATE_TIME);
                    break;
                case "LocalTime":
                    obj = LocalDateTime.parse(val, TIME);
                    break;
                case "LocalDate":
                    obj = LocalDate.parse(val, DATE);
                    break;

            }
        }
        return obj;
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

}
