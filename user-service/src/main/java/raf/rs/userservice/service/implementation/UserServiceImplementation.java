package raf.rs.userservice.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import raf.rs.userservice.domain.Rank;
import raf.rs.userservice.domain.Role;
import raf.rs.userservice.domain.User;
import raf.rs.userservice.dto.*;
import raf.rs.userservice.mapper.UserMapper;
import raf.rs.userservice.repository.RankRepository;
import raf.rs.userservice.repository.RoleRepository;
import raf.rs.userservice.repository.UserRepository;
import raf.rs.userservice.security.service.TokenService;
import raf.rs.userservice.service.UserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RankRepository rankRepository;
    private TokenService tokenService;
    private UserMapper userMapper;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, RankRepository rankRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.rankRepository = rankRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    @Override
    public ClientDto addClient(ClientCreateDto clientCreateDto) {

        User user = userMapper.clientCreateDtoToUser(clientCreateDto);

        user.setActive(true);
        Role role = roleRepository.findRoleByName("client").get();
        user.setRole(role);

        userRepository.save(user);

        return userMapper.userToClientDto(user);
    }

    @Override
    public ManagerDto addManager(ManagerCreateDto managerCreateDto) {

        User user = userMapper.managerCreateDtoToUser(managerCreateDto);

        user.setActive(true);;
        Role role = roleRepository.findRoleByName("manager").get();
        user.setRole(role);

        userRepository.save(user);

        return userMapper.userToManagerDto(user);
    }

    @Override
    public List<UserDto> findAll() {

        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = new LinkedList<>();

        for(User user: users) {
            UserDto userDto = userMapper.userToUserDto(user);
            dtoList.add(userDto);
        }

        return dtoList;
    }

    @Override
    public List<ClientDto> findAllClients() {

        List<User> users = userRepository.findAll();
        List<ClientDto> dtoList = new LinkedList<>();

        for(User user: users) {
            if(user.getRole().getName().equals("client")) {
                ClientDto userDto = userMapper.userToClientDto(user);
                dtoList.add(userDto);
            }
        }

        return dtoList;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {

        User user = userRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword()).get();

        if(user.getActive() == false)
            return null;

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        claims.put("companyName", user.getCompanyName());

        String jwt = tokenService.generate(claims);

        return new TokenResponseDto(jwt);
    }

    @Override
    public Integer getUserIdFromJwt(String jwt) {

        Claims claims = tokenService.parseToken(jwt);
        return claims.get("id", Integer.class);
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {

        User user = userRepository.findUserById(id).get();

        userMapper.updateUser(user, userUpdateDto);
        userRepository.save(user);

        return userMapper.userToUserDto(user);

    }

    @Override
    public void banUser(Long id) {

        User user = userRepository.findUserById(id).get();
        user.setActive(false);

        userRepository.save(user);
    }

    @Override
    public void unbanUser(Long id) {

        User user = userRepository.findUserById(id).get();
        user.setActive(true);

        userRepository.save(user);
    }

    @Override
    public DiscountDto getRank(Long id) {

        User user = userRepository.findUserById(id).get();
        int reservationDays = user.getReservationDays();

        List<Rank> ranks = rankRepository.findAll();

        for(Rank rank: ranks) {
            if(reservationDays >= rank.getMinimumReservationDays()
                && reservationDays <= rank.getMaximumReservationDays())

                return new DiscountDto(rank.getDiscount());
        }

        return null;
    }

    @Override
    public ClientDto increment(Long id, IncrementDaysDto incrementDaysDto) {

        User user = userRepository.findUserById(id).get();
        user.setReservationDays(user.getReservationDays() + incrementDaysDto.getIncrementDays());

        userRepository.save(user);

        return null;
    }


}
