package com.springmvc.annotations;



import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String homePage() {
		System.out.println("homePage method is running or not");
		return "home";
	}
	
	@RequestMapping("/login")
	public ModelAndView loadLoginPage() {
		return new ModelAndView("login").addObject("login",new Login());
	}
	@RequestMapping("/register")
	public ModelAndView loadRegisterPage() {
		return new ModelAndView("register").addObject("register",new Register());
	}
	
	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess(@Valid Login login,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ModelAndView("login").addObject("error","Login failed....Try again");
		boolean isValidUser=userService.loadUserInfo(login);
		if(isValidUser)
			return new ModelAndView("home").addObject("registerMessage","Successfully-Login");
		else
			return new ModelAndView("login").addObject("error","Login failed....Try again");
	}
	
	@RequestMapping("/registerProcess")
	public ModelAndView registerProcess(@Valid Register register,
			BindingResult bindingResult,@RequestParam("upload") MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				register.setImage(file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(bindingResult.hasErrors()) {
			return new ModelAndView("register").addObject("error","Registration failed....Try again");
		}
		boolean saveUser = userService.saveUser(register);
		if(saveUser) {
			register.setBase64(Base64.encodeBase64String(register.getImage()));
			return new ModelAndView("registerProcess").addObject("registers",register);
		}
		else
			return new ModelAndView("register").addObject("error","Registration failed....Try again");
		
	}
	
	@RequestMapping("/accessdenied")
	public ModelAndView accessDenied() {
		return new ModelAndView("accessdenied");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		return new ModelAndView("logout");
	}
	
	
	
}
