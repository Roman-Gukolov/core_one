package com.epam.memory.overflow.entity;

public class EntitySecond {
    private int oneValue;
    private EntityFirst clsTwoInstance = null;

    public EntitySecond() {
        oneValue = 0;
        clsTwoInstance = new EntityFirst();
    }

    public EntitySecond(int oneValue, EntityFirst clsTwoInstance) {
        this.oneValue = oneValue;
        this.clsTwoInstance = clsTwoInstance;
    }
}
