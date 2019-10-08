package com.liseh.bll.service.impl;

import com.liseh.bll.persistence.dto.UserStatusDto;
import com.liseh.bll.persistence.entity.UserStatus;
import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.constant.DateFormat;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.entity.User;
import com.liseh.bll.persistence.repository.UserStatusRepository;
import com.liseh.bll.service.UserRegistrationService;
import com.liseh.bll.service.UserStatusService;
import com.liseh.bll.utility.CommonUtils;
import com.liseh.bll.utility.DateUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserRegistrationService, UserStatusService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;

    private PropertyMap<UserRegistrationDto, User> skipFields = new PropertyMap<UserRegistrationDto, User>() {
        @Override
        protected void configure() {
            skip().setDateOfBirth(null);
            skip().setPassword(null);
        }
    };

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
        this.modelMapper.addMappings(skipFields);
    }

    @Override
    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserId(CommonUtils.randomUUID());
        user.setIsVerified(false);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate(userRegistrationDto.getDateOfBirth(), DateFormat.DD_MMM_YYYY));
        user.getRoles().add(roleRepository.findByName(RoleType.USER));
        return userRepository.save(user);
    }

    @Override
    public User registerNewAdmin(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserId(CommonUtils.randomUUID());
        user.setIsVerified(true);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate("07-OCT-2019", DateFormat.DD_MMM_YYYY));
        user.getRoles().add(roleRepository.findByName(RoleType.USER));
        user.getRoles().add(roleRepository.findByName(RoleType.ADMIN));
        return userRepository.save(user);
    }

    @Override
    public UserStatus postStatus(UserStatusDto userStatusDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        UserStatus userStatus = modelMapper.map(userStatusDto, UserStatus.class);
        userStatus.setUserId(user.getUserId());
        userStatus.setStatusId(CommonUtils.randomUUID());

        return userStatusRepository.save(userStatus);
    }

    @Override
    public List<UserStatus> getAllStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        return userStatusRepository.findAllByUserId(user.getUserId());
    }
}
