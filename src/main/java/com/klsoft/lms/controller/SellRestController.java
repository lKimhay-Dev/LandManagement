package com.klsoft.lms.controller;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.SellDto;
import com.klsoft.lms.service.SellService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sells")
public class SellRestController {

    private SellService sellService;

    public SellRestController(SellService sellService) {
        this.sellService = sellService;
    }

    @GetMapping
    public ResponseDto findAll() {
        return this.sellService.findAll();
    }

    @GetMapping("/{sellId}")
    public ResponseDto findOne(@PathVariable int sellId) {
        return this.sellService.findOne(sellId);
    }

    @PostMapping
    public ResponseDto create(@Valid @RequestBody SellDto sellDto) {
        return this.sellService.create(sellDto);
    }

    @PutMapping
    public ResponseDto update(@Valid @RequestBody SellDto sellDto) {
        return this.sellService.update(sellDto);
    }

    @DeleteMapping("/{sellId}")
    public ResponseDto delete(@PathVariable int sellId) {
        return this.sellService.delete(sellId);
    }

}
