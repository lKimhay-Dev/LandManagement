package com.klsoft.lms.service.impl;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.SellDto;
import com.klsoft.lms.entity.Sell;
import com.klsoft.lms.entity.SellLandDetail;
import com.klsoft.lms.exception.RequestException;
import com.klsoft.lms.repository.SellRepository;
import com.klsoft.lms.service.SellService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SellServiceImpl implements SellService {
    private static final String FIND_SUCCESS = "Sell find successfully";
    private static final String CREATE_SUCCESS = "Sell create successfully";
    private static final String UPDATE_SUCCESS = "Sell update successfully";
    private static final String DELETE_SUCCESS = "Sell delete successfully";
    private static final String NOT_FOUND = "Sell with id %s not found!";

    private SellRepository sellRepository;

    private ModelMapper modelMapper;

    public SellServiceImpl(SellRepository sellRepository, ModelMapper modelMapper) {
        this.sellRepository = sellRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto findAll() throws RequestException {
        Iterable<Sell> sells = this.sellRepository.findAll();
        return new ResponseDto(FIND_SUCCESS, sells);
    }

    @Override
    public ResponseDto findOne(int sellId) {
        Sell sell = checkExistingSell(sellId);
        return new ResponseDto(FIND_SUCCESS, sell);
    }

    @Override
    public ResponseDto create(SellDto sellDto) throws RequestException {
        Sell sell = modelMapper.map(sellDto, Sell.class);

        /*  Set Sell to SellLandDetail to get sell_id*/
        Set<SellLandDetail> sellLandDetails = sell.getSellLandDetails();
        Sell finalSell = sell;
        sellLandDetails.forEach(sellLandDetail -> sellLandDetail.setSell(finalSell));

        sell = this.sellRepository.save(sell);
        return new ResponseDto(CREATE_SUCCESS, sell);
    }

    @Override
    public ResponseDto update(SellDto sellDto) throws RequestException {
        this.checkExistingSell(sellDto.getId());
        Sell sell = this.modelMapper.map(sellDto, Sell.class);
        sell = this.sellRepository.save(sell);
        return new ResponseDto(UPDATE_SUCCESS, sell);
    }

    @Override
    public ResponseDto delete(int id) throws RequestException {
        Sell sell = this.checkExistingSell(id);
        this.sellRepository.save(sell);
        return new ResponseDto(DELETE_SUCCESS);
    }

    /**
     * Check sell is exist or not and return that value
     *
     * @param sellId seller's id
     * @return Seller
     * @throws RequestException Seller not found
     */
    private Sell checkExistingSell(int sellId) throws RequestException {
        Optional<Sell> sell = this.sellRepository.findById(sellId);
        if (sell.isPresent()) {
            return sell.get();
        }
        throw new RequestException(String.format(NOT_FOUND, sellId));
    }
}
