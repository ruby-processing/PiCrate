__C6H3N3O7__ [![Build Status](https://travis-ci.org/ruby-processing/PiCrate.svg?branch=master)](https://travis-ci.org/ruby-processing/PiCrate) 
[![Gem Version](https://badge.fury.io/rb/picrate.svg)](https://badge.fury.io/rb/picrate)

# PiCrate
Create processing sketches in ruby on raspberry-pi and linux (this project is a parallel development of [picrate][picrate] targetting the raspberry-pi, but will initially be developed on a regular linux box). The aim is to produce a gem installable app that can be run with jruby, with minimal dependencies.

### To Build and Test ###

Clone this repo:-

Requires maven, jdk8 (can be openjdk), and a jruby install.

```bash
cd PiCrate
rake
jgem install picrate-0.0.1-java.gem
```
To create a template sketch:-
```bash
picrate -c my_sketch 200 200
```
Edit sketch (vim is good choice):-
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
jruby my_sketch.rb
```


[picrate]:https://ruby-processing.github.io/picrate/
