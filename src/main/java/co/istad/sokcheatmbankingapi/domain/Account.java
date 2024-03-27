package co.istad.sokcheatmbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 9)
    private String actNo;
    @Column(unique = true, nullable =true, length = 100)
    private String actName;

    private BigDecimal transferLimit;
    @ManyToOne
    private AccountType accountType;
    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccountList;
    @OneToOne
    private Card card;

}
