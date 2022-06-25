package top.mxzero.common.service;

import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.PageDTO;

import java.util.List;

public interface AddressService extends BaseService<AddressDTO,Integer>{

    List<AddressDTO> listByUserId(Integer id);

    PageDTO<AddressDTO> splitByUserId(long current, long size, Integer id);

    AddressDTO getByIdAndUserId(Integer id, Integer userId);

    boolean removeByIdAndUserId(Integer id, Integer userId);

    boolean updateByIdAndUserId(Integer userId, AddressDTO addressDTO);
}
