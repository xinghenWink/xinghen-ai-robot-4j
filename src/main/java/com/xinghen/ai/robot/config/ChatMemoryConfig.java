package com.xinghen.ai.robot.config;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.cassandra.CassandraChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 李凯平
 * @date: 2025-10-15 17:43
 * @Description: 勇敢去做！
 */
@Configuration
public class ChatMemoryConfig {

    /**
     * 记忆存储
     */
    /*@Resource
    private ChatMemoryRepository chatMemoryRepository;*/

    @Resource
    private CassandraChatMemoryRepository chatMemoryRepository;

    /**
     * 初始化一个 ChatMemory 实例，并注入到 Spring 容器中
     */
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(50) // 最大消息窗口为 50，默认值为 20
                .chatMemoryRepository(chatMemoryRepository) // 记忆存储
                .build();
    }
}
