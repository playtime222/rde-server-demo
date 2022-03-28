package nl.rijksoverheid.rdw.rde.serverdemo.components;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
