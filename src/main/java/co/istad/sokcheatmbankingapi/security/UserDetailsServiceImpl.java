package co.istad.sokcheatmbankingapi.security;


import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user from database
        User user = userRepository.findByPhoneNumber(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("User not found!")
                );
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        log.info("User Response{}",user);

        return customUserDetails;
    }
}
