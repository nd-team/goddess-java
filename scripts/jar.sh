#!/bin/bash
cd /storage/dockers/commons/goddess-java && /soft/gradle/bin/gradle jar -Pdev -xtest && /soft/gradle/bin/gradle build -Pdev -xtest
