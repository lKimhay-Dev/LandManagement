package com.klsoft.lms.config;

import com.klsoft.lms.entity.Seller;
import com.klsoft.lms.entity.User;
import com.klsoft.lms.repository.SellerRepository;
import com.klsoft.lms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class BeanConfig {
    private SellerRepository sellerRepository;
    private UserRepository userRepository;

    public BeanConfig(SellerRepository sellerRepository, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void createDefaultSellerAndUser() {
        Optional<Seller> existSeller = this.sellerRepository.findById(1);
        Seller seller = new Seller();
        if (existSeller.isEmpty()) {
            seller.setName("Admin");
            seller.setAddress("Cambodia");
            seller.setPhone1("");
            this.sellerRepository.save(seller);
        }
    }

    @Bean
    public void createDefaultUser() {
        User existingUser = this.userRepository.findByUsername("admin");
        if (existingUser == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("password");
            user.setRole("ADMIN");

            Seller seller = new Seller();
            seller.setId(1);
            user.setSeller(seller);
            user.setSeller(seller);
            this.userRepository.save(user);
        }
    }
}
