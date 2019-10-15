package com.liseh.bll.data;

import com.liseh.bll.constant.PrivilegeType;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.entity.Privilege;
import com.liseh.bll.persistence.entity.Role;
import com.liseh.bll.persistence.repository.PrivilegeRepository;
import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.service.UserRegistrationService;
import com.liseh.bll.utility.LogUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LogManager.getLogger(InitialDataLoader.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRegistrationService userRegistrationService;

    private boolean alreadySetup = false;

    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, UserRegistrationService userRegistrationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        if (userRepository.findAll().isEmpty()) {
            insertSeedData();
        }

        alreadySetup = true;
    }

    private void insertSeedData() {
        try {
            Privilege privilege = new Privilege(PrivilegeType.ALL);
            privilegeRepository.saveAndFlush(privilege);

            Role userRole = new Role(RoleType.USER);
            userRole.getPrivileges().add(privilege);
            roleRepository.saveAndFlush(userRole);

            Role adminRole = new Role(RoleType.ADMIN);
            adminRole.getPrivileges().add(privilege);
            roleRepository.saveAndFlush(adminRole);

            UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
            userRegistrationDto.setUsername("admin");
            userRegistrationDto.setEmail("admin@liseh.com");
            userRegistrationDto.setPassword("admin");
            userRegistrationDto.setDateOfBirth("22-FEB-1992");
            userRegistrationDto.setMobileNumber("12345678");
            userRegistrationService.registerNewAdmin(userRegistrationDto);
        } catch (Exception ex) {
            LogUtils.error(logger, "InsertSeedData", "Failed", ex.getMessage());

        }
    }
}
