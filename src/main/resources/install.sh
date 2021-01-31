#!/bin/bash
rm -rf ~/newsbox/jars ~/newsbox/resources
unzip newsbox-*.zip
mv resources/*.sh .
rm resources/*dev*
dos2unix *.sh
dos2unix resources/*
chmod +x *.sh
cd resources/php
composer install