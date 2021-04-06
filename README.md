__C6H3N3O7__ [![Gem Version](https://badge.fury.io/rb/picrate.svg)](https://badge.fury.io/rb/picrate)![Travis CI](https://travis-ci.org/ruby-processing/PiCrate.svg)

# PiCrate
New version for `Buster` on raspberryPI 3B+ works with jdk11 (reflective access warnings fixed for jdk11). Since version 2.0.1 also works on 64 bit Manjaro distro on RaspberryPI4. Create processing sketches in ruby on raspberry-pi and linux (this project is a parallel development of [propane][propane] targetting the raspberry-pi, but will initially be developed on a regular linux box). The aim is to produce a gem installable app that can be run with jruby, with minimal dependencies. Drop the `C` and you get pirate, or and an `e` and get `PiCreate`, a happy coincidence?


### To install from rubygems ###

```bash
jgem install picrate
```

### To Build and Test ###

Clone main branch:-

Requires java to build (and [jogl-2.4.0-rc jars][jogl_jars]), but uses a maven wrapper so you don't need to install maven. Suggest build/test on regular linux box, but is designed for use on both RaspberryPI 3B+ and RaspberryPI4. Needs installed jruby to test/run.

```bash
cd PiCrate # or whatever you call it
rake # assumes an installed version of vanilla processing
jgem install picrate-2.4.0-java.gem

```
To create a template sketch:-
```bash
picrate -c my_sketch 200 200
```
Edit sketch ([vim][vim] is a good choice, but we recommend [geany][geany]) :-
```ruby
#!/usr/bin/env jruby
# frozen_string_literal: false
require 'picrate'

class MySketch < Processing::App
  def settings
    size 200, 200
  end

  def setup
    sketch_title 'My Sketch'
  end

  def draw
    background 0
    fill 0, 0, 200
    ellipse 100, 100, 90, 70
  end
end

MySketch.new
```
### Run Sketch ###
```bash
jruby --dev my_sketch.rb # --dev flag speeds start-up
```

[buster]:https://gist.github.com/monkstone/6ae9840d7b7008c177b4a9f589d14ec6
[propane]:https://ruby-processing.github.io/propane/
[vim]:https://github.com/ruby-processing/PiCrate/blob/master/docs/_editors/vim.md
[geany]:https://github.com/ruby-processing/PiCrate/blob/master/docs/_editors/geany.md
[jogl_jars]:https://jogamp.org/deployment/archive/rc/v2.4.0-rc-20210111/jar/
