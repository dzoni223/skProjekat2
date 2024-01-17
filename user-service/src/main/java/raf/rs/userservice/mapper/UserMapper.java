package raf.rs.userservice.mapper;

import org.springframework.stereotype.Component;
import raf.rs.userservice.domain.User;
import raf.rs.userservice.dto.*;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user) {

        UserDto userDto = new UserDto();

        setUserDtoFields(user, userDto);

        return userDto;
    }

    public User clientCreateDtoToUser(ClientCreateDto clientCreateDto) {

        User user = new User();
        setUserFields(user, clientCreateDto);

        user.setPassportNo(clientCreateDto.getPassportNo());
        user.setReservationDays(clientCreateDto.getReservationDays());
        user.setClanskaKarta(clientCreateDto.getClanskaKarta());

        return user;
    }


    public ClientDto userToClientDto(User user) {

        ClientDto clientDto = new ClientDto();
        setUserDtoFields(user, clientDto);

        clientDto.setPassportNo(user.getPassportNo());
        clientDto.setReservationDays(user.getReservationDays());
        clientDto.setClanksaKarta(user.getClanskaKarta());

        return clientDto;
    }

    public User managerCreateDtoToUser(ManagerCreateDto managerCreateDto) {

        User user = new User();
        setUserFields(user, managerCreateDto);

        user.setCompanyName(managerCreateDto.getCompanyName());
        user.setHireDate(managerCreateDto.getHireDate());

        return user;
    }

    public ManagerDto userToManagerDto(User user) {

        ManagerDto managerDto = new ManagerDto();
        setUserDtoFields(user, managerDto);

        managerDto.setCompanyName(user.getCompanyName());
        managerDto.setHireDate(user.getHireDate());

        return managerDto;
    }

    public void updateUser(User user, UserUpdateDto userUpdateDto) {

        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());
        user.setName(userUpdateDto.getName());
        user.setLastName(userUpdateDto.getLastName());
        user.setEmail(userUpdateDto.getEmail());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        user.setBirthDate(userUpdateDto.getBirthDate());

        user.setPassportNo(userUpdateDto.getPassportNo());
        user.setReservationDays(userUpdateDto.getReservationDays());
        user.setClanskaKarta(userUpdateDto.getClanskaKarta());

        user.setCompanyName(userUpdateDto.getCompanyName());
        user.setHireDate(userUpdateDto.getHireDate());

    }

    public void setUserFields(User user, UserCreateDto userCreateDto) {

        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setEmail(userCreateDto.getEmail());
        user.setName(userCreateDto.getName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setBirthDate(userCreateDto.getBirthDate());
        user.setActive(userCreateDto.getActive());

    }

    public void setUserDtoFields(User user, UserDto userDto) {

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setActive(user.getActive());
    }
}
