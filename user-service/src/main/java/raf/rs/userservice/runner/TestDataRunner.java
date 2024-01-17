package raf.rs.userservice.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.rs.userservice.domain.Rank;
import raf.rs.userservice.domain.Role;
import raf.rs.userservice.domain.User;
import raf.rs.userservice.repository.RankRepository;
import raf.rs.userservice.repository.RoleRepository;
import raf.rs.userservice.repository.UserRepository;

@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private RankRepository rankRepository;

    public TestDataRunner(RoleRepository roleRepository, UserRepository userRepository, RankRepository rankRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName("admin");
        adminRole.setDescription("Admin role - all privileges");

        Role clientRole = new Role();
        clientRole.setName("client");
        clientRole.setDescription("Client role - client privileges");

        Role managerRole = new Role();
        managerRole.setName("manager");
        managerRole.setDescription("Manager role - manager privileges");

        roleRepository.save(adminRole);
        roleRepository.save(clientRole);
        roleRepository.save(managerRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setActive(true);
        admin.setRole(adminRole);

        userRepository.save(admin);

        Rank rank1 = new Rank();
        rank1.setName("bronze");
        rank1.setMinimumReservationDays(0);
        rank1.setMaximumReservationDays(7);
        rank1.setDiscount(0.0);

        Rank rank2 = new Rank();
        rank2.setName("silver");
        rank2.setMinimumReservationDays(8);
        rank2.setMaximumReservationDays(20);
        rank2.setDiscount(0.15);

        Rank rank3 = new Rank();
        rank3.setName("gold");
        rank3.setMinimumReservationDays(21);
        rank3.setMaximumReservationDays(100);
        rank3.setDiscount(0.3);

        rankRepository.save(rank1);
        rankRepository.save(rank2);
        rankRepository.save(rank3);

    }
}
