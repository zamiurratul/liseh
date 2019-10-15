package com.liseh.bll.service.impl;

import com.liseh.GenericKafkaObject;
import com.liseh.bll.constant.EventType;
import com.liseh.bll.constant.UserEvent;
import com.liseh.bll.persistence.dto.UserDto;
import com.liseh.bll.persistence.dto.UserStatusDto;
import com.liseh.bll.persistence.entity.UserStatus;
import com.liseh.bll.persistence.repository.RoleRepository;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.constant.RoleType;
import com.liseh.bll.constant.DateFormat;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.entity.User;
import com.liseh.bll.persistence.repository.UserStatusRepository;
import com.liseh.bll.service.MessageExchangeService;
import com.liseh.bll.service.UserRegistrationService;
import com.liseh.bll.service.UserStatusService;
import com.liseh.bll.utility.CommonUtil;
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
    private final MessageExchangeService messageExchangeService;

    private PropertyMap<UserRegistrationDto, User> skipFields = new PropertyMap<UserRegistrationDto, User>() {
        @Override
        protected void configure() {
            skip().setDateOfBirth(null);
            skip().setPassword(null);
        }
    };

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserStatusRepository userStatusRepository, MessageExchangeService messageExchangeService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
        this.messageExchangeService = messageExchangeService;
        this.modelMapper.addMappings(skipFields);
    }

    @Override
    public void registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserId(CommonUtil.randomUUID());
        user.setIsVerified(false);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate(userRegistrationDto.getDateOfBirth(), DateFormat.DD_MMM_YYYY));
        user.getRoles().add(roleRepository.findByName(RoleType.USER));
        userRepository.saveAndFlush(user);

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(userRegistrationDto.getPassword());
        messageExchangeService.sendMessage(EventType.USER, UserEvent.USER_REGISTRATION, CommonUtil.getJsonString(userDto));
    }

    @Override
    public void registerNewAdmin(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserId(CommonUtil.randomUUID());
        user.setIsVerified(true);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate("07-OCT-2019", DateFormat.DD_MMM_YYYY));
        user.getRoles().add(roleRepository.findByName(RoleType.USER));
        user.getRoles().add(roleRepository.findByName(RoleType.ADMIN));
        userRepository.save(user);
    }

    @Override
    public UserStatus postStatus(UserStatusDto userStatusDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        UserStatus userStatus = modelMapper.map(userStatusDto, UserStatus.class);
        userStatus.setUserId(user.getUserId());
        userStatus.setStatusId(CommonUtil.randomUUID());

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
