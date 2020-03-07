__C6H3N3O7__ [![Gem Version](https://badge.fury.io/rb/picrate.svg)](https://badge.fury.io/rb/picrate)![Travis CI](https://travis-ci.org/ruby-processing/PiCrate.svg)

# PiCrate
Version for RaspberryPI `Stretch` and `Oracle JDK8`, will probably also work on `OpenJDK11+`. For latest version see auto-install script for [PiCrate-2.0][buster] on Buster (tested on RaspberryPI4). Create processing sketches in ruby on raspberry-pi and linux (this project is a parallel development of [propane][propane] targetting the raspberry-pi, but will initially be developed on a regular linux box). The aim is to produce a gem installable app that can be run with jruby, with minimal dependencies. Drop the `C` and you get pirate, or and an `e` and get `PiCreate`, a happy coincidence?

### To install from rubygems ###

```bash
jgem install picrate
```

### To Build and Test ###

Clone this repo:-

Requires java to build, but uses a maven wrapper so you don't need to install maven. Suggest build/test on regular linux box, but is designed for use on RaspberryPI 3B+. Needs installed jruby to test/run.

```bash
cd PiCrate
rake # assumes an installed version of vanilla processing
jgem install picrate-1.2.4-java.gem

```
To create a template sketch:-
```bash
picrate -c my_sketch 200 200
```
Edit sketch (vim is a good choice):-
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
