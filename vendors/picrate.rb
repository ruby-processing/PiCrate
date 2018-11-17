#!/usr/bin/env jruby
require 'picrate'
# Sketch class
class MySketch < Processing::App
  def settings
    size 200, 200
  end

  def setup
    background 0
  end

  def draw
    # animation loop
  end
end

MySketch.new
