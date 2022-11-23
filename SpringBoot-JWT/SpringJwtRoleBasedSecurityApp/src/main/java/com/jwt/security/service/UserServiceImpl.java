package com.jwt.security.service;

import com.jwt.security.entity.Role;
import com.jwt.security.entity.User;
import com.jwt.security.repository.RoleRepository;
import com.jwt.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public void initUserAndAdminRoles(){
        var adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("Admin Role");
        roleRepository.save(adminRole);

        var userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("User Role");
        roleRepository.save(userRole);


        var adminUser = new User();
        adminUser.setUserName("admin-venkat");
        adminUser.setUserFirstName("Venkatesh");
        adminUser.setUserLastName("Kokkanti");
        adminUser.setPassword("adminVenkat");
        Set<Role> userAdminRole = new HashSet<>();
        userAdminRole.add(adminRole);
        adminUser.setRoles(userAdminRole);

        userRepository.save(adminUser);


        var newUser = new User();
        newUser.setUserName("user-rohitha");
        newUser.setUserFirstName("Rohitha");
        newUser.setUserLastName("Kokkanti");
        newUser.setPassword("rohitha12");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        newUser.setRoles(userRoles);
        userRepository.save(newUser);
    }

    @Override
    public User createNewUser(User user) {
        return null;
    }
}
