#!/bin/bash
dir=$(cd `dirname $0`; pwd)
projectRoot=${dir%goddess-java*}
if [ "$1" != "stop" ];then
	source $projectRoot"goddess-java/"scripts/provider.sh 1 stop
fi
source $projectRoot"goddess-java/"scripts/provider.sh 1 $*

