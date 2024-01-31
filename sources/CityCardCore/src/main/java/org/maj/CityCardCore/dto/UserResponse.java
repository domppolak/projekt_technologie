package org.maj.CityCardCore.dto;

import lombok.Data;
import org.maj.CityCardCore.enums.Role;
import org.maj.CityCardCore.model.User;

import java.util.List;

@Data
public class UserResponse {
    Long userId;
    List<Role> roles;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.roles = user.getRoles();
    }
}
