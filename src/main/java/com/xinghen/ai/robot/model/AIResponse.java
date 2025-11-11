package com.xinghen.ai.robot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 李凯平
 * @date: 2025-10-17 09:58
 * @Description: Ai对话响应类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {
    // 流式响应内容
    private String v;
}
