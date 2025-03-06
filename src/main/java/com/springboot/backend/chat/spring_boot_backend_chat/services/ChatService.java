package com.springboot.backend.chat.spring_boot_backend_chat.services;

import java.util.List;

import com.springboot.backend.chat.spring_boot_backend_chat.models.documents.Mensaje;

public interface ChatService {

    public List<Mensaje> obtenerUltimos10Mensajes();
    
    public Mensaje guardarMensaje(Mensaje mensaje);
    
}
