---
layout: page
title: About
permalink: /about/
---

![Alt text](picrate.svg) <img src="assets/picrate.svg">

PiCrate is both a `library` and an `app` that allows you to create [processing][processing] sketches in ruby on the [RaspberryPI][PI] (or 64 bit linux). PiCrate is available as a gem (from [rubygems][rubygems]), it requires [JRuby][jruby] to both install and run.


#### Executable ####

The executable is `picrate`, which can be used to install samples (and configure [GeanyIDE][geany]) and some processing libraries

#### Library ###

Your PiCrate sketches need to `require` the `picrate` library, and can be run with `jruby`.

* Command line interface (typically using vim as editor)

* Gui interface, sketches can be run from [GeanyIDE][geany]




[rubygems]:https://rubygems.org/
[jruby]:https://www.jruby.org/
[PI]:https://www.raspberrypi.org/
[processing]:https://www.processing.org/
[geany]:https://www.geany.org/
