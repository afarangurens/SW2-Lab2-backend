package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.pojo.RolePOJO;
import co.edu.unal.software_engineering.labs.repository.RoleRepository;
import co.edu.unal.software_engineering.labs.service.RoleService;
import co.edu.unal.software_engineering.labs.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController{

    private final RoleService roleService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public RoleController( RoleService roleService, UserService userService, RoleRepository roleRepository ){
        this.roleService = roleService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping( "/mis-roles" )
    public List<RolePOJO> getUserRoles( ){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        List<RolePOJO> roles = new ArrayList<>( );

        for( Role role : userService.findByUsername( username ).getRoles( ) ){
            roles.add( new RolePOJO( role ) );
        }
        return roles;
    }

    @GetMapping( value = { "/roles" } )
    public List<RolePOJO> getAllRoles( ){
        List<RolePOJO> roles = new ArrayList<>();

        for (Role role : roleRepository.findAll()) {
            roles.add(new RolePOJO( role ));
        }

        return roles;
    }
}
