package co.istad.sokcheatmbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private LocalTime transactionAt;
    @ManyToOne
    @JoinColumn(name="transaction_id")
    private Transaction transactionId;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private User senderId;
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiverId;

}
