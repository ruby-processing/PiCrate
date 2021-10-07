---
layout: post
title:  "Getting Started Manjaro"
date:   2020-05-11 07:34:13
categories: PiCrate update
permalink: /getting_manjaro/
---
If Manjaro does not come with a pre-installed java, then the first step is to install a `jdk` the distro version (jdk16) should work.

```bash
sudo pacman -S jre-openjdk # current distro version jdk14
```
You can also use pacman to install jruby
```bash
sudo pacman -S jruby # current version jruby-9.3.0.0
```
It is probably a good idea to create a local gem store (rather needing to use sudo to install gems)
```bash
sudo pacman -S vim # all you need if your happy with vim
```
Vim is not installed by default
```bash
sudo pacman -S geany # if you prefer a GUI
```
Geany is not installed by default


```bash
mkdir -p ~/.gem/ruby/2.6.0 # current MRI version supported by jruby
```
Now set your `GEM_HOME`, `GEM_PATH` and amend your `PATH` as follows:-

```bash
echo "export GEM_HOME=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.bashrc
echo "export GEM_PATH=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.bashrc
echo "export PATH=\"\${PATH}:\${GEM_PATH}/bin\"" >> ~/.bashrc
source ~/.bashrc # to update environment without re-logging in
```
Now should be ready to install `picrate` and other gems. But speed up install time you should set `--no-document` option in ~/.gemrc

```bash
touch ~/.gemrc
echo "gem: --no-document" > ~/.gemrc
```
To install latest picrate and its dependencies:-

```bash
jgem install picrate
```

Geany is a good editor/ide for PiCrate on the RaspberryPI (install via pacman), but some may prefer vim. For geany you should edit/preferences/Terminal to `Execute programs in the VTE`.

__For a first install:-__

```bash
picrate --install # no args, install samples and geany config
# or
picrate -i Samples # to omit geany config
```

This installs example sketches in `~/projects/examples` and ties them into a `geany` project `examples.geany`. It should also be possible to run sketches from the `geany` ide. The geany config creates `picrate.rb` template sketch so you can create a new sketch with template.

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
jruby --dev my_sketch.rb
```

Or even `chmod +x my_sketch.rb` to make an executable script.

See [editors][geany] geany, for how to run sketches from a gui.

### JWishy Sketch Running on RaspberryPI

![jwishy_buster]({{ site.github.url }}/assets/jwishy_buster.png)

[buster]: https://gist.github.com/monkstone/04a1272ca9274a2c7e3e1bf170877bfb
[java]:http://ruby-processing.github.io/java/raspberry/
[jruby]:{{ site.github.url }}/install_jruby/
[geany]:{{ site.github.url }}/editors/geany
