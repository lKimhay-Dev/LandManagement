package com.klsoft.lms.service.impl;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.user.CreateUserDto;
import com.klsoft.lms.dto.user.UpdateUserDto;
import com.klsoft.lms.entity.Seller;
import com.klsoft.lms.entity.User;
import com.klsoft.lms.exception.RequestException;
import com.klsoft.lms.repository.SellerRepository;
import com.klsoft.lms.repository.UserRepository;
import com.klsoft.lms.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String FIND_SUCCESS = "User find successfully";
    private static final String CREATE_SUCCESS = "User create successfully";
    private static final String UPDATE_SUCCESS = "User update successfully";
    private static final String DELETE_SUCCESS = "User delete successfully";
    private static final String NOT_FOUND = "User with id %s not found!";
    private static final String NOT_AVAILABLE = "Username not available!";
    private static final String INVALID_SELLER_ID = "Seller's id must be not missing or empty!";
    private static final String SELLER_ID_NOT_AVAILABLE = "Seller's id not available!";
    private static final String SELLER_ID_NOT_EXIST = "Seller's id not exist!";

    private SellerRepository sellerRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(SellerRepository sellerRepository, UserRepository userRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseDto findAll() {
        Iterable<User> userList = this.userRepository.findAllByStatus(true);
        return new ResponseDto(FIND_SUCCESS, userList);
    }

    @Override
    public ResponseDto findOne(int userId) throws RequestException {
        User user = this.checkExistingUser(userId);
        return new ResponseDto(FIND_SUCCESS, user);
    }

    @Override
    public ResponseDto create(CreateUserDto createUserDto) throws RequestException {
        // Validate username
        User user = this.userRepository.findByUsername(createUserDto.getUsername());
        if (user != null) {
            throw new RequestException(NOT_AVAILABLE);
        }

        // Validate seller's id
        Seller seller = createUserDto.getSeller();
        if (!(seller != null && seller.getId() != null)) {
            throw new RequestException(INVALID_SELLER_ID);
        }

        // Check seller's id is exist or not
        Seller existSeller = this.sellerRepository.findByIdAndStatus(seller.getId(), true);
        if (existSeller == null) {
            throw new RequestException(SELLER_ID_NOT_EXIST);
        }

        // Check seller's id is available or not
        user = this.userRepository.findBySellerId(seller.getId());
        if (user != null) {
            throw new RequestException(SELLER_ID_NOT_AVAILABLE);
        }

        // Encode Password
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        // Convert role to to upper-case
        createUserDto.setRole(createUserDto.getRole().toUpperCase());

        // Convert UserDto to entity User
        User newUser = modelMapper.map(createUserDto, User.class);

        // Save new user
        user = this.userRepository.save(newUser);

        return new ResponseDto(CREATE_SUCCESS, user);
    }

    @Override
    public ResponseDto update(UpdateUserDto userUpdateDto) throws RequestException {
        this.checkExistingUser(userUpdateDto.getId());

        // Encode Password
        userUpdateDto.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        // Convert UserDto to entity User
        User user = modelMapper.map(userUpdateDto, User.class);

        // Convert role to to upper-case
        userUpdateDto.setRole(userUpdateDto.getRole().toUpperCase());

        // Update user
        user = this.userRepository.save(user);

        return new ResponseDto(UPDATE_SUCCESS, user);
    }

    @Override
    public ResponseDto delete(int id) throws RequestException {
        User user = this.checkExistingUser(id);
        user.setStatus(false);
        this.userRepository.save(user);
        return new ResponseDto(DELETE_SUCCESS);
    }

    /**
     * Check user exist ot not and return that value
     *
     * @param userId user's id
     * @return User
     * @throws RequestException User not found
     */
    private User checkExistingUser(int userId) throws RequestException {
        User user = this.userRepository.findByIdAndStatus(userId, true);
        if (user != null) {
            return user;
        } else {
            throw new RequestException(String.format(NOT_FOUND, userId));
        }
    }
}
