package co.istad.sokcheatmbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable=false)
    private String uuid;
    @Column(length=50)
    private String name;
    @Column(length= 8)
    private String gender;
    @Column(unique = true)
    private String oneSignalId;
    @Column(unique = true)
    private String studentIdCard;
    private Boolean isDeleted;
    private Boolean isStudent;
    @OneToMany (mappedBy="user")
    private List<UserAccount> accounts;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
