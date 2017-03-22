#!/bin/bash
cd .. && /soft/gradle/bin/gradle jar -P$1 -xtest && /soft/gradle/bin/gradle jar -P$1 -xtest
