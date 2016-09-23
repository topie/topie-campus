#Topie-campus



###Database and Redis:


```
drop database if exists topie_campus;
create database topie_campus DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

redis-cli
>>
auth: redis-cli -a {redis password} keys "*" | grep topie_ | xargs redis-cli -a {redis password} del
none auth : redis-cli -a {redis password} keys "*" | grep topie_ | xargs redis-cli -a {redis password} del

```
**Redis-server** is required! 
Start redis server and edit **config/properties/redis.properties** !

Edit **/static/application/js/app.js** and set your projectName!
###Start the project and enjoy it!

2 Build-in user:
```
User - user:user
Admin - admin:admin
```

3 Welcome page
```
/static/admin/index.html
```

4. Notice!!
```
When using modal and form plugins, you must modal.show() before modal.form() to avoid the kindeditor bug!
```
