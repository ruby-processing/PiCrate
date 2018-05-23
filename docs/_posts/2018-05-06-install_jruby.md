---
layout: post
title:  "Installing JRuby"
date:   2018-05-24 07:34:13
categories: PiCrate update
permalink: /install_jruby/
---
Pure installation Debian linux (should work raspbian) no need for `rbenv` or `rvm`

Get the latest version from [http://jruby.org/download][download]

```bash
cd /opt
sudo tar xzvf /pathToDownload/jruby-bin-9.x.x.x.tar.gz
```

Then use the excellent `update-alternatives` tool to provide symbolic links to `jruby`, `jgem`, `jirb` and `rake` especially if you haven't installed `mri` ruby.

```bash
sudo update-alternatives --install /usr/bin/jruby jruby /opt/jruby{version}/bin/jruby 100
sudo update-alternatives --config jruby
```

You should prefer to install gems locally (no need for sudo). To do that it is convenient on linux to edit your `~/.profile` (or equivalent eg `~./bashrc`) file as follows, the important thing is ensure that the gem bin directory is on your path.

```bash
alias jpry="jruby -e \"require 'pry'; binding.pry\""
# export JAVA_HOME="/opt/jdk1.8.0_151" # if using oracle java openjdk-8 is fine though
export GEM_HOME="/home/tux/.gem/ruby/2.3.0"
export GEM_PATH="/home/tux/.gem/ruby/2.3.0"
export PATH="${PATH}:${GEM_PATH}/bin"
```

If you know better please post on wiki

[download]:https://s3.amazonaws.com/jruby.org/downloads/9.1.17.0/jruby-bin-9.1.17.0.tar.gz
