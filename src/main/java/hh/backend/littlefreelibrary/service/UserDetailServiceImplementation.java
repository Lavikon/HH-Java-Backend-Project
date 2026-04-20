package hh.backend.littlefreelibrary.service;

import org.springframework.stereotype.Service;

import hh.backend.littlefreelibrary.domain.AppUser;
import hh.backend.littlefreelibrary.domain.AppUserRepository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailServiceImplementation implements UserDetailsService{

    // repos
    private final AppUserRepository repository;
    public UserDetailServiceImplementation(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currrentUser = repository.findByUsername(username);
        UserDetails user = new User(username, currrentUser.getPassword(), AuthorityUtils.createAuthorityList(currrentUser.getRole()));
        
        return user;
    }
}
