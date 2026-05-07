package com.pluralsight;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        Hand dealer = new Hand();
        int playerAmount = 0;
        while (true) {
            try {
                System.out.print("Yo chose how many players you want!: ");
                playerAmount = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Don't type in Strings!!!!");
                sc.nextLine();
            }
        }

        ArrayList<Hand> playerHands = new ArrayList<>();
        ArrayList<Boolean> playerDone = new ArrayList<>();

        for (int i = 0; i < playerAmount; i++) {
            playerDone.add(false);
        }

        deck.shuffle();

        for (int i = 0; i < playerAmount; i++) {
            Hand hand = new Hand();
            playerHands.add(hand);
            System.out.println("This works?");
        }
        System.out.println(playerHands.size());

        for (Hand h : playerHands) {
            for(int i = 0; i < 2; i++) {
                Card card = deck.deal();
                h.deal(card);
            }
        }

        int s = 0;
        for (Hand h : playerHands) {
            s++;
            for (Card c : h.getCards()) {
                c.flip();
                System.out.println("Player " + s + "|" + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
                c.flip();
            }
        }


        deck.shuffle();
// deal 2 cards
        for(int i = 0; i < 2; i++) {
// get a card from the deck
            Card card = deck.deal();
// deal that card to the hand
            dealer.deal(card);
        }

        while (dealer.getValue() < 17) {
            Card card = deck.deal();
            dealer.deal(card);
        }
        int i = 0;

        for (Card c : dealer.getCards()) {
            if (i == 0) {
                c.flip();
                System.out.println("Dealer Hand: " + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
                c.flip();
                i++;
            }
            else System.out.println("Dealer Hand: " + c.getSuit() + ":" + c.getValue() + "->" + c.getPointValue());
        }

        while (true) {
            boolean allDone = true;

            for (int p = 0; p < playerHands.size(); p++) {
                Hand h = playerHands.get(p);
                if (playerDone.get(p)) {
                    continue;
                }
                allDone = false;
                if (h.getValue() > 21) {
                    System.out.printf("(%d) Player Busted\n", p + 1);
                    playerDone.set(p, true);
                    continue;
                }
                System.out.printf("(%d) Hit (h) or Stand (s)?\n", p + 1);
                String input = sc.nextLine().trim().toLowerCase();
                switch (input) {
                    case "h" -> {
                        Card card = deck.deal();
                        h.deal(card);
                        System.out.println("Hand value: " + h.getValue());
                    }
                    case "s" -> {
                        playerDone.set(p, true);
                    }
                    default -> System.out.println("Invalid input");
                }
            }
            if (allDone) break;
        }
    }
}