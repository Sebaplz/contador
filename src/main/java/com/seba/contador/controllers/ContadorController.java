package com.seba.contador.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContadorController {

    private void setContadorSession(HttpSession session, int numeroVeces) {
        session.setAttribute("contador", numeroVeces);
    }

    private int getContadorSession(HttpSession session) {
        Object sessionVal = session.getAttribute("contador");
        if (sessionVal == null){
            setContadorSession(session, 0);
            sessionVal = session.getAttribute("contador");
        }
        return (int) sessionVal;
    }

    @RequestMapping("/")
    public String index(HttpSession session) {
        int contadorActual = getContadorSession(session);
        setContadorSession(session, contadorActual + 1);
        return "index.jsp";
    }

    @RequestMapping("/contador")
    public String contador(HttpSession session, Model model){
        model.addAttribute("contador", getContadorSession(session));
        return "counter.jsp";
    }

    @RequestMapping("/reset")
    public String reset(HttpSession session){
        session.invalidate();
        return "redirect:/contador";
    }

    @RequestMapping("/incrementar")
    public String incrementar(HttpSession session){
        int contadorActual = getContadorSession(session);
        setContadorSession(session, contadorActual + 2);
        return "incrementar.jsp";
    }


}
