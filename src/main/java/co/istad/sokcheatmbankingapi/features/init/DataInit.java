package co.istad.sokcheatmbankingapi.features.init;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.domain.Role;
import co.istad.sokcheatmbankingapi.features.account.account_type.AccountTypeRepository;
import co.istad.sokcheatmbankingapi.features.account.card.card_type.CardTypeRepository;
import co.istad.sokcheatmbankingapi.features.user.RoleRespository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRespository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CardTypeRepository cardTypeRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role admin = new Role();
            admin.setName("ADMIN");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            roleRepository.saveAll(List.of(user, customer, staff, admin));
        }

        if (accountTypeRepository.count() < 1) {
            AccountType payroll = new AccountType();
            payroll.setAlias("payroll");
            payroll.setName("PAYROLL"); // Set the name attribute

            AccountType saving = new AccountType();
            saving.setAlias("saving");
            saving.setName("SAVING"); // Set the name attribute

            AccountType card = new AccountType();
            card.setAlias("card");
            card.setName("CARD"); // Set the name attribute

            accountTypeRepository.saveAll(List.of(payroll, saving, card));
        }
        if(cardTypeRepository.count()<1){
            CardType visa = new CardType();
            visa.setName("VISA");

            CardType masterCard = new CardType();
            masterCard.setName("MASTER CARD");

            cardTypeRepository.saveAll(List.of(visa,masterCard));
        }
    }
}
