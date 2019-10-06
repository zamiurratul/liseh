package com.liseh.bll.service.impl;

import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.constant.DateFormat;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.entity.User;
import com.liseh.bll.service.UserService;
import com.liseh.bll.utility.CommonUtils;
import com.liseh.bll.utility.DateUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRegistrationServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private PropertyMap<UserRegistrationDto, User> skipFields = new PropertyMap<UserRegistrationDto, User>() {
        @Override
        protected void configure() {
            skip().setDateOfBirth(null);
            skip().setPassword(null);
        }
    };

    public UserRegistrationServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper.addMappings(skipFields);
    }

    @Override
    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserIdentifier(CommonUtils.randomUUID());
        user.setIsVerified(false);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate(userRegistrationDto.getDateOfBirth(), DateFormat.DD_MMM_YYYY));
        user.setRoles(Arrays.asList(roleRepository.findByName(RoleType.USER)));
        return userRepository.save(user);
    }
}
