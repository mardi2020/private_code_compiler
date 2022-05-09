#!/bin/bash


g++ main.cpp -O2 -o main -std=gnu++17 && time timeout 5 ./main < input.txt