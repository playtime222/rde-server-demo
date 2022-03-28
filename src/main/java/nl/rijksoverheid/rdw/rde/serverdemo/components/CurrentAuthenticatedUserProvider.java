package nl.rijksoverheid.rdw.rde.serverdemo.components;

import org.springframework.security.core.context.SecurityContextHolder;


@Deprecated
public class CurrentAuthenticatedUserProvider //implements UsernameProvider
{
    //TODO auth name is mapped to the email address not the username
    public String getUserEmailAddress()
    {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return "";
        return auth.getName();
    }
}
