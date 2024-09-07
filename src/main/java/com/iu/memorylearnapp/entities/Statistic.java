package com.iu.memorylearnapp.entities;

import jakarta.persistence.*;

import java.util.StringJoiner;

/**
 * Entity that represents the database object of a memory statistic entity related to a specific card set.
 */
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    private double lastTime;

    private int lastMoves;

    private double bestTime;

    private int bestMoves;

    private double avgTime;

    private int avgMoves;

    private int repetitions;

    public Statistic() {
        this(null);
    }

    public Statistic(final CardSet cardSet) {
        this.cardSet = cardSet;
        this.lastTime = 0;
        this.lastMoves = 0;
        this.bestTime = 0;
        this.bestMoves = 0;
        this.avgTime = 0;
        this.avgMoves = 0;
        this.repetitions = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public double getLastTime() {
        return lastTime;
    }

    public void setLastTime(final double lastTime) {
        this.lastTime = lastTime;
    }

    public int getLastMoves() {
        return lastMoves;
    }

    public void setLastMoves(final int lastMoves) {
        this.lastMoves = lastMoves;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(final double bestTime) {
        this.bestTime = bestTime;
    }

    public int getBestMoves() {
        return bestMoves;
    }

    public void setBestMoves(final int bestMoves) {
        this.bestMoves = bestMoves;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(final double avgTime) {
        this.avgTime = avgTime;
    }

    public int getAvgMoves() {
        return avgMoves;
    }

    public void setAvgMoves(final int avgMoves) {
        this.avgMoves = avgMoves;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(final int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Statistic.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("lastTime=" + lastTime)
            .add("lastMoves=" + lastMoves)
            .add("bestTime=" + bestTime)
            .add("bestMoves=" + bestMoves)
            .add("avgTime=" + avgTime)
            .add("avgMoves=" + avgMoves)
            .add("repetitions=" + repetitions)
            .toString();
    }
}
