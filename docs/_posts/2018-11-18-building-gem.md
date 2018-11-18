---
layout: post
title:  "Building PiCrate gem on Linux"
date:   2018-11-18 07:34:13
categories: PiCrate update
---
Requirements:-

1. An installed version of vanilla processing to provide `processing.org` customised version of the `jogl` jars. The default `Rakefile` assumes root installation of processing, for debian distros you will need to adjust `processing_root` _ca. line 20_.

2. jdk-8 and maven

Simply clone this distribution, then rake to build and test gem
```bash
git clone https://github.com/ruby-processing/PiCrate.git
cd PiCrate
rake # to build gem
```
