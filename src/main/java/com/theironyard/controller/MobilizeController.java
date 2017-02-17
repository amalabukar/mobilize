package com.theironyard.controller;

import com.theironyard.entities.Cause;
import com.theironyard.entities.User;
import com.theironyard.services.CauseRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by amalabukar on 2/8/17.
 */
@Controller
public class MobilizeController {

    @Autowired
    CauseRepository causes;

    @Autowired
    UserRepository users;
//
//    @Autowired
//    DonationRepository donation;
//    public static ArrayList<Donation> donations = new ArrayList<>();


    //    querying by category, maybe filter by rating
    public static ArrayList<Cause> charity = new ArrayList<>();






    @RequestMapping(path = "/")
    public String link2() {
        return "index";
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String category) throws Exception {

        String name = (String) session.getAttribute("name");

        if (name != null) {
            User user = users.findFirstByName(name);
            model.addAttribute("user", user);
        }

        List<Cause> causeList;

        if (category != null) {
            causeList = (List<Cause>) causes.findByCategory(category);
        } else {
            causeList = (List<Cause>) causes.findAll();
        }
        model.addAttribute("causes", causeList);
        model.addAttribute("name", charity);
        return "home";


    }

    @PostConstruct
    public void init() throws Exception {
        if (causes.count() == 0) {
            File f = new File("causes.csv");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] a = line.split(",");
                Cause c = new Cause(a[0], a[1], a[2], Integer.parseInt(a[3]));
                causes.save(c);
            }

        }
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String name, String password) throws Exception {
        User user = users.findFirstByName(name);
        if (user == null) {
            user = new User(name, password);
            users.save(user);
        } else if (!password.equals(user.password)) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("name", name);
        return "redirect:/home";
    }


    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
//
//    @RequestMapping(path = "/add-donation", method = RequestMethod.POST)
//    public String donate(String text) {
//        Donation d = new Donation(text);
//        donations.add(d);
//        return "redirect:/";


    public static void main(String[] args) throws Exception {
        File f = new File("causes.csv");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            while (line.startsWith("name")) {
                line = fileScanner.nextLine();
            }
            String[] columns = line.split(",");
            Cause names = new Cause(columns[1], columns[2], columns[3], Integer.parseInt(columns[4]));
            charity.add(names);

        }
        fileScanner.close();


    }

    @RequestMapping(path = "/charity")
    public String link() {
        return "charity";
    }

    @RequestMapping(path = "/donate")
    public String link1() {
        return "donate";
    }


    }
