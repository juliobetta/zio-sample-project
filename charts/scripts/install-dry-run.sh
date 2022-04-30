#!/bin/bash
# When you want to test the template rendering, but not actually install anything, you can use

helm install zio-app --debug --dry-run ./charts/zio-app
