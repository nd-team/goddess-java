#/bin/bash
for name in `ls modules`
do
	for ww in `ls modules/$name`
	do
		rm -rf "modules/$name/$ww/build"
		#echo "modules/$name/$ww/out"
		#git -rf --cached "modules/$name/$ww/src/main/profile/"
		
	done
done
