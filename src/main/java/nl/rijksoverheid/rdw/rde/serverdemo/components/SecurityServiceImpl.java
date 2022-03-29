package nl.rijksoverheid.rdw.rde.serverdemo.components;

import nl.rijksoverheid.rdw.rde.serverdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SecurityServiceImpl implements SecurityService {


    public SecurityServiceImpl(@Autowired UserDetailsService userDetailsService, @Autowired AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        var principle = authentication.getPrincipal();
        if (principle instanceof org.springframework.security.core.userdetails.User)
        {
            return ((org.springframework.security.core.userdetails.User)principle).getUsername();
        }
//        var userDetails = authentication.getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
        return null;
    }
    @Override
    public void autoLogin(String username, String password) {
        var userDetails = userDetailsService.loadUserByUsername(username);
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}


