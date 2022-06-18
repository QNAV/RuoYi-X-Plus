#!/bin/bash
sudo docker-compose -f $(dirname $(pwd))/ruoyi-application-admin/docker-compose.yml -p ruoyi-application-admin up -d --build --force-recreate
