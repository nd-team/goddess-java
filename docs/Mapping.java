/**
 * @Author: [liguiqin]
 * @Date: [2016-12-23 09:51]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MappingDemo {

    /**
     * 相关属性说明：
     * 1：optional 是定义该关联类是否必须存在,值为false 时，关联类双方都必须存在(inner join) true是为left join
     * 2：CascadeType.PERSIST（级联新建）、CascadeType.REMOVE（级联删除）、CascadeType.REFRESH（级联刷新）、CascadeType.MERGE（级联更新）中选择一个或多个。还有一个选择是使用CascadeType.ALL，表示选择全部四项。    @ManyToOne(cascade = {CascadeType.ALL})
     * 3：fetch = FetchType.EAGER/LAZY 懒加载或者正常加载（建议用LAZY）
     * 4：@JoinColumn(name="xxx_id")改注解会在该类对应表上生成一个关联主表的外键
     * 5：@Column(unique = true)(约束唯一) 默认为false
     * 6：@Column(columnDefinition = "TINYINT(1)")//指定数据库类型
     * 7：@OrderBy(value = "seq ASC")排序指定字段
     * 8：@Column（nullable = false）该字段不能为空 默认可以为空true
     */


    /** one-to-one 配置
     *@Entity
     *@Table(name = "user")
     * public class User{
     *      mappedBy = "user" User 为主控方，维持userInfo（被控方关系）
     *      private UserInfo userInfo;
     *      get set ...
     *      }
     *
     *@Entity
     *@Table(name = "user_info")
     * public class UserInfo{
     *       @OneToOne(optional = false, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
     *       @JoinColumn(name="user_id",unique = true)
     *       @JSONField(serialize = false)
     *       private User user;
     *       get set ...
     *      }
     * 除了 many to one 其他关联关系都有mappedBy属性，使用mappedBy的一端为主控方，
     * 主控方(User)来维持对象关系，如果涉及到大量数据的查询，不建议这样使用，因为当查询主表User时
     * 是通过mappedBy去找被控方（UserInfo）的外建查询的（因为UserInfo没有作为外键在User主表上），所有每查询
     *一个用户就会查询一次UserInfo,当查询n个用户时就会发出n条sql
     *(建议单独维护UserInfo或者把外键反转，把userInfo的主键放在User表)
     */


    /************************************************************************/


    /**many-to-one配置
     * 多对一配置 group_id作为外键关联映射表的主键列关联
     * ManyToOne 指定 many(group) 一方是不能独立存在的，否则存在孤儿数据
     *@Entity
     *@Table(name = "user")
     * public class User{
     *     @ManyToOne(cascade = {CascadeType.ALL,fetch = FetchType.LAZY})
     *     @JoinColumn(name = "group_id")
     *    private  Group group;
     *    get set ...
     *    }
     *
     **@Entity
     *@Table(name = "group")
     *  public Class  Group {
     *      .....
     *  }
     *
     *
     */

    /************************************************************************/


    /**
     * one-to-many配置
     * 一般由多的一方（UserInterest）维护关系
     *@Entity
     *@Table(name = "user")
     * public class User{
     *       @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
     *       private Set<UserInterest> interestSet;(一定以Set结束的命名为规范，否则做不了连接条件查询)
     *       get set ...
     *  }
     *
     */

    /************************************************************************/

    /**
     * many-to-many配 {1.通过many-to-many注解}
     * 一般由双方各自维护关系
     * 注：@JoinTable(name="role_permission"）会生成一张含有各自主键id的中间表（JoinColumn为外键id名）
     *
     * @Entity
     *@Table(name = "role")
     * public class Role{
     *      @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     *      @JoinTable(name="role_permission",joinColumns={@JoinColumn(name="role_id",nullable = false)},
     *      inverseJoinColumns={@JoinColumn(name="permission_id",nullable = false)})
     *      private Set<Permission> permissionSet;
     *      get set ...
     * }
     *
     *@Entity
     *@Table(name = "permission")
     * public class Permission{
     *     @ManyToMany(mappedBy="permissionSet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     *      private Set<Role> roleSet ;
     *      get set ...
     * }
     *
     */


    /**
     * many-to-many配置 {2.通过双向多对一，一对多注解many-to-one,one-to-many}
     * 区别：此从方式是自己建立一张中间表进行维护，生成多对多关系，查询上面稍有区别
     * 至于使用那种方式，视业务而定(第一种查询比较方便)
     * 一般由双方各自维护关系
     **@Entity
     *@Table(name = "role_permission")
     * public class RolePermission{ 中间表
     *   @ManyToOne(cascade = {CascadeType.ALL})
     *   @JoinColumn(name = "rid")
     *   private Role role;
     *
     *   @ManyToOne(cascade = {CascadeType.ALL})
     *    @JoinColumn(name = "pid")
     *    private Permission permission;
     *
     *    }
     *@Entity
     *@Table(name = "role")
     *   public class Role{
     *      @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
     *      private Set<RolePermission> rolePermissionSet;
     *   }
     *
     * @Entity
     *@Table(name = "permission")
     *   public class Permission{
     *      @OneToMany(mappedBy = "permission", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
     *      private Set<RolePermission> rolePermissionSet;
     *   }
     */


}
