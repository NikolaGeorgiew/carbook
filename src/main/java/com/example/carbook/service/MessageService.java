package com.example.carbook.service;

import com.example.carbook.model.dto.MessageDTO;
import com.example.carbook.model.entity.MessageEntity;

public interface MessageService {

    void save(MessageDTO messageDTO);
}
