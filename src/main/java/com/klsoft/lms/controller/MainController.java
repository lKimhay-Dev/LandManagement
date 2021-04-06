package com.klsoft.lms.controller;

import com.klsoft.lms.entity.Seller;
import com.klsoft.lms.repository.SellerRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private SellerRepository sellerRepository;

    public MainController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("mDBActive", "active");
        return "layouts/dashboard";
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        model.addAttribute("mUserOpen", "menu-open");
        model.addAttribute("mULActive", "active");
        return "layouts/user/list";
    }

    // action: add  || update
    @GetMapping({"/users/{action}"})
    public String userCreate(Model model,
                             @RequestParam(value = "userId", required = false) String userId,
                             @PathVariable String action) {

        Iterable<Seller> sellers = this.sellerRepository.findAvailableSellerForUser();
        model.addAttribute("sellers", sellers);
        model.addAttribute("action", action);
        model.addAttribute("mUserOpen", "menu-open");
        model.addAttribute("mUCActive", "active");
        model.addAttribute("userId", userId);
        return "layouts/user/create-update";
    }

    @GetMapping("/project-list")
    public String projectList(Model model) {
        model.addAttribute("mProjectOpen", "menu-open");
        model.addAttribute("mPLActive", "active");
        return "layouts/project/list_project";
    }

    @GetMapping("/project/{action}")
    public String projectCreate(Model model,
                                @RequestParam(value = "projectId", required = false) String projectId,
                                @PathVariable String action) {
        model.addAttribute("action", action);
        model.addAttribute("projectId", projectId);
        model.addAttribute("mProjectOpen", "menu-open");
        model.addAttribute("mPCActive", "active");
        return "layouts/project/create_project";
    }

    @GetMapping("/sell-list")
    public String sellList(Model model) {
        model.addAttribute("mSellOpen", "menu-open");
        model.addAttribute("mSLActive", "active");
        return "layouts/sell/list_sell";
    }

    @GetMapping("/sell/{action}")
    public String sellCreate(Model model,
                             @RequestParam(value = "sellId", required = false) String sellId,
                             @PathVariable String action) {
        model.addAttribute("action", action);
        model.addAttribute("sellId", sellId);
        model.addAttribute("mSellOpen", "menu-open");
        model.addAttribute("mSCActive", "active");
        return "layouts/sell/create-update";
    }

    @GetMapping("/land-list")
    public String landList(Model model) {
        model.addAttribute("mLandOpen", "menu-open");
        model.addAttribute("mLLActive", "active");
        return "layouts/land/list-land";
    }

    @GetMapping("/land/{action}")
    public String landCreate(Model model,
                             @RequestParam(value = "landId", required = false) String landId,
                             @PathVariable String action) {

        model.addAttribute("action", action);
        model.addAttribute("landId", landId);
        model.addAttribute("mLandOpen", "menu-open");
        model.addAttribute("mLCActive", "active");
        return "layouts/land/create-update";
    }

    @GetMapping("/seller-list")
    public String sellerList(Model model) {
        model.addAttribute("mSellerOpen", "menu-open");
        model.addAttribute("mSLLActive", "active");
        return "layouts/seller/list";
    }

    @GetMapping("/seller/{action}")
    public String sellerCreate(Model model,
                               @RequestParam(value = "sellerId", required = false) String sellerId,
                               @PathVariable String action) {
        model.addAttribute("mSellerOpen", "menu-open");
        model.addAttribute("mSLCActive", "active");
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("action", action);
        return "layouts/seller/create-update";
    }

    @GetMapping("/customer-list")
    public String customerList(Model model) {
        model.addAttribute("mCustomerOpen", "menu-open");
        model.addAttribute("mCLActive", "active");
        return "layouts/customer/list-customer";
    }

    @GetMapping("/customer/{action}")
    public String customerCreate(Model model,
                                 @RequestParam(value = "customerId", required = false) String customerId,
                                 @PathVariable String action) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("action", action);
        model.addAttribute("mCustomerOpen", "menu-open");
        model.addAttribute("mCCActive", "active");
        return "layouts/customer/create-update";
    }

    @GetMapping("/penalty-list")
    public String penaltyList(Model model) {
        model.addAttribute("mPenaltyOpen", "menu-open");
        model.addAttribute("mPTLActive", "active");
        return "layouts/penalty/list";
    }

    @GetMapping("/penalty/{action}")
    public String penaltyCreate(Model model,
                                @RequestParam(value = "penaltyId", required = false) String penaltyId,
                                @PathVariable String action) {
        model.addAttribute("penaltyId", penaltyId);
        model.addAttribute("action", action);
        model.addAttribute("mPenaltyOpen", "menu-open");
        model.addAttribute("mPTCActive", "active");
        return "layouts/penalty/create-update";
    }
}
