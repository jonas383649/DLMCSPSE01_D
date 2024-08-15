package com.iu.memorylearnapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memory_pair_id")
    @JsonBackReference
    private CardPair cardPair;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public CardPair getCardPair() {
        return cardPair;
    }

    public void setCardPair(final CardPair cardPair) {
        this.cardPair = cardPair;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("content=" + content)
            .toString();
    }
}