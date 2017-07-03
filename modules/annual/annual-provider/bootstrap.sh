#!/bin/bash
dir=$(cd `dirname $0`; pwd)
projectRoot=${dir%goddess-java*}
source $projectRoot"goddess-java/"scripts/provider.sh 1 $*

