package com.epam.spring.basic.entity;

public class EntityB {
    private EntityA entityA;

    public EntityB(EntityA entityA) {
        this.entityA = entityA;
    }

    public EntityA getEntityA() {
        return entityA;
    }

    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }
}
