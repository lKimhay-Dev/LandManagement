package com.klsoft.lms.service;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.SellDto;
import com.klsoft.lms.exception.RequestException;

public interface SellService {
    ResponseDto findAll() throws RequestException;

    ResponseDto findOne(int sellId);

    ResponseDto create(SellDto sellDto) throws RequestException;

    ResponseDto update(SellDto sellDto) throws RequestException;

    ResponseDto delete(int id) throws RequestException;
}
