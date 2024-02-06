package org.ruslan.user.userEntity;


import jakarta.persistence.*;
import lombok.*;
import org.ruslan.core.entity.BaseEntity;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "fpms")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> implements Serializable {


    @Size(min = 3, message = "Not less than 3 characters")
    @Column(name = "username")
    private String username;

    @Size(min = 6, message = "Not less than 6 characters")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> listRoles;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
