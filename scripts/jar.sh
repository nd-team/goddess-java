#!/bin/bash
cd /storage/dockers/commons/goddess-java && /soft/gradle/bin/gradle jar -P$1 -xtest && /soft/gradle/bin/gradle build -P$1 -xtest
