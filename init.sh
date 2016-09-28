#!/usr/bin/env bash
mysql -uroot -proot -e "drop database if exists topie_campus;"
mysql -uroot -proot -e "create database topie_campus DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;"
//redis-cli -a 123456 keys "*" | grep topie_ | xargs redis-cli -a 123456 del
