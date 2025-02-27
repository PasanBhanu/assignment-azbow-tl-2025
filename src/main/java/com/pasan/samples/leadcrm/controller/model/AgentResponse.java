package com.pasan.samples.leadcrm.controller.model;

import com.pasan.samples.leadcrm.repository.model.Agent;

public record AgentResponse(
        Integer id,
        String name
) {
    public static AgentResponse of(Agent agent) {
        return new AgentResponse(
                agent.getId(),
                agent.getName()
        );
    }
}
