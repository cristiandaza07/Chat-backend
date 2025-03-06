package com.springboot.backend.chat.spring_boot_backend_chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.backend.chat.spring_boot_backend_chat.models.documents.Mensaje;
import com.springboot.backend.chat.spring_boot_backend_chat.repositories.ChatRespository;

@Service
public class ChatServiceImpl implements ChatService {
    
    @Autowired
    private ChatRespository chatRespository;

    @Override
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatRespository.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return chatRespository.save(mensaje);
    }
    
}
