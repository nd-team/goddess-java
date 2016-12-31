package test_java_service.code.entity;

import com.bjike.goddess.dbs.jpa.entity.BaseEntity;
import com.bjike.goddess.dbs.jpa.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_role")
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;//角色名
    private String description;//描述
    private Status status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "dateTime")//指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Role parent;//父角色

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     @JoinTable(name="user_role_permission",joinColumns={@JoinColumn(name="role_id",nullable = false)},
            inverseJoinColumns={@JoinColumn(name="permission_id",nullable = false)})
    private Set<Permission> permissionSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
