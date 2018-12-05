---
layout: post
title:  "Automated PiCrate Install"
date:   2018-06-26 07:34:13
categories: PiCrate update
permalink: /install_picrate/
---

For a semi-automated setup and install, download and extract [this gist](https://gist.github.com/monkstone/c7d7741f800eb2327253635ee283c7eb). To keep your system clean put the file in a folder say
`~/install_picrate`

```bash
cd ~/install_picrate
rake # default is to install jruby to opt, then link using update-alternatives
```
Adjust script to suit needs if required, now installs `gems` with `jgem` from `rubygems`.

Note the script modifies `~/.profile` to define `GEM_HOME` and puts gem binaries on your path, it does this on logon. To use immediately:-

```bash
source ~/.profile
```
