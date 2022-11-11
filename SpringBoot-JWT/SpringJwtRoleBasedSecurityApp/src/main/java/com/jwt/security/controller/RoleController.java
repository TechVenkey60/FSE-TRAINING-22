package com.jwt.security.controller;

import com.jwt.security.entity.Role;
import com.jwt.security.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {

  private final IRoleService roleService;

  @PostMapping("/newRole")
  public ResponseEntity<Role> createNewRole(@RequestBody Role role){
    return new ResponseEntity<>(roleService.createNewRole(role), HttpStatus.CREATED);
  }

}
