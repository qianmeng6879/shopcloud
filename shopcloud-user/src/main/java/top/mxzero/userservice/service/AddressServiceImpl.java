package top.mxzero.userservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.PageDTO;
import top.mxzero.common.service.AddressService;
import top.mxzero.common.util.DeepBeanUtil;
import top.mxzero.userservice.mapper.AddressMapper;
import top.mxzero.userservice.model.Address;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressDTO> listByUserId(Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("user_id", userId);

        List<Address> addresses = addressMapper.selectList(addressQueryWrapper);

        return DeepBeanUtil.copyListProperties(addresses, AddressDTO::new);
    }

    @Override
    public PageDTO<AddressDTO> splitByUserId(long current, long size, Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("user_id", userId);

        IPage<Address> page = new Page<>(current, size);

        addressMapper.selectPage(page, addressQueryWrapper);

        PageDTO<AddressDTO> addressDTOPageDTO = new PageDTO<>();

        addressDTOPageDTO.setCurrent(page.getCurrent());
        addressDTOPageDTO.setSize(page.getRecords().size());
        addressDTOPageDTO.setTotal(page.getTotal());
        addressDTOPageDTO.setData(DeepBeanUtil.copyListProperties(page.getRecords(), AddressDTO::new));


        return addressDTOPageDTO;
    }

    @Override
    public AddressDTO getByIdAndUserId(Integer id, Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("id", id).eq("user_id", userId);

        Address address = addressMapper.selectOne(addressQueryWrapper);
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        BeanUtils.copyProperties(address, addressDTO);

        return addressDTO;
    }

    @Override
    public boolean removeByIdAndUserId(Integer id, Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("id", id).eq("user_id", userId);

        return addressMapper.delete(addressQueryWrapper) > 0;
    }

    @Override
    public boolean updateByIdAndUserId(Integer userId, AddressDTO addressDTO) {
        UpdateWrapper<Address> addressUpdateWrapper = new UpdateWrapper<>();
        addressUpdateWrapper.eq("id", addressDTO.getId()).eq("user_id", addressDTO.getUserId());

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);

        return addressMapper.update(address, addressUpdateWrapper) > 0;
    }

    @Override
    public AddressDTO get(Integer id) {
        Address address = addressMapper.selectById(id);

        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        BeanUtils.copyProperties(address, addressDTO);

        return addressDTO;
    }

    @Override
    public List<AddressDTO> list() {
        List<Address> addresses = addressMapper.selectList(null);

        return DeepBeanUtil.copyListProperties(addresses, AddressDTO::new);
    }

    @Override
    public PageDTO<AddressDTO> split(long current, long size) {
        IPage<Address> page = new Page<>(current, size);

        addressMapper.selectPage(page, null);

        PageDTO<AddressDTO> addressDTOPageDTO = new PageDTO<>();

        addressDTOPageDTO.setCurrent(page.getCurrent());
        addressDTOPageDTO.setSize(page.getRecords().size());
        addressDTOPageDTO.setTotal(page.getTotal());
        addressDTOPageDTO.setData(DeepBeanUtil.copyListProperties(page.getRecords(), AddressDTO::new));

        return addressDTOPageDTO;
    }

    @Override
    public boolean add(AddressDTO dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        address.setCreateTime(new Date());
        return addressMapper.insert(address) > 0;
    }

    @Override
    public boolean update(AddressDTO dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        address.setUpdateTime(new Date());
        return addressMapper.updateById(address) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return addressMapper.deleteById(id) > 0;
    }

    @Override
    public long count() {
        return addressMapper.selectCount(null);
    }
}
