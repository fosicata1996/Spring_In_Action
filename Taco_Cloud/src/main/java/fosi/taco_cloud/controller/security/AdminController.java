package fosi.taco_cloud.controller.security;

import fosi.taco_cloud.service.OrderAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    private final OrderAdminService adminService;

    public AdminController(OrderAdminService adminService)
    {
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders()
    {
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
