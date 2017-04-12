# goddess-java

# 删除consumer多余包命令
vi ~/.zshrc
alias del='7z d -tzip `ls | grep .jar$`  BOOT-INF/lib'
vi /etc/profile 
alias del='7z d -tzip `ls | grep .jar$`  BOOT-INF/lib'
重启电脑生效

执行 git add 之前先执行 del 命令