package com.example.lab10_spring;

public record Rectangle(float x, float y,
                        float width, float height,
                        String color) {}

// Wygeneruj konstruktor, akcesory oraz mutatory do wszystkich pól.
//Utwórz klasę Rectangle składającą się z
// całkowitych parametrów: położenia x, położenia y,
// szerokości, wysokości oraz koloru wyrażonego napisem.