package com.evn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.evn.services.ConfigurationService;
import com.evn.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evn.model.Configuration;
import com.evn.model.Invoice;
import com.evn.model.User;
import com.evn.repository.ConfigurationRepository;
import com.evn.repository.InvoiceRepository;
import com.evn.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private InvoiceRepository invoiceRepo;
	@Autowired
	private ConfigurationRepository configRepo;

	private ConfigurationService configurationService;
	private InvoiceService invoiceService;

	public UserController(ConfigurationService configurationService, InvoiceService invoiceService) {
		this.configurationService = configurationService;
		this.invoiceService = invoiceService;
	}

	// Đăng nhập
	@PostMapping("/login")
	public String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, Model model) {
		User user = userRepo.findByUsernameAndPassword(username, password);
		if (user != null) {// kiểm tra đăng nhập accc]ount có tồn tại?
			model.addAttribute("user", user);
			return "homepage";
		} else {
			model.addAttribute("message", "Sai tài khoản hoặc mật khẩu");
			return "index";
		}
	}

//	Gửi mail
	@GetMapping("/sendmail/{iduser}")
	public String goSendMailPage(@PathVariable("iduser") String id, Model model) {// hàm trở về trang chủ sau khi đã
																					// login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			List<Invoice> invoices = new ArrayList<>();
			invoices = invoiceService.getByStatus("Chưa hoàn thành");
			System.out.println(invoices);
			model.addAttribute("mails", invoices);
			return "mail";
		} else {
			return "index";
		}
	}

	@PostMapping("/sendmail/view")
	public String viewSendmail(@RequestParam(name = "idUser") String id,
			@RequestParam(name = "district") String district, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "time") Integer time, Model model) {// hàm trở về trang chủ sau khi đã login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			System.out.println(user);
			List<Invoice> invoices = new ArrayList<Invoice>();
			if (time <= 12) {
				invoices = invoiceService.getByStatusAndMonthAndYear("Chưa hoàn thành", time, year);
				System.out.println(invoices);
			} else if (time > 12) {

				invoices = invoiceService.getByStatusAndQuarterAndYear("Chưa hoàn thành", time - 12, year);
				System.out.println(invoices);
			}
			List<Invoice> mails = new ArrayList<Invoice>();
			for (Invoice x : invoices) {
				if (x.getCustomer().getDistrict().equals(district)) {
					mails.add(x);
					System.out.println(x);
				}
			}
			System.out.println("list mail:  " + mails);
			model.addAttribute("mails", mails);
			return "mail";
		} else {
			return "homepage";
		}
	}

	@PostMapping("/sendMail/send")
	public String sendMail(@RequestParam("idUser") String id, @RequestParam("idInvoice") String idIn, Model model) {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
//			System.out.println(user);
			model.addAttribute("user", user);
			Optional<Invoice> in = invoiceRepo.findById(idIn);
			if (in.isPresent()) {
				Invoice inv = in.get();
				inv.setStatus("Đã hoàn thành");
				invoiceRepo.save(inv);
				List<Invoice> invoices = new ArrayList<>();
				invoices = invoiceService.getByStatus("Chưa hoàn thành");
				System.out.println(invoices);
				model.addAttribute("mails", invoices);
				return "mail";
			}

		}
			return "homepage";
		

	}

//	Xuất báo cáo
	@GetMapping("/report/{iduser}")
	public String goReportPage(@PathVariable("iduser") String id, Model model) {// hàm trở về trang chủ sau khi đã login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			return "baocao";
		} else {
			return "index";
		}
	}

	@PostMapping("/report/view")
	public String viewReport(@RequestParam(name = "idUser") String id, @RequestParam(name = "district") String district,
			@RequestParam(name = "year") Integer year, @RequestParam(name = "status") Integer status,
			@RequestParam(name = "time") Integer time, Model model) {// hàm trở về trang chủ sau khi đã login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			System.out.println(user);
			List<Invoice> invoices = new ArrayList<Invoice>();
			if (time <= 12) {
				if (status == 2) {
					invoices = invoiceService.getByStatusAndMonthAndYear("Chưa hoàn thành", time, year);
					System.out.println(invoices);
				}
				if (status == 3) {
					invoices = invoiceService.getByStatusAndMonthAndYear("Đã hoàn thành", time, year);
					System.out.println(invoices);
				}
				if (status == 1) {
					invoices = invoiceService.getByMonthAndYear(time, year);
					System.out.println(invoices);
				}
			} else {
				if (status == 2) {
					invoices = invoiceService.getByStatusAndQuarterAndYear("Chưa hoàn thành", time - 12, year);
					System.out.println(invoices);
				}
				if (status == 3) {
					invoices = invoiceService.getByStatusAndQuarterAndYear("Đã hoàn thành", time - 12, year);
					System.out.println(invoices);
				}
				if (status == 1) {
					invoices = invoiceService.getByQuarterAndYear(time - 12, year);
					System.out.println(invoices);
				}
			}
			List<Invoice> reports = new ArrayList<Invoice>();
			for (Invoice x : invoices) {
				if (x.getCustomer().getDistrict().equals(district)) {
					reports.add(x);
					System.out.println(x);
				}
			}
			System.out.println("list report:  " + reports);
			model.addAttribute("reports", reports);
			return "baocao";
		} else {
			return "index";
		}
	}

