#Topie-campus



###First create database:


```
drop database if exists topie_campus;
create database topie_campus DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

redis-cli
>>
del topie_user_{用户登录名}
del topie_fun_key_{用户登录名}
del topie_role_key
```
**Redis-server** is required! 
Start the redis server and configure **config/properties/redis.properties** !

###Start the project and enjoy it!

2 Build-in user:
```
User - user:user
Admin - admin:admin
```

3 Welcome page
```
/static/index.html
```
