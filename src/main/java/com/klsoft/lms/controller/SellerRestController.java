package com.klsoft.lms.controller;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.seller.CreateSellerDto;
import com.klsoft.lms.dto.seller.UpdateSellerDto;
import com.klsoft.lms.service.SellerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sellers")
public class SellerRestController {

    private SellerService sellerService;

    public SellerRestController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseDto findAll() {
        return this.sellerService.findAll();
    }

    @GetMapping("/{sellerId}")
    public ResponseDto findOne(@PathVariable int sellerId) {
        return this.sellerService.findOne(sellerId);
    }

    @PostMapping
    public ResponseDto create(@Valid @RequestBody CreateSellerDto createSellerDto) {
        return this.sellerService.create(createSellerDto);
    }

    @PutMapping
    public ResponseDto update(@Valid @RequestBody UpdateSellerDto updateSellerDto) {
        return this.sellerService.update(updateSellerDto);
    }

    @DeleteMapping("/{sellerId}")
    public ResponseDto delete(@PathVariable int sellerId) {
        return this.sellerService.delete(sellerId);
    }
}
