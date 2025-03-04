package com.springboot.backend.chat.spring_boot_backend_chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.springboot.backend.chat.spring_boot_backend_chat.models.documents.Mensaje;

@Controller
public class ChatController {
    private String[] colores = { "red", "yellow", "purple", "magenta", "orange", "blue", "maroon", "fuchsia", "aqua", "silver" };
    
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje) {
        mensaje.setFecha(new Date().getTime());

        if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("Nuevo usuario");
        } else if (mensaje.getTipo().equals("MENSAJE")) {
            mensaje.setTexto(mensaje.getTexto());
        }

        return mensaje;
    }
    
    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username) {
        return username;
    }

}
