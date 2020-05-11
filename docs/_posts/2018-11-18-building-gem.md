---
layout: post
title:  "Building PiCrate gem on Linux"
date:   2018-11-18 07:34:13
categories: PiCrate update
---
Requirements:-

1. Latest jogl-2.4.0-rc jars (place in ~/jogl24 folder).

2. jdk-11+ and maven

Simply clone this distribution, then rake to build and test gem
```bash
git clone --depth 1 https://github.com/ruby-processing/PiCrate.git
cd PiCrate
rake # to build gem
```
