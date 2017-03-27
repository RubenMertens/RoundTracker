package com.test.domain;

/**
 * Created by Ravanys on 22/03/2017.
 */
public class RoundEntity { //todo rework logic here
    private String name;
    private Integer initiativeRoll;
    private Integer initiativeModifier;
    private Integer health;

    public RoundEntity() {
    }

    @Override
    public String toString() {
        return "RoundEntity{" +
                "name='" + name + '\'' +
                ", initiativeRoll=" + initiativeRoll +
                ", initiativeModifier=" + initiativeModifier +
                ", health=" + health +
                '}';
    }

    public RoundEntity(String name, Integer initiativeRoll, Integer initiativeModifier, Integer health) {
        this.name = name;
        this.initiativeRoll = initiativeRoll;
        this.initiativeModifier = initiativeModifier;
        this.health = health;
    }

    public Integer getFullInitiative(){
        return this.initiativeModifier + this.initiativeRoll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitiativeRoll() {
        return initiativeRoll;
    }

    public void setInitiativeRoll(Integer initiativeRoll) {
        this.initiativeRoll = initiativeRoll;
    }

    public Integer getInitiativeModifier() {
        return initiativeModifier;
    }

    public void setInitiativeModifier(Integer initiativeModifier) {
        this.initiativeModifier = initiativeModifier;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoundEntity that = (RoundEntity) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
