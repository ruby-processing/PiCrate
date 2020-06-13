---
layout: post
title:  "Getting Started Manjaro"
date:   2020-05-11 07:34:13
categories: PiCrate update
permalink: /getting_manjaro/
---
Currently Manjaro does not come with a pre-installed java. So the first step is to install a `jdk` we currently recommend installing hotspot adopt-openjdk11 from the AdoptOpenJDK project (there may be issues with distro version). Setting the JDK_HOME environment (easiest done `/etc/profile.d`) and then manually install the latest JRuby. It is probably worth creating a symbolic links to `/usr/bin/jruby` and `/usr/bin/jgem` from wherever you installed jruby eg /opt folder.

```bash
mkdir -p ~/.gem/jruby/2.5.0
```

Now set your `GEM_HOME`, `GEM_PATH` and amend your `PATH` as follows:-

```bash
echo "export GEM_HOME=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.bashrc
echo "export GEM_PATH=\"\${HOME}/.gem/ruby/${MRI_RUBY}\"" >> ~/.bashrc
echo "export PATH=\"\${PATH}:\${GEM_PATH}/bin\"" >> ~/.bashrc
source ~/.bashrc # to update environment without re-logging in
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

This installs example sketches in `~/projects/examples` and ties them into a `geany` project `examples.geany` (_needs modification was designed for raspbian Buster_). It should also be possible to run sketches from the `geany` ide. Sketches need to be run in VTE terminal on Manjaro ARM the terminal emulator, does not give access to local environment variables in the version of Geany supplied. The other thing that needs to change is name of user, defaults to `pi` in installer script.

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
