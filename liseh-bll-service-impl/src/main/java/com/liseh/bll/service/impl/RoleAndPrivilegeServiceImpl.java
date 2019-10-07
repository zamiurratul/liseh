package com.liseh.bll.service.impl;

import com.liseh.bll.event.AppEventManager;
import com.liseh.bll.persistence.repository.PrivilegeRepository;
import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.constant.PrivilegeType;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.persistence.entity.Privilege;
import com.liseh.bll.persistence.entity.Role;
import com.liseh.bll.service.RoleAndPrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleAndPrivilegeServiceImpl implements RoleAndPrivilegeService {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public RoleAndPrivilegeServiceImpl(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @PostConstruct
    public void init() {
        AppEventManager.register("TEST_CUSTOM_EVENT", ()-> System.out.println("FROM: RoleAndPrivilegeServiceImpl"));

        List<Privilege> privilegeList = privilegeRepository.findAll();
        if (privilegeList.isEmpty()) {
            Privilege privilege = new Privilege(PrivilegeType.ALL);
            privilegeRepository.saveAndFlush(privilege);

            Role role = new Role(RoleType.USER);
            role.setPrivileges(Arrays.asList(privilege));
            roleRepository.saveAndFlush(role);
        }
    }
}
