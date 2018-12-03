---
layout: post
title:  "Automated PiCrate Install"
date:   2018-06-26 07:34:13
categories: PiCrate update
permalink: /install_picrate/
---

For a semi-automated setup and install, download and extract [this gist](https://gist.github.com/monkstone/159c5a9813c9cd181040b4715e74f6b2)

```bash
bash gem_path.sh # sets up a local gem environment
rake # default is to install jruby to opt, then link using update-alternatives
```
Adjust script to suit needs if required, now installs `gems` with `jgem` from `rubygems`.
