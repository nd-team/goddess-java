# goddess-java

# 删除consumer多余包命令
echo "alias del='7z d -tzip `ls | grep .jar$`  BOOT-INF/lib'" >> /etc/profile
source /etc/profile

执行 git add 之前先执行 del 命令