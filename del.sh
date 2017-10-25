#/bin/bash
for name in `ls modules`
do
	for ww in `ls modules/$name`
	do
		rm -rf "modules/$name/out"
		#echo "modules/$name/$ww/out"
		#echo  "modules/$name/out"
	done
done
