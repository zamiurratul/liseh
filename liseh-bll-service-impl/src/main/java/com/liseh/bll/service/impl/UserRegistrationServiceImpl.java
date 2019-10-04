package com.liseh.bll.service.impl;

import com.liseh.bll.UserRepository;
import com.liseh.bll.constants.DateFormats;
import com.liseh.bll.constants.ResponseCode;
import com.liseh.bll.exception.BaseException;
import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.UserRegistrationDto;
import com.liseh.bll.model.entity.User;
import com.liseh.bll.service.UserService;
import com.liseh.bll.utility.CommonUtils;
import com.liseh.bll.utility.DateUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegistrationServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private PropertyMap<UserRegistrationDto, User> skipFields = new PropertyMap<UserRegistrationDto, User>() {
        @Override
        protected void configure() {
            skip().setDateOfBirth(null);
            skip().setPassword(null);
        }
    };

    public UserRegistrationServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper.addMappings(skipFields);
    }

    @Override
    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setUserIdentifier(CommonUtils.randomUUID());
        user.setIsVerified(false);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setDateOfBirth(DateUtils.parseDate(userRegistrationDto.getDateOfBirth(), DateFormats.DD_MMM_YYYY));
        return userRepository.save(user);
    }
}
