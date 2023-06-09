package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String showUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users/allUsers";
	}

	@GetMapping("/newUser")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "users/newUser";
	}

	@PostMapping("/allUsers")
	public String create(@ModelAttribute("user") User user) {
		userService.createUser(user);
		return "redirect:";
	}

	@GetMapping("users/{id}")
	public String readUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.readUser(id));
		return "users/show";
	}

	@GetMapping("users/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.readUser(id));
		return "users/updateUser";
	}
	@PatchMapping("users/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userService.updateUser(user,id);
		return "redirect:";
	}
	@DeleteMapping("users/{id}")
	public String  deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "redirect:";
	}
}
