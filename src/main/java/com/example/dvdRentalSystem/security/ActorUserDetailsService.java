package com.example.dvdRentalSystem.security;

import com.example.dvdRentalSystem.model.Actor;
import com.example.dvdRentalSystem.repository.ActorRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorUserDetailsService implements UserDetailsService {

    private final ActorRepo actorRepo;

    public ActorUserDetailsService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Actor actor = actorRepo.findByFirstName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Actor not found: " + username));

        Set<GrantedAuthority> authorities = actor.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(actor.getFirstName(), actor.getLastName(), authorities);
    }
}
