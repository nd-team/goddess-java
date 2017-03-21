#!/bin/bash
cd .. && gradle jar -P$1 -xtest && gradle jar -P$1 -xtest
