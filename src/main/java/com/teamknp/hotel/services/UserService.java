package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Role;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.form.EmployeeForm;
import com.teamknp.hotel.form.DeliveryForm;
import com.teamknp.hotel.repository.RoleRepository;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Page<User> search(String query, Pageable pageable) {
        return userRepository.search(query, pageable);
    }

    public User save(EmployeeForm form) {

        Set<Role> roles = null;
        Set<Integer> rolesId = form.getRoles();

        User user = new User();
        user.setUsername(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());

        for(Integer a: rolesId){
            user.addRole(roleRepository.findOne(a));
        }
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void update(User entity, EmployeeForm formData) {
    }

    public Page<Role> searchRole(String search, Pageable pageable) {
        search = "%" + search + "%";
        return roleRepository.findAllByNameLike(search, pageable);
    }
}
