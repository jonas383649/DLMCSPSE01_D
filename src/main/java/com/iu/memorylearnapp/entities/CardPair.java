package com.iu.memorylearnapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
public class CardPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_set_id")
    @JsonBackReference
    private CardSet cardSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_card_id")
    @JsonManagedReference
    private Card firstCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_card_id")
    @JsonManagedReference
    private Card secondCard;

    public CardPair() {
    }

    public CardPair(final Card firstCard, final Card secondCard) {
        this.firstCard = firstCard;
        this.secondCard = secondCard;
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

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(final Card firstCard) {
        this.firstCard = firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(final Card secondCard) {
        this.secondCard = secondCard;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CardPair.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("firstCard=" + firstCard)
            .add("secondCard=" + secondCard)
            .toString();
    }
}