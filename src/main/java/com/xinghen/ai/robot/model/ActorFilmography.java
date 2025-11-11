package com.xinghen.ai.robot.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author: 李凯平
 * @date: 2025-10-22 11:30
 * @Description: 演员 - 电影集合
 */
@JsonPropertyOrder({"actor", "movies"})
public record ActorFilmography(String actor, List<String> movies) {
}
