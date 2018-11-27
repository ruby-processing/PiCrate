---
layout: post
title:  "Getting Started"
date:   2018-05-24 07:34:13
categories: PiCrate update
permalink: /getting/
---
The current version of raspbian installs jdk-1.8.0_65-bi17 (hard float) and that is just fine, if you have more that one version of java installed you could use `update-alternatives` tool to manage their use.

Then install JRuby see [jruby][jruby].

```bash
jgem install picrate
```

Install samples, and configure `geany` ide (for `PiCrate`)

```bash
picrate -i Samples # geany configuration files are installed if required
```

Install processing libraries

```bash
picrate -i Sound
picrate -i glvideo # preferred over vanilla video for now
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
