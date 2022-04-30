#!/bin/bash
# When you want to test the template rendering, but not actually install anything, you can use

kubectl delete deployment zio-chart -n zio
sleep 5
helm uninstall zio-app --debug -n zio
