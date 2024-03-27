package co.istad.sokcheatmbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String number;
    @Column(length = 4, nullable = false,unique = true)
    private String cvv;
    private String holder;
    private LocalDate issuedAt;
    private LocalDate expiredAt;
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;
    @OneToOne(mappedBy = "card")
    private Account account;

}

