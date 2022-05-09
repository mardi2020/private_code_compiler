#!/bin/bash


javac -J-Xms1024m -J-Xmx1024m -J-Xss512m -encoding UTF-8 main.java && time timeout 10 java main < input.txt