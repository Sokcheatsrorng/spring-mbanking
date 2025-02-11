package co.istad.sokcheatmbankingapi.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedError<T> {
    // Request Entity Too Large,Bad Request,...
    private String code;

    //Detail error for user
    private T description;


}
