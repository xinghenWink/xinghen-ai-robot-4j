package com.xinghen.ai.robot.controller;

import com.xinghen.ai.robot.model.AIResponse;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author: 李凯平
 * @date: 2025-10-15 16:33
 * @Description: DeepSeek聊天
 */
@RestController
@RequestMapping("v0/ai")
public class DeepSeekChatController {

    @Resource
    private DeepSeekChatModel chatModel;

    /**
     * 普通对话
     * @param message
     * @return
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatModel.call(message);
    }

    /**
     * 流式对话
     * @param message
     * @return
     */
    /*@GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt)
                .mapNotNull(chatResponse ->
                        chatResponse.getResult().getOutput().getText());
    }*/
    @GetMapping(value = "/generateStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AIResponse> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt)
                .mapNotNull(chatResponse ->{
                    String text = chatResponse.getResult().getOutput().getText();
                    return AIResponse.builder().v(text).build();
                });
    }

}
