v1.3.0 Last version for jdk8. But should run OK jdk11 with JRuby-9.2.11.0 and with included jogl-2.4.0-rc.

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

v0.7.0 Refactor sketch_writer to load params from ~/.picrate/sketch.yml. `AppRender => GfxRender` because we only need `PGraphics` in renderer.

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
