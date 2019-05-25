---
layout: post
title:  "Installing JRuby on Archlinux arm"
date:   2018-05-24 07:34:13
categories: PiCrate update
---
It is quite likely that people wishing to experiment with PiCrate on arm have moved beyond the raspbian distribution. As a first step [archlinuxarm][alarm] which seems to be well thought out. Installing jdk8 and jruby should be as simple as:-

```bash
sudo pacman -Syu # get up to data
sudo pacman jdk-arm # but I'm not 100% sure what version is installed jdk8+ is reqd
sudo archlinux-java set java-8-openjdk/jre # case you have more than one java installed
sudo pacman jruby # should install latest version
```


[alarm]:https://archlinuxarm.org/platforms/armv6/raspberry-pi
