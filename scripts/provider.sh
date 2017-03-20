#!/bin/bash
deployPath=deploys #部署目录与goddess-java同级
dir=$(cd `dirname $0`; pwd) #模块目录
projectRootParent=${dir%goddess-java*} #goddess-java父级目录
moduleRelativePath=${dir#*goddess-java} #模块相对goddess-java目录
distPath=$projectRootParent$deployPath$moduleRelativePath #转移后的目录
str=" ------------------ "
if [ "$2" = "help" ];then
	echo "默认  程序已经启动则杀掉重新启动,未启动则帮助启动,并保留历史版本."
	echo "stop -f  停止已经启动中的程序[-f 为强制停止]."
	echo "nlog  程序运行,不查看日志输出."
	echo "run   运行本地程序版本,[run 1]."
	echo "help  使用帮助."
	exit 0
fi
if [ $1 -ne 1 ];then
	echo $str"您的bootstrap.sh部署脚本版本过低,请升级"$str
	exit 0
fi
function stopApp(){
	echo $str"尝试停止$moduleRelativePath程序"$str
	mName=${moduleRelativePath##*/}
	ps -ef | grep "$mName" | grep -v "grep" >> /dev/null
	if [ $? -eq 0 ];then
		if [ "$1" = "-f" ];then
			kill -9 `ps -ef | grep $mName | grep -v "grep" | awk '{print $2}'` >> /dev/null
			echo $str$moduleRelativePath" 程序强制停止成功"$str
		else
			kill `ps -ef | grep $mName | grep -v "grep" | awk '{print $2}'` >> /dev/null
			echo $str$moduleRelativePath" 程序停止成功"$str
		fi
	else
		echo $str$moduleRelativePath" 程序未启动"$str
	fi
}
if [ "$2" = "run" ];then
	if [ $# -eq 2 ];then
		echo $str"请选择要运行的版本号"$str
		exit 0
	fi
	if [ "$3" -gt 0 ] && [ "$3" -lt 6 ] 2>/dev/null ; then
		echo $str"您选择运行第 $3 个版本"$str	
	else
		echo $str"版本号只能为1~5,请检查"$str
		exit 0
	fi
	libPath=$distPath"/"build/$3""_libs
else
	if [ "$2" = "list" ];then
		source $projectRootParent"goddess-java/"scripts/copy.sh 1 $2
	elif [ "$2" = "" ];then
		stopApp -f
		echo $str"停止程序"$str
		source $projectRootParent"goddess-java/"scripts/copy.sh 1 $*
	fi
	libPath=$distPath"/"build/libs
fi
if [ ! -d $distPath"/build" ];then
	echo $str"$distPath/build 目录下没有可运动jar包"$str
	exit 0
fi
if [ ! -d "$libPath" ];then
	echo $str$libPath"目录不存在"$str
	exit 0
fi
jarPath=`ls $libPath/*.jar`
if [ "$2" = "stop" ];then
	stopApp $3
else
	ps -ef | grep $jarPath | grep -v "grep" >> /dev/null
	if [ $? -eq 0 ];then
		kill `ps -ef | grep $jarPath | grep -v "grep" | awk '{print $2}'` >> /dev/null
		if [ "$1" != "nlog" ];then
			java -jar $jarPath
		else
			java -jar $jarPath >> /dev/null
		fi
	else
		if [ "$2" != "nlog" ];then
			java -jar $jarPath
		else
			java -jar $jarPath >> /dev/null
		fi
	fi
fi


