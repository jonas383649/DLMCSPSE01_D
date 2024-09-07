package com.iu.memorylearnapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Entity that represents the database object of a memory card set.
 */
@Entity
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CardPair> cardPairs;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    private String name;

    public CardSet() {
        this(null);
    }

    public CardSet(final String name) {
        this(name, new ArrayList<>());
    }

    public CardSet(final String name, final List<CardPair> cardPairs) {
        statistic = new Statistic(this);
        this.cardPairs = cardPairs;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CardPair> getCardPairs() {
        return cardPairs;
    }

    public void setCardPairs(final List<CardPair> cardPairs) {
        this.cardPairs = cardPairs;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(final Statistic statistic) {
        this.statistic = statistic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CardSet.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("name=" + name)
            .add("cardPairs=" + cardPairs)
            .add("statistic=" + statistic)
            .toString();
    }
}
