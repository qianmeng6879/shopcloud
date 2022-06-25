package top.mxzero.userservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mxzero.common.dto.PageDTO;
import top.mxzero.common.dto.UserDTO;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.service.UserService;
import top.mxzero.common.util.DeepBeanUtil;
import top.mxzero.userservice.mapper.UserMapper;
import top.mxzero.userservice.model.User;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO get(Integer id) {
        log.info("【service】ID查询开始,{}", id);
        User user = userMapper.selectById(id);
        log.info("【service】ID查询结束,{}", user);

        if (user == null) {
            return null;
        }
        user.setPassword(null);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> list() {
        List<User> users = userMapper.selectList(null);
        if (users.size() == 0) {
            return new ArrayList<>(0);
        }
        users.forEach(user -> user.setPassword(null));
        return DeepBeanUtil.copyListProperties(users, UserDTO::new);
    }

    @Override
    public PageDTO<UserDTO> split(long current, long size) {
        IPage<User> page = new Page<>(current, size);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(user -> user.setPassword(null));

        PageDTO<UserDTO> userDTOPage = new PageDTO<>();
        userDTOPage.setCurrent(page.getCurrent());
        userDTOPage.setSize(page.getRecords().size());
        userDTOPage.setTotal(page.getTotal());
        userDTOPage.setData(DeepBeanUtil.copyListProperties(page.getRecords(), UserDTO::new));

        return userDTOPage;
    }

    @Override
    @Transactional
    public boolean add(UserDTO dto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", dto.getUsername()).select("id", "username", "is_delete");
        User currentUser = userMapper.selectOne(userQueryWrapper);

        System.out.println(currentUser);
        if(currentUser != null){
            if(!currentUser.isDelete()){
                log.info("【service】用户已存在,{}", dto.getUsername());
                throw new ServiceException("用户名已存在");
            }else {
                int userId = currentUser.getId();
                User user = new User();
                BeanUtils.copyProperties(dto, user);
                user.setId(userId);
                user.setCreateTime(new Date());
                log.info("【service】新增用户开始,通过逻辑删除用户修改,{}", user);
                int result = userMapper.updateById(user);
                log.info("【service】新增用户结束,通过逻辑删除用户修改,{}", result);
                return result > 0;
            }
        }


        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setCreateTime(new Date());
        log.info("【service】新增用户开始,{}", user);
        int result = userMapper.insert(user);
        log.info("【service】新增用户结束,{}", result);
        return result > 0;
    }

    @Override
    @Transactional
    public boolean update(UserDTO dto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", dto.getId());
        if (userMapper.selectCount(userQueryWrapper) == 0) {
            log.info("【service】用户ID不存在:{}", dto.getId());
            return false;
        }
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setUpdateTime(new Date());
        log.info("【service】修改用户开始, {}", user);
        int result = userMapper.updateById(user);
        log.info("【service】修改用户结束, {}", result);
        return result > 0;
    }

    @Override
    @Transactional
    public boolean remove(Integer id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", id);
        if (userMapper.selectCount(userQueryWrapper) == 0) {
            log.info("【service】删除的ID不存在,{}", id);
            return false;
        }
        log.info("【service】删除用户开始,{}", id);
        int result = userMapper.deleteById(id);
        log.info("【service】删除用户结束,{}", result);
        return result > 0;
    }

    @Override
    public long count() {
        return userMapper.selectCount(null);
    }

    @Override
    public UserDTO getByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
