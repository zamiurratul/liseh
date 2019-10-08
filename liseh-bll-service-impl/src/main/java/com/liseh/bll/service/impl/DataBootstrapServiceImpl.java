package com.liseh.bll.service.impl;

import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.repository.PrivilegeRepository;
import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.constant.PrivilegeType;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.persistence.entity.Privilege;
import com.liseh.bll.persistence.entity.Role;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.service.DataBootstrapService;
import com.liseh.bll.service.UserRegistrationService;
import com.liseh.bll.utility.LogUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class DataBootstrapServiceImpl implements DataBootstrapService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRegistrationService userRegistrationService;

    public DataBootstrapServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, UserRegistrationService userRegistrationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.userRegistrationService = userRegistrationService;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findAll().isEmpty()) {
            insertSeedData();
        }
    }

    private void insertSeedData() {
        try {
            UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
            userRegistrationDto.setUsername("admin");
            userRegistrationDto.setEmail("admin@liseh.com");
            userRegistrationDto.setPassword("admin123");
            userRegistrationService.registerNewUser(userRegistrationDto);

            Privilege privilege = new Privilege(PrivilegeType.ALL);
            privilegeRepository.saveAndFlush(privilege);

            Role role = new Role(RoleType.USER);
            role.setPrivileges(Collections.singletonList(privilege));
            roleRepository.saveAndFlush(role);

        } catch (Exception ex) {
            LogUtils.error("Failed to insert feed data. Cause: " + ex.getMessage());
        }
    }
}
