---
layout: default
title: About
permalink: /about/
---
![picrate.png]({{ site.github.url }}/assets/android-chrome-192x192.png)
PiCrate is both a `library` and an `app` that allows you to create [processing][processing] sketches in ruby on the [RaspberryPI][PI] (or 64 bit linux). PiCrate is available as a gem (from [rubygems][rubygems]), it requires [JRuby][jruby] to both install and run. It does not depend on a vanilla processing install.Tested on RaspbianOS on RaspberryPI3B+ and RaspberryPI4, also works on Manajaro distro (64bit) on RaspberryPI4.

#### Executable ####

PiCrate sketches are executable scripts that (but without the executable permission), that will run directly from jruby.


```bash
jruby my_sketch.rb
```

The gem executable is `picrate`, which can be used to install samples (and to configure [GeanyIDE][geany]) and some processing libraries

#### Library ###

Your PiCrate sketches need to `require` the `picrate` library, and can be run with `jruby`.

* Command line interface (typically using vim as editor)

* Gui interface, sketches can be run from [GeanyIDE][geany]

* Use --dev flag to speed start-up


[rubygems]:https://rubygems.org/
[jruby]:https://www.jruby.org/
[PI]:https://www.raspberrypi.org/
[processing]:https://www.processing.org/
[geany]:https://www.geany.org/
