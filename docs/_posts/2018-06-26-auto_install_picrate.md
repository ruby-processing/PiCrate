---
layout: post
title:  "Installing JRuby"
date:   2018-06-26 07:34:13
categories: PiCrate update
permalink: /install_picrate/
---

For a semi-automated setup and install, download and extract [this gist](https://gist.github.com/monkstone/8f06529790c36f5b7f668015faadcbc5/archive/d8617b32e5b83f33e077d48b862a79b46bcdbbbc.zip)

```bash
bash gem_environment.sh # sets up a local gem environment
rake # default is to install jruby to opt, then link using update-alternatives
```
Adjust script to suit needs if required, currently downloads gems and installs using downloaded copies (currently there is an issue with jgem downloads from rubygems on raspberryPI).
