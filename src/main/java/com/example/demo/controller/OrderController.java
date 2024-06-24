package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailPname;
import com.example.demo.entity.User;
import com.example.demo.entity.UserOrderDetailPname;
import com.example.demo.form.UserLoginForm;
import com.example.demo.repository.OrderDetailPnameRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserOrderDetailPnameRepository;
import com.example.demo.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class OrderController {

	private final OrderRepository orderReposotroy;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderDetailPnameRepository orderDetailPnameRepository;
	private final UserOrderDetailPnameRepository userOrderDetailPnameRepository;
	private final UserService userService;

	@Transactional // メソッド終了時にコミット 
	@GetMapping("/orderTest")
	public ModelAndView showProductList(ModelAndView mv) {

		Order order = new Order();
		order.setEid("atsuki");
		order.setOrderdate(LocalDate.now());
		order.setOrderamt(7800);
		orderReposotroy.save(order); // saveメソッドの実行後ではなくコミットされたときにDBへ反映 

		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setOrderid(order.getOrderid()); // 採番されたorderidが入る 
		orderDetail1.setPid("0029");
		orderDetail1.setAmount(2);
		orderDetailRepository.save(orderDetail1);

		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setOrderid(order.getOrderid()); // 採番されたorderidが入る 
		orderDetail2.setPid("0030");
		orderDetail2.setAmount(1);
		orderDetailRepository.save(orderDetail2);

		mv.setViewName("orderComplete");
		return mv;
	}

	@GetMapping("/orderDetailList/{orderid}")
	public ModelAndView showOrderDetailList(
			ModelAndView mv, @PathVariable("orderid") Integer orderid) {
		List<OrderDetailPname> list = orderDetailPnameRepository.findAllByOrderId(orderid);
		mv.addObject("orderDetailList", list);
		mv.setViewName("orderDetailList");
		return mv;
	}

	@GetMapping("/userLogin")
	public String showUserOrderDetailList(UserLoginForm userLoginForm) {
		return "userLogin";
	}

	@PostMapping("/userOrderList")
	public ModelAndView showUserOrderDetailList(@ModelAttribute @Validated UserLoginForm userLoginForm,
			BindingResult result, ModelAndView mv) {
		User origin = userService.getByEname(userLoginForm, result);
		
		if (!result.hasErrors()) {
			String ename = userLoginForm.getEname();
			List<UserOrderDetailPname> list = userOrderDetailPnameRepository.findAllByEId(ename);
			mv.addObject("orderDetailList", list);
			mv.setViewName("userOrderDetailList");
			return mv;
		} else {
			mv.setViewName("userLogin");
			return mv;
		}
	}

}