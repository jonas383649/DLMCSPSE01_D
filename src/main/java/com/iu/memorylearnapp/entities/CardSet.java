package com.iu.memorylearnapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.StringJoiner;

@Entity
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CardPair> cardPairs;

    private String name;

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
            .toString();
    }
}
