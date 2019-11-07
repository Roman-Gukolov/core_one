package com.epam.memory.overflow.entity;

public class EntityFirst {
    private int oneValue;
    private EntitySecond clsTwoInstance = null;

    public EntityFirst() {
        oneValue = 0;
        clsTwoInstance = new EntitySecond();
    }

    public EntityFirst(int oneValue, EntitySecond clsTwoInstance) {
        this.oneValue = oneValue;
        this.clsTwoInstance = clsTwoInstance;
    }
}
