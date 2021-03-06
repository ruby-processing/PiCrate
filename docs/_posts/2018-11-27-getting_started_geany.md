---
layout: post
title:  "Getting Started with Geany"
date:   2018-11-27 07:34:13
categories: PiCrate update
permalink: /geany_ide/
---
The current version of raspbian installs jdk-11 (hard float) and that is just fine, if you have more that one version of java installed you could use `update-alternatives` tool to manage their use.

Then install JRuby see [jruby][jruby].

```bash
jgem install picrate
```

Install samples, and configure `geany` ide (for `PiCrate`)

```bash
picrate -i
# or
picrate --install
```
or for no geany configuration
```bash
picrate -i Samples
# or
picrate --install Samples
```


Install processing libraries

```bash
# picrate -i Sound not currently working
picrate -i video # preferred over vanilla video for now
```

### Creating Sketches from the GeanyIDE

See [editors][geany] geany, for how to run sketches from a gui.

### Running PiCrate sketch on RaspberryPI from geany editor

![snake_kolam]({{ site.github.url }}/assets/geany.png)

[jruby]:{{ site.github.url }}/install_jruby/
[geany]:{{ site.github.url }}/editors/geany