//	Theo dõi danh sách
	@GetMapping("/follow/{iduser}")
	public String goFollowPage(@PathVariable("iduser") String id, Model model) {// hàm trở về trang chủ sau khi đã login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
//			System.out.println(user);
			model.addAttribute("user", user);
			return "follow";
		} else {
			return "index";
		}
	}

	@PostMapping("/follow/view")
	public String viewFollow(@RequestParam(name = "idUser") String id, @RequestParam(name = "district") String district,
			@RequestParam(name = "year") Integer year, @RequestParam(name = "time") Integer time, Model model) {// hàm
																												// trở
																												// về
																												// trang
																												// chủ
																												// sau
																												// khi
																												// đã
																												// login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			System.out.println(user);
			List<Invoice> invoices = new ArrayList<Invoice>();
			if (time <= 12) {
				invoices = invoiceService.getByStatusAndMonthAndYear("Chưa hoàn thành", time, year);
				System.out.println(invoices);
			} else if (time > 12) {

				invoices = invoiceService.getByStatusAndQuarterAndYear("Chưa hoàn thành", time - 12, year);
				System.out.println(invoices);
			}
			List<Invoice> follows = new ArrayList<Invoice>();
			for (Invoice x : invoices) {
				if (x.getCustomer().getDistrict().equals(district)) {
					follows.add(x);
					System.out.println(x);
				}
			}
			System.out.println("list follow:  " + follows);
			model.addAttribute("follows", follows);
			return "follow";
		} else {
			return "homepage";
		}
	}

//	Danh sách cấu hình
	@GetMapping("/config/{iduser}")
	public String goConfigPage(@PathVariable("iduser") String id, Model model) {// hàm trở về trang chủ sau khi đã login
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			List<Configuration> configs = new ArrayList<>();
			configs = configRepo.findAll();
			System.out.println(configs);
			model.addAttribute("configs", configs);
			return "cauhinh";
		} else {
			return "homepage";
		}
	}

//	Thêm cấu hình
	@GetMapping("/config/{iduser}/addConfig/view")
	public String viewToAddConfig(@PathVariable("iduser") String id, Model model) {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			model.addAttribute("config", new Configuration());
			return "viewtoaddconfig";
		}
		return "homepage";
	}

	@PostMapping("/config/{iduser}/addConfig/add")
	public String addConfig(@PathVariable("iduser") String id, @RequestParam("creator") String creator,
			@RequestParam("dateCreate") String dateCreate,

			@RequestParam("price") Float price, @RequestParam("subject") String subject, Model model)
			throws ParseException {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Configuration c = new Configuration();
			c.setCreator(creator);
			c.setDateCreate(formatter.parse(dateCreate));
//			c.setEditor(editor);
//			c.setDateEdit(formatter.parse(dateEdit));
			c.setPrice(price);
			c.setSubject(subject);
			configRepo.save(c);
			List<Configuration> list = new ArrayList<>();
			list = configRepo.findAll();
			System.out.println(list);
			model.addAttribute("configs", list);
			return "deleteconfigsuccess";
		}
		return "homepage";
	}

//	Xóa cấu hình
	@GetMapping("/config/{iduser}/delConfig/view/{idConfiguration}")
	public String viewToDelConfig(@PathVariable("iduser") String id, @PathVariable("idConfiguration") int idCon,
			Model model) {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			Optional<Configuration> con = configRepo.findById(idCon);
			if (con.isPresent()) {
				System.out.println(con);
				model.addAttribute("config", con.get());
				return "viewtodelconfig";
			}
		}
		return "homepage";
	}

	@PostMapping("/config/{iduser}/delConfig/del/{idConfiguration}")
	public String delConfig(@PathVariable("iduser") String id, @RequestParam("idConfiguration") int idCon,
			Model model) {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			Optional<Configuration> con = configRepo.findById(idCon);
			if (con.isPresent()) {

				configRepo.deleteById(idCon);
				List<Configuration> list = new ArrayList<>();
				list = configRepo.findAll();
				System.out.println(list);
				model.addAttribute("configs", list);
				return "deleteconfigsuccess";
			}
		}
		return "homepage";
	}

//	Sửa cấu hình
	@GetMapping("/config/{iduser}/editConfig/view/{idConfiguration}")
	public String viewToEditConfig(@PathVariable("iduser") String id, @PathVariable("idConfiguration") int idCon,
			Model model) {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			Optional<Configuration> con = configRepo.findById(idCon);
			if (con.isPresent()) {
				System.out.println(con);
				model.addAttribute("config", con.get());
				return "viewtoeditconfig";
			}
		}
		return "homepage";
	}

	@PostMapping("/config/{iduser}/editConfig/edit/{idConfiguration}")
	public String editConfig(@PathVariable("iduser") String id, @PathVariable("idConfiguration") int idCon,
			@RequestParam("creator") String creator, @RequestParam("dateCreate") String dateCreate,
			@RequestParam("editor") String editor, @RequestParam("dateEdit") String dateEdit,
			@RequestParam("price") Float price, @RequestParam("subject") String subject, Model model)
			throws ParseException {
		Optional<User> optuser = userRepo.findById(id);
		if (optuser.isPresent()) {
			User user = optuser.get();
			model.addAttribute("user", user);
			Optional<Configuration> con = configRepo.findById(idCon);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			if (con.isPresent()) {
				Configuration c = con.get();
				c.setCreator(creator);
				c.setDateCreate(formatter.parse(dateCreate));
				c.setEditor(editor);
				c.setDateEdit(formatter.parse(dateEdit));
				c.setPrice(price);
				c.setSubject(subject);
				configRepo.save(c);
				List<Configuration> list = new ArrayList<>();
				list = configRepo.findAll();
				System.out.println(list);
				model.addAttribute("configs", list);
				return "deleteconfigsuccess";
			}
		}
		return "homepage";
	}
}
