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
@Table(name="account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false,length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean isDisabled;
    @OneToMany (mappedBy = "accountType")
    public List<Account> accounts;

}
