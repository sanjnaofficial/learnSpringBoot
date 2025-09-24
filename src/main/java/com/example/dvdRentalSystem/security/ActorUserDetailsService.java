package com.example.dvdRentalSystem.security;

import com.example.dvdRentalSystem.model.Actor;
import com.example.dvdRentalSystem.repository.ActorRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        System.out.println("Found actor: " + actor.getFirstName() + " / " + actor.getLastName());

        // for demo: password = lastName
        return User.withUsername(actor.getFirstName())
                .password(actor.getLastName())
                .authorities("ROLE_USER")
                .build();
    }
}
