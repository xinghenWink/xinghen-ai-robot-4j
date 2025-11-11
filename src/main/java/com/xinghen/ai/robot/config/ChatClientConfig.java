package com.xinghen.ai.robot.config;

import com.xinghen.ai.robot.advisor.MyLoggerAdvisor;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 李凯平
 * @date: 2025-10-15 17:05
 * @Description: 勇敢去做！
 */
@Configuration
public class ChatClientConfig {

    @Resource
    private ChatMemory chatMemory;

    /**
     * 初始化 ChatClient 客户端
     * @param chatModel
     * @return
     */
    @Bean
    public ChatClient chatClient(DeepSeekChatModel chatModel, ToolCallbackProvider tools) {
        return ChatClient.builder(chatModel)
                .defaultToolCallbacks(tools) // MCP
//                .defaultSystem("请你扮演一名星痕 Java 项目实战专栏的客服人员")
                .defaultAdvisors(new SimpleLoggerAdvisor(), // 添加 Spring AI 内置的日志记录功能
//                                 new MyLoggerAdvisor(), // 添加自定义的日志打印 Advisor
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }

    /**
     * 初始化ChatModel客户端
     * @param chatModel
     * @return
     */
    /*@Bean
    public ChatClient chatClient(DeepSeekChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient.builder(chatModel)
//                .defaultSystem("请你扮演一名星痕 Java 项目实战专栏的客服人员")
                .defaultAdvisors(new SimpleLoggerAdvisor(), // 添加 Spring AI 内置的日志记录功能
//                                new MyLoggerAdvisor(), // 添加自定义的日志打印 Advisor
                                MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }*/
}
