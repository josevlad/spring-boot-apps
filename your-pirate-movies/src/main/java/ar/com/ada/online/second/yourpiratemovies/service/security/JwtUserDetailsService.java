package ar.com.ada.online.second.yourpiratemovies.service.security;

import ar.com.ada.online.second.yourpiratemovies.component.security.JwtAuthProvider;
import ar.com.ada.online.second.yourpiratemovies.model.entity.security.User;
import ar.com.ada.online.second.yourpiratemovies.model.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthProvider jwtAuthProvider;

    @Override @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

        return jwtAuthProvider.createJwtUserDetails(user);
    }
}
