MY APP

A sample spring + mybatis + spring security + rest api web app!

2 build-in user:
```
User - user:user
Admin - admin:admin
```

Step 1:
```
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"admin"}' http://localhost:8080/api/auth
```

response:
```
{"code":200,"token":"eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjpbeyJhdXRob3JpdHkiOiIxIn0seyJhdXRob3JpdHkiOiIyIn1dLCJjcmVhdGVkIjoxNDY3MDgxMTI4MjQ2LCJleHAiOjE0Njc2ODU5MjgsInVzZXIiOiJhZG1pbiJ9.vy_EWXAr8iduuDqVInjICyLngv8is90pEUNaR7V8Ok4ipsgQPrXN4yCxH7PjixJKcwc-YPWXYlpuKji6oruZBg","data":null}
```

Step 2:
```
curl -i -H "Content-Type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjpbeyJhdXRob3JpdHkiOiIxIn0seyJhdXRob3JpdHkiOiIyIn1dLCJjcmVhdGVkIjoxNDY3MDgxMTI4MjQ2LCJleHAiOjE0Njc2ODU5MjgsInVzZXIiOiJhZG1pbiJ9.vy_EWXAr8iduuDqVInjICyLngv8is90pEUNaR7V8Ok4ipsgQPrXN4yCxH7PjixJKcwc-YPWXYlpuKji6oruZBg" -X GET http://localhost:8080/api/protected
```

response:
```
{"code":200,"message":"YEAH!","data":null}
```
