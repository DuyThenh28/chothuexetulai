package com.thuexe.thuexetulai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thuexe.thuexetulai.model.Booking;
import com.thuexe.thuexetulai.model.User;
import com.thuexe.thuexetulai.repository.BookingRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    // 👉 CHECK ADMIN (tái sử dụng)
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && "ADMIN".equals(user.getRole());
    }

    // 👉 TRANG ADMIN
    @GetMapping("/admin")
    public String adminPage(HttpSession session){

        if(!isAdmin(session)){
            return "redirect:/login";
        }

        return "admin";
    }

    // 👉 DANH SÁCH BOOKING
    @GetMapping("/admin/bookings")
    public String bookings(HttpSession session, Model model){

        User user = (User) session.getAttribute("user");

        if(user == null){
            return "redirect:/login";
        }

        if(!"ADMIN".equals(user.getRole())){
            return "redirect:/";
        }

        model.addAttribute("bookings", bookingRepository.findAll());

        return "admin/admin-bookings";
    }

    // 👉 DUYỆT ĐƠN
    @GetMapping("/admin/bookings/approve/{id}")
    public String approve(@PathVariable Long id, HttpSession session){

        if(!isAdmin(session)){
            return "redirect:/login";
        }

        Booking b = bookingRepository.findById(id).orElse(null);

        if(b != null){
            b.setStatus("APPROVED");
            bookingRepository.save(b);
        }

        return "redirect:/admin/bookings";
    }

    // 👉 (OPTIONAL) TỪ CHỐI
    @GetMapping("/admin/bookings/reject/{id}")
    public String reject(@PathVariable Long id, HttpSession session){

        if(!isAdmin(session)){
            return "redirect:/login";
        }

        Booking b = bookingRepository.findById(id).orElse(null);

        if(b != null){
            b.setStatus("REJECTED");
            bookingRepository.save(b);
        }

        return "redirect:/admin/bookings";
    }
}