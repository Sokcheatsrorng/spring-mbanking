package co.istad.sokcheatmbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="role_authorities")
public class RoleAuthority {
    @Id
    private Integer id;
    @Column(unique = true, nullable = false)
    private Integer authority_id;
    @ManyToOne
    private Role role;

}
