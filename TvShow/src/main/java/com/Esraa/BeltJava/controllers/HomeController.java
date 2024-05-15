package com.Esraa.BeltJava.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.Esraa.BeltJava.models.LoginUser;
import com.Esraa.BeltJava.models.Rating;
import com.Esraa.BeltJava.models.Show;
import com.Esraa.BeltJava.models.User;
import com.Esraa.BeltJava.services.RatingService;
import com.Esraa.BeltJava.services.ShowService;
import com.Esraa.BeltJava.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userServ;

    @Autowired
    private ShowService showServ;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("user_id") == null) {
            model.addAttribute("newUser", new User());
            model.addAttribute("newLogin", new LoginUser());
            return "forms.jsp";
        } else {
            return "redirect:/shows";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if (session.getAttribute("user_id") != null) {
            session.removeAttribute("user_id");
            // session.invalidate();//destroy all
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "forms.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/shows";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "forms.jsp";
        }

        session.setAttribute("user_id", user.getId());
        return "redirect:/shows";
    }

    @GetMapping("/shows")
    public String welcome(Model model, HttpSession session) {
        if (session.getAttribute("user_id") != null) {
            User user = userServ.findUser((Long) session.getAttribute("user_id"));
            List<Show> shows = showServ.allShows();
            // ratingService.count(sho)

            model.addAttribute("function", ratingService);
            model.addAttribute("User", user);
            model.addAttribute("shows", shows);

            return "index.jsp";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/show/add") // add
    public String create(@Valid @ModelAttribute("Show") Show show,
            BindingResult result, Model model,
            HttpSession session) {
        if (session.getAttribute("user_id") != null) {
            if (showServ.titlesShow(show.getTitle()).size() > 0) {
                result.rejectValue("title", "Unique", "This title is already in use!");
            }
            if (result.hasErrors()) {
                return "add.jsp";
            } else {
                User user = userServ.findUser((Long) session.getAttribute("user_id"));
                show.setUser(user);
                showServ.createShow(show);
                return "redirect:/shows";

            }
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/show/new") // add form
    public String New(@ModelAttribute("Show") Show show, BindingResult result, Model model,
            HttpSession session) {
        if (session.getAttribute("user_id") != null) {

            return "add.jsp";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/show/{id}/edit") // edit form
    public String edit(@PathVariable("id") Long id, @ModelAttribute("Show") Show showinfo,
            BindingResult result,
            Model model, HttpSession session) {
        User user = userServ.findUser((Long) session.getAttribute("user_id"));
        Show show = showServ.findShow(id);
        if (session.getAttribute("user_id") != null && show.getUser() == user) {

            model.addAttribute("id", id);
            model.addAttribute("User", user);
            model.addAttribute("tvshow", show);
            model.addAttribute("Show", show);
            return "edit.jsp";
        } else {
            return "redirect:/";
        }
    }

    @PutMapping("/show/{id}/edit") // update
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("Show") Show showinfo,
            BindingResult result, HttpSession session, Model model) {
        User user = userServ.findUser((Long) session.getAttribute("user_id"));
        Show show = showServ.findShow(id);
        if (session.getAttribute("user_id") != null && show.getUser() == user) {
            if (result.hasErrors()) {
                model.addAttribute("id", id);
                model.addAttribute("tvshow", show);

                model.addAttribute("User", user);
                return "edit.jsp";
            } else {
                showServ.updateShow(showinfo, id);
                return "redirect:/shows";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/show/{id}") // details page
    public String details(@PathVariable("id") Long id, Model model, HttpSession session,
            @ModelAttribute("rating") Rating rate, BindingResult result) {
        if (session.getAttribute("user_id") != null) {
            Show show = showServ.findShow(id);
            model.addAttribute("show", show);
            User user = userServ.findUser((Long) session.getAttribute("user_id"));
            model.addAttribute("User", user);
            model.addAttribute("id", id);
            List<Rating> rating = ratingService.allRatingsForShow(show);
            model.addAttribute("ratings", rating);
            return "details.jsp";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/show/{id}/rate") // ratings page
    public String Ratings(@PathVariable("id") Long id, @Valid @ModelAttribute("rating") Rating rating,
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.findUser((Long) session.getAttribute("user_id"));
        Show show = showServ.findShow(id);

        if (session.getAttribute("user_id") != null) {
            if (result.hasErrors()) {
                model.addAttribute("id", id);
                model.addAttribute("show", show);
                model.addAttribute("id", id);
                List<Rating> listRating = ratingService.allRatingsForShow(show);
                model.addAttribute("ratings", listRating);

                return "details.jsp";
            } else {
                // add
                ratingService.createRating(rating, user, show);
                return "redirect:/show/" + id;
            }
        } else {
            return "redirect:/show/" + id;
        }
    }



    @DeleteMapping("/Show/{id}") // del
    public String destroy(@PathVariable("id") Long id,Model model) {
//    	Show show = showServ.findShow(id);
//    	show.setRatings(null);
//    	showServ.updateShow(show, id);
        showServ.deleteShow(id);
        return "redirect:/shows";
    }

}
