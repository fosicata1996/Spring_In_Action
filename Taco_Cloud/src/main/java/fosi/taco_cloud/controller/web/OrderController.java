package fosi.taco_cloud.controller.web;

import fosi.taco_cloud.entity.TacoOrder;
import fosi.taco_cloud.entity.security.User;
import fosi.taco_cloud.repository.OrderRepository;
import fosi.taco_cloud.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController
{
    private int pageSize = 20;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository)
    {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    @GetMapping("/current")
    public String orderForm()
    {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
        // 1 -->    Principal principal
        // 2 -->     Authentication authentication
        @AuthenticationPrincipal User user
    )
    {
        if (errors.hasErrors())
        {
            return "orderForm";
        }

        // 1 -->       order.setUser(userRepository.findByUsername(principal.getName()));
        // 2 -->       order.setUser((User) authentication.getPrincipal());
        // 3 -->       order.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        order.setUser(user); // <-- 4

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model)
    {
        Pageable pageable = PageRequest.of(0, pageSize);

        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
