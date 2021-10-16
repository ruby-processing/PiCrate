---
layout: post
title:  "Getting Started RaspbianOS"
date:   2019-11-11 07:34:13
categories: PiCrate update
permalink: /getting_buster/
---
RaspberryPI OS usually comes with a pre-installed jdk11. Before installing JRuby it might be worth setting the `JAVA_HOME` environmental variable (_to supress reflection warnings_).

  __Otherwise follow these instructions in order as needed:-__

```bash
sudo apt-get update # sync your local database with current release
sudo update # update to latest release
sudo apt install openjdk-11-jdk # installs latest jdk11
java -version # check installed version
```
If you have already installed a version java the java version may not match you can control the _active_ java version on Debian using `update-alternatives` as follows:-
```bash
sudo update-alternatives --config java
sudo update-alternatives --config javac
sudo update-alternatives --config jar
```
See [java][java] install for more explanations.

__Then install JRuby see__ [jruby][jruby].

We strongly recommend that you create a local storage for gems on your system, as follows:-

```bash
mkdir -p ~/.gem/jruby/2.5.0
```
Now set your `GEM_HOME`, `GEM_PATH` and amend your `PATH` as follows:-

```bash
echo "export GEM_HOME=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.profile
echo "export GEM_PATH=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.profile
echo "export PATH=\"\${PATH}:\${GEM_PATH}/bin\"" >> ~/.profile
source ~/.profile # to update environment without re-logging in
```
Now should be ready to install `picrate` and other gems.
Install a local version of rake:-
```bash
jgem install rake
```
To install latest picrate and its dependencies:-

```bash
jgem install picrate
```
__For a first install:-__

```bash
picrate --install # no args, install samples and geany config
# or
picrate -i Samples # to omit geany config
```

This installs example sketches in `~/projects/examples` and ties them into a `geany` project `examples.geany`. It should also be possible to run sketches from the `geany` ide.

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

### JWishy Sketch Running on RaspberryPI

![jwishy_buster]({{ site.github.url }}/assets/jwishy_buster.png)

[buster]: https://gist.github.com/monkstone/04a1272ca9274a2c7e3e1bf170877bfb
[java]:http://ruby-processing.github.io/java/raspberry/
[jruby]:{{ site.github.url }}/install_jruby/
[geany]:{{ site.github.url }}/editors/geany
