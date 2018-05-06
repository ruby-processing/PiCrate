---
layout: post
title:  "Getting Started"
date:   2018-05-24 07:34:13
categories: PiCrate update
---
You need to install jdk8, there's probably easy ways to do it, if you have more that one version of java installed you could use `update-alternatives` tool to control them.
Then install JRuby.

```bash
jgem install picrate # also installs arcball dependency
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
require 'propane'

class MySketch < Propane::App
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
