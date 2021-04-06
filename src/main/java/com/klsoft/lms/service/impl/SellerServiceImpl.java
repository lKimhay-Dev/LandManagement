package com.klsoft.lms.service.impl;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.seller.CreateSellerDto;
import com.klsoft.lms.dto.seller.UpdateSellerDto;
import com.klsoft.lms.entity.Seller;
import com.klsoft.lms.exception.RequestException;
import com.klsoft.lms.repository.SellerRepository;
import com.klsoft.lms.service.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String FIND_SUCCESS = "Seller find successfully";
    private static final String CREATE_SUCCESS = "Seller create successfully";
    private static final String UPDATE_SUCCESS = "Seller update successfully";
    private static final String DELETE_SUCCESS = "Seller delete successfully";
    private static final String NOT_FOUND = "Seller with id %s not found!";

    private SellerRepository sellerRepository;
    private ModelMapper modelMapper;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto findAll() throws RequestException {
        Iterable<Seller> sellers = this.sellerRepository.findAllByStatus(true);
        return new ResponseDto(FIND_SUCCESS, sellers);
    }

    @Override
    public ResponseDto findOne(int sellerId) {
        Seller seller = checkExistingSeller(sellerId);
        return new ResponseDto(FIND_SUCCESS, seller);
    }

    @Override
    public ResponseDto create(CreateSellerDto createSellerDto) throws RequestException {
        Seller seller = modelMapper.map(createSellerDto, Seller.class);
        seller = this.sellerRepository.save(seller);
        return new ResponseDto(CREATE_SUCCESS, seller);
    }

    @Override
    public ResponseDto update(UpdateSellerDto updateSellerDto) throws RequestException {
        this.checkExistingSeller(updateSellerDto.getId());
        Seller seller = this.modelMapper.map(updateSellerDto, Seller.class);
        seller = this.sellerRepository.save(seller);
        return new ResponseDto(UPDATE_SUCCESS, seller);
    }

    @Override
    public ResponseDto delete(int id) throws RequestException {
        Seller seller = this.checkExistingSeller(id);
        seller.setStatus(false);
        this.sellerRepository.save(seller);
        return new ResponseDto(DELETE_SUCCESS);
    }

    /**
     * Check user is exist or not and return that value
     *
     * @param sellerId seller's id
     * @return Seller
     * @throws RequestException Seller not found
     */
    private Seller checkExistingSeller(int sellerId) throws RequestException {
        Optional<Seller> seller = this.sellerRepository.findById(sellerId);
        if (seller.isPresent()) {
            return seller.get();
        }
        throw new RequestException(String.format(NOT_FOUND, sellerId));
    }
}
