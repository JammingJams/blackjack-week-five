package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        Hand hand1 = new Hand();
        Hand youHandMe = new Hand();
        deck.shuffle();
// deal 1 cards
        for(int i = 0; i < 2; i++) {
// get a card from the deck
            Card card = deck.deal();
// deal that card to the hand
            hand1.deal(card);
            card = deck.deal();
            youHandMe.deal(card);
        }

        int i = 0;
        boolean userDare = true;
        String userInput = "";

        for (Card c : hand1.getCards()) {
            if (i == 0) {
                c.flip();
                System.out.println("Dealer Hand: " + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
                c.flip();
                //i++;
            }
            else System.out.println("Dealer Hand: " + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
        }

        for (Card c : youHandMe.getCards()) {
            c.flip();
            System.out.println("My Hand: " + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
            c.flip();
        }

        int handValue = youHandMe.getValue();
        System.out.println("This hand is worth " + handValue);

        while (userDare) {

            if (handValue <= 21) {
                System.out.println("What will you do? H-> HIT! S->Ends Turn");
                userInput = sc.nextLine().trim().toLowerCase();

                switch (userInput) {
                    case ("h") -> {
                        Card card = deck.deal();
                        youHandMe.deal(card);
                        handValue = youHandMe.getValue();
                        System.out.println("This hand is worth " + handValue);
                    }
                    case ("s") -> {
                        userDare = false;
                    }
                    default -> System.out.println("Hey put in S or H!");
                }
            }
            else {
                System.out.println("You busted!!!");
                userDare = false;
            }

        }
    }
}