package com.test.websockets.messages;

import com.test.domain.Condition;

/**
 * Ruben Mertens
 * 06/04/2017
 */
public class ConditionWrapper {
    private String name;
    private Condition condition;

    public ConditionWrapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
