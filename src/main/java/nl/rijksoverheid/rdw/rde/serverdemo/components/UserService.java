package nl.rijksoverheid.rdw.rde.serverdemo.components;

import nl.rijksoverheid.rdw.rde.serverdemo.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
