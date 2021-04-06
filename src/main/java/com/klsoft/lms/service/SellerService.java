package com.klsoft.lms.service;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.seller.CreateSellerDto;
import com.klsoft.lms.dto.seller.UpdateSellerDto;

public interface SellerService {
    ResponseDto findAll();

    ResponseDto findOne(int sellerId);

    ResponseDto create(CreateSellerDto createSellerDto);

    ResponseDto update(UpdateSellerDto createSellerDto);

    ResponseDto delete(int id);
}
