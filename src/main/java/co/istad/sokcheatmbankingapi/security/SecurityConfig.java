package co.istad.sokcheatmbankingapi.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

//    @Bean
//    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        UserDetails userAdmin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("USER","ADMIN")
//                .build();
//
//        UserDetails userStaff = User.builder()
//                .username("staff")
//                .password(passwordEncoder.encode("staff"))
//                .roles("USER","STAFF")
//                .build();
//
//        UserDetails userCustomer = User.builder()
//                .username("customer")
//                .password(passwordEncoder.encode("customer"))
//                .roles("USER","CUSTOMER")
//                .build();
//
//        UserDetails userUSER = User.builder()
//                .username("user")
//                .password(passwordEncoder. encode("user"))
//                .roles("USER","USER")
//                .build();
//
//        manager.createUser(userAdmin);
//        manager.createUser(userStaff);
//        manager.createUser(userCustomer);
//        manager.createUser(userUSER);
//
//        return manager;
//    }

    @Bean
    SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //TODO: your security logic
        httpSecurity
                .authorizeHttpRequests(request-> request
                                .requestMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/v1/accounts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"api/v1/accounts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"api/v1/accounts/**").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.POST,"api/v1/accounts/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE,"api/v1/accounts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"api/v1/account-types/**").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.GET,"api/v1/card-types/**").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.GET,"api/v1/transactions/**").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.POST,"api/v1/transactions/**").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.GET,"api/v1/transactions/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.GET,"api/v1/accounts/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.GET,"api/v1/payments/**").hasRole("STAFF")
                                .anyRequest()
                                .authenticated()
                        );

                // authenticated will provide each scope features for users
                // for permit it will be noAuth for every endpoints

        //config enableWebsSecurity
        httpSecurity.httpBasic(Customizer.withDefaults());

        //disabled CSRF
        httpSecurity.csrf(token-> token.disable());
        httpSecurity.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();

    }

}
