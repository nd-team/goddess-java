#!/bin/bash

function console(){
	if [ "$1" = "error" ];then
		echo -e "\033[31m"$2"\033[0m"
	elif [ "$1" = "warn" ];then
		echo -e "\033[33m"$2"\033[0m"
	fi
}
