package org.ruslan.user.userEntity;

import jakarta.persistence.*;
import lombok.*;
import org.ruslan.core.entity.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseEntity<Long> {

    private String email;
    private String fullName;

    @Column(name="date_of_birth")
    private LocalDate dateBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
