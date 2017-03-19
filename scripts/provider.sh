#!/bin/bash
dir=$(cd `dirname $0`; pwd)
if [ $1 -ne 1 ];then
	echo "您的bootstrap.sh部署脚本版本过低,请升级."
	exit 0
fi
if [ ! -d $dir"/build/libs" ];then
	echo "$dir/build/libs 目录下没有可运动jar包."
	exit 0
fi
javaName=`ls $dir"/"build/libs/*.jar`
if [ "$2" = "help" ];then
	echo "默认  程序已经启动则杀掉重新启动,未启动则帮助启动."
	echo "stop  停止已经启动中的程序."
	echo "nlog  程序运行,不查看日志输出."
	echo "help  使用帮助."
	exit 0
fi
if [ "$2" = "stop" ];then
	ps -ef | grep $javaName | grep -v "grep" >> /dev/null
	if [ $? -eq 0 ];then
		kill `ps -ef | grep $javaName | grep -v "grep" | awk '{print $2}'` >> /dev/null
		echo $javaName" 程序停止成功."
	else
		echo $javaName" 程序未启动."
	fi
else
	ps -ef | grep $javaName | grep -v "grep" >> /dev/null
	if [ $? -eq 0 ];then
		kill `ps -ef | grep $javaName | grep -v "grep" | awk '{print $2}'` >> /dev/null
		if [ "$1" != "nlog" ];then
			java -jar $javaName
		else
			java -jar $javaName >> /dev/null
		fi
		echo $javaName" 程序重新启动."
	else
		if [ "$2" != "nlog" ];then
			java -jar $javaName
		else
			java -jar $javaName >> /dev/null
		fi
		echo $javaName" 程序启动成功."
	fi
fi


