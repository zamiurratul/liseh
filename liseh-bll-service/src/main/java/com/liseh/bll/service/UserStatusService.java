package com.liseh.bll.service;

import com.liseh.bll.persistence.dto.UserStatusDto;
import com.liseh.bll.persistence.entity.UserStatus;

import java.util.List;

public interface UserStatusService {
    List<UserStatus> getAllStatus();
    UserStatus postStatus(UserStatusDto userStatusDto);
}
