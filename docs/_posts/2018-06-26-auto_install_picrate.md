---
layout: post
title:  "Automated PiCrate Install"
date:   2018-06-26 07:34:13
categories: PiCrate update
permalink: /install_picrate/
---

For a semi-automated setup and install, download and extract [this gist](https://gist.github.com/monkstone/e9df8ea776aed58ce1c4de8e12982aad). To keep your system clean put the file in a folder say
`~/install_picrate`

```bash
cd ~/picrate_install
bash picrate_install.sh # to run default task
```
#### What the script does ###
1. Downloads JRuby
2. Installs JRuby to `/opt`
3. Uses `update-alternatives` to configure `jruby`, `jgem` and `jirb`
4. The script checks for `GEM_HOME`, if undefined it modifies `~/.profile` to define `GEM_HOME` and puts gem binaries on your path, at logon.
5. Installs `picrate` gem
6. Downloads and installs `picrate_samples` also configures `geanyIDE` for use with `picrate`

#### Note ####
`GEM_HOME` and path to `gem` binaries is not available until next logon, to use immediately you could:-

```bash
source ~/.profile
```
NB: this only works in current shell, so if you want to use `geanyIDE` you should logout and logon again.
