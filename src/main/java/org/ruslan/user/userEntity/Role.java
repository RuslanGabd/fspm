package org.ruslan.user.userEntity;


import jakarta.persistence.*;
import lombok.*;
import org.ruslan.core.entity.BaseEntity;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "role")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {
    @Column(name = "role_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "listRoles")
    private List<User> listUser;
}
