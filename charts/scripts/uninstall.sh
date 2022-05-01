#!/bin/bash
# When you want to test the template rendering, but not actually install anything, you can use

kubectl delete deployment zio-app-chart
sleep 5
helm uninstall zio-app --debug
