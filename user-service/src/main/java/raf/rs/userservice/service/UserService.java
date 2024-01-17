package raf.rs.userservice.service;

import org.springframework.stereotype.Service;
import raf.rs.userservice.dto.*;

import java.util.List;

public interface UserService {

    public ClientDto addClient(ClientCreateDto clientCreateDto);

    public ManagerDto addManager(ManagerCreateDto managerCreateDto);

    public List<UserDto> findAll();

    public List<ClientDto> findAllClients();

    public TokenResponseDto login(TokenRequestDto tokenRequestDto);

    public Integer getUserIdFromJwt(String jwt);

    public UserDto update(Long id, UserUpdateDto userUpdateDto);

    public void banUser(Long id);

    public void unbanUser(Long id);

    public DiscountDto getRank(Long id);

    public ClientDto increment(Long id, IncrementDaysDto incrementDaysDto);

}
