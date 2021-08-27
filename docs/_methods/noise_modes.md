---
layout: post
title:  "noise_mode"
---

### Name ###

There are two noise modes available in PiCrate, both base on KdotJPG java noise.
The default mode is a FastNoise module, the other SmoothNoise module can be used using the module name as a prefix see example below.


### Examples ###

```ruby
#!/usr/bin/env jruby -w
require 'picrate'

class TestNoise < Processing::App
  attr_reader :z

  def setup
    stroke(255, 64)
    @z = 0
  end

  def draw
    noise_scale = 0.01
    background(0)
    grid(width, height, 10, 10) do |x, y|
      arrow(x, y, SmoothNoise.noise(x * noise_scale, y * noise_scale, z * noise_scale) * TWO_PI * 2)
      # arrow(x, y, noise(x * noise_scale, y * noise_scale, z * noise_scale) * TWO_PI * 2)
    end
    @z += 1
  end

  def arrow(x, y, ang)
    push_matrix()
    translate(x, y)
    rotate(ang)
    line(0, 0, 20, 0)
    translate(20, 0)
    rotate(PI + 0.4)
    line(0, 0, 5, 0)
    rotate(-0.8)
    line(0, 0, 5, 0)
    pop_matrix()
  end

  def settings
    size(600, 400, P2D)
  end
end

TestNoise.new

```

### Description	###

Currently supports four implementations of noise:-
1. DEFAULT # fast classic OpenSimplex2
2. OPEN_SMOOTH # smoother class OpenSimplex2F
3. FAST_TERRAIN # more suited to terrain
4. SMOOTH_TERRAIN # as above but smoother

### Syntax ###

```ruby
noise_mode(mode) # default is NoiseMode::DEFAULT, a fast classic OpenSimplex2
```

### Related ###

`noise(x, y, z, w)`

`noise(x, y, z)`

`noise(x, y)`
