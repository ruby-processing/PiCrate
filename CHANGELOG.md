v2.5.0 Update to JRuby-9.3.0.0.

v2.4.2 Update to JRuby-9.2.19, getting ready for JRuby-9.3+.

v2.4.1 patch library loader, add PGS library examples.

v2.4.0 Refactor noise to two modules, FastNoise (default) & SmoothNoise, with terrain noise `:tnoise` option.

v2.3.0 Abandon Processing Value Noise in favour of OpenSimplex2, with choosable options. Added pdf library using iText5.

v2.2.0 Bump to latest JOGL-rc January 2021

v2.1.2 Bump to JRuby-9.2.14.0

v2.1.1 Bump to JRuby-9.2.13.0, include examples using ruby `clamp` in place of processing `constrain`, update to video-2.0 release, add run instruction to help

v2.1.0 Refactor how we configure geany so we can use ENV['HOME'] rather hard code config file. Bump to JRuby-9.2.12.0, include Sam Pottingers July 2020 staging changes.

v2.0.1 Use JOGL-2.4.0-rc fixes reflection warnings, remove geomerative and wordcram gem dependencies since can now be in step with JRubyArt and propane (re jdk compatibility), bump version for release. Removed Gottfreid Haider shaders, warn legacy driver doesn't work with P2D and P3D sketches. Support aarch64 architecture on RaspberriPI4.

v2.0.0 Use JDK11+ update to processing-3.5.4 suggest jruby-9.2.11.0

v1.2.4 Extract picrate examples to ~/projects/examples instead of ~/picrate_samples for tighter integration with geany editor. Remove non-functioning sound library as an install option (direct toward minim as option).

v1.2.3 Favor latest beta Video2 library over GLVideo.

v1.2.2 Removed landscape from example as too taxing for raspberryPI. Improved geany tools.

v1.2.1 Fix install Samples etc. in runner.rb to match modified vendor Rakefile.

v1.2.0 Update samples to use safer fonts, add linux specific suggestion on missing fonts PFont.java, fix :web_to_color_array(web).

v1.1.0 Bring up to date with latest development branch, except still target Stretch and Oracle jdk8, add Gemfile to help version locking.

v1.0.0 Release for Raspbian Stretch and Oracle java

v0.9.0 Remove unnecessary MacOS variations, bump examples version

v0.8.1 Refactor processing.core.PFont to remove need to load Fonts, that was a hack to support MacOS. List available fonts instead.

v0.8.0 Refactor processing code to jdk8 syntax, include lambda, switch on string etc. Make some methods used in constructors (eg allocate) final, might cause breakages? Using maven `release` flag which assumes build with jdk9+ (though targeting 8, which only supports `source` & `release` flags). The Jar manifest is now dynamically created by maven, which allows `travisci` to pass on java build.

v0.7.1 Oops fix `GfxRender` had not been merged

v0.7.0 Refactor sketch_writer to load params from ~/.picrate/sketch.yml. `GfxRender => GfxRender` because we only need `PGraphics` in renderer.

v0.6.0 Re-branding with new 'pick'/'eight' svg for PiCrate

v0.5.0 Do geany configuration when installing samples

v0.4.3 Update to jruby-9.2.4.0

v0.4.2 Update to jruby-9.2.0.0

v0.4.1 Proper sensible release since we can install from rubygems with jruby-9.2.1.0

v0.0.4 LSystem examples

v0.0.3
Extended Samples

v0.0.2
Attempt to set native folder for armv6hf

v0.0.1
First iteration with drivers as used by processing.org
