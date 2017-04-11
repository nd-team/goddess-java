package com.bjike.goddess.common.jpa.dao;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.type.RepExceptionType;
import com.bjike.goddess.common.api.type.RestrictionType;
import com.bjike.goddess.common.jpa.utils.PrimitiveUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Method;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa 高级查询，高级查询条件拼装分页，排序等]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class JpaSpecification<BE extends BaseEntity, BD extends BaseDTO> implements Specification<BE> {

    private static final Pattern PATTERN = Pattern.compile("\\[[a-zA-Z0-9]+\\]");

    private BD dto;

    public JpaSpecification(BD dto) {
        this.dto = dto;
    }


    @Override
    public Predicate toPredicate(Root<BE> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        try {
            Predicate predicate = initPredicates(dto, root, cb);
            return query.where(predicate).getRestriction();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     * 连表查询支持单属性（model）查询，set，list集合
     *
     * @param dto
     * @param root
     * @param cb
     * @return
     * @throws RepException
     */
    private Predicate initPredicates(BD dto, Root<BE> root, CriteriaBuilder cb) throws RepException {
        List<Predicate> preList = new ArrayList<>(0); //条件列表
        List<Condition> conditions = dto.getConditions();//避免条件列表为空
        List<Predicate> or_preList = new ArrayList<>(); //or 条件列表
        Class clazz = null;
        Join<BE, Object> join = null;
        if (null != conditions) {
            try {
                for (Condition model : conditions) {
                    Boolean isOrPre = false; //是否为or查询
                    Predicate predicate = null;
                    if (null != model.getValue()) {
                        if (model.getValue() instanceof Boolean) {
                            model.setValue(Boolean.TRUE == model.getValue() ? "0" : "1");
                        }
                        clazz = PrimitiveUtil.switchType(model.getValue()); //得到数据类型

                    } else {
                        clazz = String.class;
                    }
                    String field = model.getField(); //字段

                    RestrictionType type = model.getRestrict();
                    String[] fields = model.getField().split("\\.");
                    join = handlerJoinTable(root, fields);  //是否有连接查询
                    Method method = handlerMethod(cb, model);//获得反射调用方法
                    Boolean existJoin = (null != join);
                    if (existJoin) {
                        field = fields[fields.length - 1]; //有连接查询取最后的分割字段
                    }
                    switch (type) {
                        case LIKE:
                            if (existJoin) {
                                predicate = cb.like(join.get(field).as(clazz), "%" + model.getValue() + "%");
                            } else {
                                predicate = cb.like(root.get(field).as(clazz), "%" + model.getValue() + "%");
                            }
                            break;
                        case ISNULL:
                            if (existJoin) {
                                predicate = cb.isNull(join.get(field).as(clazz));
                            } else {
                                predicate = cb.isNull(root.get(field).as(clazz));
                            }
                            break;
                        case ISNOTNULL:
                            if (existJoin) {
                                predicate = cb.isNotNull(join.get(field).as(clazz));
                            } else {
                                predicate = cb.isNotNull(root.get(field).as(clazz));
                            }
                            break;
                        case OR:
                            isOrPre = true;
                            if (existJoin) {
                                predicate = cb.or(cb.equal(join.get(field).as(clazz), model.getValue()));
                            } else {
                                predicate = cb.or(cb.equal(root.get(field).as(clazz), model.getValue()));
                            }
                            break;
                        default:
                            Object[] values = PrimitiveUtil.convertValuesByType(model.getValue());
                            if (type == RestrictionType.IN) {
                                if (existJoin) {
                                    predicate = (Predicate) method.invoke(cb, join.get(field).as(clazz), values);
                                } else {
                                    predicate = (Predicate) method.invoke(cb, root.get(field).as(clazz), values);
                                }
                            } else {
                                if (existJoin) {
                                    predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, join.get(field).as(clazz)));
                                } else {
                                    predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, root.get(field).as(clazz)));
                                }
                            }
                    }
                    if (null != predicate) {
                        if (!isOrPre) {
                            preList.add(predicate);
                        } else {
                            or_preList.add(predicate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                exceptionHandler(e);

            }

            Predicate[] predicates = new Predicate[preList.size()];
            Predicate predicate = cb.and(preList.toArray(predicates));
            if (or_preList.size() > 0) { //处理 or 查询
                or_preList.add(0, predicate);
                Predicate[] or_pres = new Predicate[or_preList.size()];
                predicate = cb.or(or_preList.toArray(or_pres));
            }
            return predicate;
        } else {
            return null;
        }

    }

    private Method handlerMethod(CriteriaBuilder cb, Condition condition) {
        Method[] methods = cb.getClass().getDeclaredMethods();
        Method method = null;

        for (Method m : methods) {
            Class<?>[] types = m.getParameterTypes();
            String name = RestrictionType.getRestrict(condition.getRestrict());
            if (m.getName().equals(name) &&
                    types[types.length - 1] != Expression.class) {
                method = m;
                break;
            }
        }
        return method;
    }


    /**
     * 左连接查询
     *
     * @param root
     * @return
     */
    private Join<BE, Object> handlerJoinTable(Root<BE> root, String[] fields) {
        int fields_length = fields.length - 1; //忽略最后的属性查询字段 如user.userinfo.email 只取user.userinfo
        Join<BE, Object> join = null;
        if (fields_length >= 1) {  //存在连接查询
            for (int i = 0; i < fields_length; i++) {
                String entityName = fields[i];
                String last = entityName.substring(entityName.length() - 3, entityName.length());
                if ("Set".equals(last)) {
                    join = root.joinSet(entityName, JoinType.LEFT);
                } else if ("List".equals(last)) {
                    join = root.joinList(entityName, JoinType.LEFT);
                } else {
                    join = root.join(entityName, JoinType.LEFT);
                }
            }

            return join;
        }
        return null;
    }

    /**
     * 查询异常处理
     *
     * @param e
     * @return
     */
    private RepException exceptionHandler(Exception e) throws RepException {
        String msg = "";
        RepExceptionType type = RepExceptionType.UNDEFINE;

        if (e instanceof IllegalArgumentException) {
            Matcher matcher = PATTERN.matcher(e.getMessage());
            boolean isFind = matcher.find();
            if (isFind) { //字段不匹配
                msg = StringUtils.substring(matcher.group(), 1, -1);
                type = RepExceptionType.NOT_FIND_FIELD;
            } else if (e.getMessage().indexOf("wrong number of arguments") != -1) { //其他错误
                msg = "wrong number of arguments";
                type = RepExceptionType.ERROR_ARGUMENTS;
            } else if (e instanceof NumberFormatException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_NUMBER_FORMAT;
            }
        } else {
            if (e instanceof DateTimeParseException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_PARSE_DATE;
            } else {
                msg = e.getMessage();
                type = RepExceptionType.UNDEFINE;
            }

        }
        throw new RepException(type, msg);
    }


    /**
     * 分页及排序
     *
     * @param dto
     * @return
     */
    public PageRequest getPageRequest(BD dto) {
        PageRequest pageRequest;
        Sort sort = null;
        List<String> _sorts = dto.getSorts();
        if (_sorts != null && _sorts.size() > 0) {
            String field;
            String order = null;
            for (String sorts : _sorts) {
                String[] _sort = sorts.split("=");
                if (_sort.length > 1) {
                    order = _sort[1];
                }
                field = _sort[0];
                Sort.Direction dct = null;
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
            pageRequest = new PageRequest(dto.getPage(), dto.getLimit(), sort); //分页带排序
        } else {
            pageRequest = new PageRequest(dto.getPage(), dto.getLimit()); //分页不带排序
        }
        return pageRequest;
    }


}
