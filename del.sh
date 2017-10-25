#/bin/bash
for name in `ls modules`
do
	for ww in `ls modules/$name`
	do
		rm -rf "modules/$name/$ww/build"
		#echo "modules/$name/$ww/out"
		
	done
done
