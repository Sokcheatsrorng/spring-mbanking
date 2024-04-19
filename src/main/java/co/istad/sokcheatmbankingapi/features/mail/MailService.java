package co.istad.sokcheatmbankingapi.features.mail;

import co.istad.sokcheatmbankingapi.features.mail.dto.MailRequest;
import co.istad.sokcheatmbankingapi.features.mail.dto.MailResponse;

public interface MailService {

    MailResponse send(MailRequest mailRequest);

}
