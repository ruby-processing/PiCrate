---
layout: page
title: About
permalink: /about/
---

PiCrate is an experimental ruby wrapper for processing for the raspberrypi (raspbian image), like the vanilla processing version this uses the 32 bit video drivers. It could be that 64 bit drivers work, but is possible they don't play too well together (to use the 64 bit drivers uncomment them in Rakefile and gemspec and comment out the 32 bit drivers). Also `native_folder.rb` will need amending for sound etc libraries.
See [github repo][repo]

See also [gottfreid haider][gottfreid] at vanilla processing and vanilla [processing wiki][wiki]

[jruby_art]: https://ruby-processing.github.io/index.html
[propane]:https://ruby-processing.github.io/propane/
[repo]:https://github.com/ruby-processing/PiCrate
[gottfreid]:https://github.com/processing/processing/wiki/Raspberry-Pi#download
[wiki]:https://github.com/processing/processing/wiki/Raspberry-Pi
