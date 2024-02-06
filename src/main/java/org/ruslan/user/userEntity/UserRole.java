package org.ruslan.user.userEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ruslan.core.entity.BaseEntity;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
 public class UserRole extends BaseEntity<Long> {
    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;
}
