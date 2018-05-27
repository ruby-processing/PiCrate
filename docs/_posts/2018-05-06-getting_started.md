---
layout: post
title:  "Getting Started"
date:   2018-05-24 07:34:13
categories: PiCrate update
permalink: /getting/
---
The current version of raspbian installs jdk-1.8.0_65-bi17 (hard float) and that is just fine, if you have more that one version of java installed you could use `update-alternatives` tool to control them.

Then install JRuby see [jruby][jruby].

There is something wrong with jgem install from rubygems (involving `flock` and possibly wrong bit) so currently the best approach is to download the gems and install locally

```bash
jgem install arcball-1.0.1-java.gem
jgem install picrate-0.1.0-java.gem
```

Install samples

```bash
picrate -i Samples
```

Install processing libraries

```bash
picrate -i Sound
picrate -i glvideo # preferred over vanilla video for now
```

Create template

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

Edit in say vim
```bash
vim my_sketch.rb
:!jruby % # from vim runs the sketch
```

Alternatively to run sketches

```bash
jruby my_sketch.rb
```

Or even `chmod +x my_sketch.rb` to make an executable script.

### Circle Collision Sketch Running on RaspberryPI

![circle_collisions]({{ site.github.url }}/assets/circle_collisions.png)

[jruby]:{{ site.github.url }}/install_jruby/
