---
layout: post
title:  "Getting Started Buster"
date:   2020-03-09 07:34:13
categories: PiCrate update
permalink: /getting/
---
Raspbian Buster installs OpenJDK11 (hard float) and that is just fine, if you have more that one version of java installed you could use `update-alternatives` tool to manage their use. NB: Will not work with OpenJDK-8.

__Then install JRuby see__ [jruby][jruby].

```bash
jgem install picrate
```

__For a first install:-__

```bash
picrate --install # no args, install samples and geany config
# or
picrate -i Samples # to omit geany config
```

This installs example sketches in `~/sample_sketches` and ties them into a `geany` project `picrate.geany`. It should also be possible to run sketches from the `geany` ide.

__Install processing libraries__

```bash
picrate -i Sound
picrate -i video # install beta verion of vanilla video for now
```

To create a template sketch from the command line:-

```bash
picrate -c my_sketch 600 400
```
creates file my_sketch.rb

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

  end
end
MySketch.new

```

Edit in `vim` (at command line) or `geany` (gui), you may need to install `vim`
```bash
vim my_sketch.rb
:!jruby % # from vim runs the sketch
```

To run sketches from command line:-

```bash
jruby my_sketch.rb
```

Or even `chmod +x my_sketch.rb` to make an executable script.

See [editors][geany] geany, for how to run sketches from a gui.

### Circle Collision Sketch Running on RaspberryPI

![circle_collisions]({{ site.github.url }}/assets/circle_collisions.png)

[jruby]:{{ site.github.url }}/install_jruby/
[geany]:{{ site.github.url }}/editors/geany
