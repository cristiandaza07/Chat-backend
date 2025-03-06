package com.springboot.backend.chat.spring_boot_backend_chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.springboot.backend.chat.spring_boot_backend_chat.models.documents.Mensaje;
import com.springboot.backend.chat.spring_boot_backend_chat.services.ChatService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "http://localhost:4200" })
@Controller
public class ChatController {
    private String[] colores = { "red", "yellow", "purple", "magenta", "orange", "blue", "maroon", "fuchsia", "aqua",
            "silver" };
    
    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje) {
        mensaje.setFecha(new Date().getTime());

        if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("Nuevo usuario");
        } else if (mensaje.getTipo().equals("MENSAJE")) {
            //mensaje.setTexto(mensaje.getTexto());
            chatService.guardarMensaje(mensaje);
        }

        return mensaje;
    }
    
    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username) {
        return username;
    }
    
    @MessageMapping("/historial")
    public void historial(String clientId) {
        simpMessagingTemplate.convertAndSend("/chat/historial/" + clientId, chatService.obtenerUltimos10Mensajes());
    }

}
