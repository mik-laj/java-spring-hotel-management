package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.Role;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.repository.RoleRepository;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import java.util.Arrays;
import java.util.HashSet;

@Component
@Order(10)
public class UserInitializer implements DataLoader {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void load() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            Role roleUser = new Role("ROLE_USER");
            Role roleReception = new Role("ROLE_RECEPTION");
            Role roleHr = new Role("ROLE_HR");
            Role roleWarehouse = new Role("ROLE_WAREHOUSE");
            roleRepository.save(new HashSet<>(Arrays.asList(roleUser, roleReception, roleHr, roleWarehouse)));

            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setFirstName("Marek");
            userAdmin.setLastName("Kociak");
            userAdmin.setPassword(encoder.encode("admin"));
            userAdmin.setRoles(new HashSet<>(Arrays.asList(roleUser, roleReception, roleHr, roleWarehouse)));
            userRepository.save(userAdmin);

            User user = new User();
            user.setUsername("user");
            user.setFirstName("Jarek");
            user.setLastName("Kotek");
            user.setRoles(new HashSet<>(Arrays.asList(roleUser, roleReception)));
            user.setPassword(encoder.encode("user"));
            userRepository.save(user);

            User userWarehouse = new User();
            userWarehouse.setUsername("warehouse");
            userWarehouse.setFirstName("Darek");
            userWarehouse.setLastName("Koteczek");
            userWarehouse.setRoles(new HashSet<>(Arrays.asList(roleUser, roleWarehouse)));
            userWarehouse.setPassword(encoder.encode("warehouse"));
            userRepository.save(userWarehouse);

            User userRaport = new User();
            userRaport.setUsername("hr");
            userRaport.setFirstName("Czarek");
            userRaport.setLastName("Kot");
            userRaport.setRoles(new HashSet<>(Arrays.asList(roleUser, roleHr)));
            userRaport.setPassword(encoder.encode("hr"));
            userRepository.save(userRaport);
        }
    }
}
