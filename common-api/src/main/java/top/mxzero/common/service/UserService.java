package top.mxzero.common.service;

import top.mxzero.common.dto.UserDTO;

public interface UserService extends BaseService<UserDTO, Integer> {
    public UserDTO getByUsername(String username);
}
