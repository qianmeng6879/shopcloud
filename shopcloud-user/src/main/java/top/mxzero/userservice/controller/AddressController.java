package top.mxzero.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.service.AddressService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户收货地址API
 *
 * @author zero
 */
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;


    @JWTAuthentication
    @GetMapping("/address")
    public Object listAddress(Long current, Long size, HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("USERID");

        if (size != null) {
            if (current == null) {
                current = 1L;
            }
            return addressService.splitByUserId(current, size, userId);
        }

        return addressService.listByUserId(userId);
    }

    @JWTAuthentication
    @GetMapping("/address/{id}")
    public Object getAddress(@PathVariable("id") Integer id, HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("USERID");

        return addressService.getByIdAndUserId(id, userId);
    }

    @JWTAuthentication
    @PostMapping("/address")
    public Object addAddress(@Valid @RequestBody AddressDTO addressDTO, HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("USERID");
        addressDTO.setUserId(userId);
        return addressService.add(addressDTO);
    }

    @JWTAuthentication
    @PutMapping("/address/{id}")
    public Object updateAddress(@PathVariable("id") Integer id, @Valid @RequestBody AddressDTO addressDTO, HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("USERID");
        return addressService.updateByIdAndUserId(userId, addressDTO);
    }

    @JWTAuthentication
    @DeleteMapping("/address/{id}")
    public Object deleteAddress(@PathVariable("id") Integer id, HttpServletRequest request){
        int userId = (Integer) request.getAttribute("USERID");
        return addressService.removeByIdAndUserId(id, userId);
    }

}

