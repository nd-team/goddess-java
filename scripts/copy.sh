#!/bin/bash
str=" ------------------ "
deployPath=deploys #部署目录与goddess-java同级
dir=$(cd `dirname $0`; pwd) #模块目录
projectRootParent=${dir%goddess-java*} #goddess-java父级目录
moduleRelativePath=${dir#*goddess-java} #模块相对goddess-java目录
distPath=$projectRootParent$deployPath$moduleRelativePath #转移后的目录
source $projectRootParent/goddess-java/scripts/console.sh
saveVersion=5
copyVersion=1

function saveHistoryVersions(){
	let overVersion=$[$saveVersion + 1]
	if [ -d $distPath"/build/"$overVersion"_libs" ];then
		rm -rf $distPath"/build/"$overVersion"_libs"
		echo " 删除超出的版本 $overVersion_libs"
	fi
	for index in `seq $saveVersion -1 1`
	do
		let ind=$[$index + 1]
		if [ -d $distPath/build/$index"_libs" ];then
			mv $distPath/build/$index"_libs" $distPath"/build/"$ind"_libs"
			echo " "$index"_libs 版本往后递增 $ind"_libs
		fi
	done
	if [ -d $distPath"/build/libs" ];then
		mv $distPath/build/libs $distPath/build/1_libs
		echo " libs 旧版本往后递增 1_libs"
	fi
}
function showList(){
	if [ ! -d $distPath/build ];then
		console error "$str $distPath/build 文件夹不存在 $str"
		exit 0
	fi
	let count=`ls -l $distPath/build | grep "^d" | wc -l`
	if [ $count -lt 2 ];then
		console warn "$str 没有版本可显示 $str"
		exit 0
	fi
	echo "$str 版本列表如下 $str"
	for name in `ls $distPath/build`;do
		if [ "$name" != "libs" ];then
			echo " "${name%_libs*}
		fi
	done
	echo "$str 版本列表end $str"
}

if [ "$1" != "$copyVersion" ];then
	console warn "$str 您的copy.sh复制脚本版本过低,请升级 $str"
	exit 0
fi
if [ "$2" = "list" ];then
	showList
	exit 0
fi
if [ ! -d "$dir/build/libs" ];then
	console error "$str $dir/build/libs 目录不存在 $str"
	exit 0
else
	mkdir -p $distPath"/build"
	echo "$str 历史版本保留中 $str"
	saveHistoryVersions
	echo "$str 历史版本保留完成 $str"
	cp -rf $dir/build/libs $distPath/build/
	echo "$str libs新版本复制完成 $str"
fi

