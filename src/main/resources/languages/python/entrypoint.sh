#!/bin/bash

#  python3 -c "import py_compile; py_compile.compile(r'main.py')"
time timeout 17 python3 main.py < input.txt
