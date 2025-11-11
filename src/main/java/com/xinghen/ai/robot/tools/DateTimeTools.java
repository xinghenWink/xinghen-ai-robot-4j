package com.xinghen.ai.robot.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDateTime;
/**
 * @author: 李凯平
 * @date: 2025-10-23 15:36
 * @Description: 日期Tool
 */
@Slf4j
public class DateTimeTools {

    @Tool(description = "获取当前日期和时间")
    String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

}
