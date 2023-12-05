package com.example.carbook.service.impl;

import com.example.carbook.model.dto.MessageDTO;
import com.example.carbook.model.entity.MessageEntity;
import com.example.carbook.repo.MessageRepository;
import com.example.carbook.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(ModelMapper modelMapper, MessageRepository messageRepository) {
        this.modelMapper = modelMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(MessageDTO messageDTO) {
        MessageEntity messageEntity = modelMapper.map(messageDTO, MessageEntity.class);

        messageRepository.save(messageEntity);
    }


//    private MessageEntity map(MessageDTO messageDTO) {
//        MessageEntity messageEntity = new MessageEntity();
//        messageEntity.setMessage(messageDTO.message());
//        messageEntity.setEmailOfUser(messageDTO.emailOfUser());
//        messageEntity.setSubject(messageDTO.subject());
//        messageEntity.setNameOfUser(messageDTO.nameOfUser());
//        messageEntity.setId(messageDTO.id());
//
//        return messageEntity;
//    }
}
