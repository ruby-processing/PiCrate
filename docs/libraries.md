---
layout: page
title: Libraries
---
It is possible to use vanilla <a href="https://www.processing.org/reference/libraries/">processing libraries</a> with picrate, but we also provide pure ruby libraries eg `boids`.  Most libraries can be loaded using the <a href="https://ruby-processing.github.io/PiCrate/load_library">load_library</a> method, the exception being those that can be loaded as <a href="https://ruby-processing.github.io/PiCrate/gems.html">gems</a>. It is also possible use the <a href="https://ruby-processing.github.io/PiCrate/load_library">load_library</a> method to load local libraries for your sketches see <a href="https://github.com/ruby-processing/picrate-examples/blob/master/demo/raining.rb">raining.rb</a>, if you stick with PiCrate library conventions. For more advanced sketches it is often a good idea to create your own <a href="https://en.wikipedia.org/wiki/Library_%28computing%29">libraries</a>.

{% for library in site.libraries %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}
  <p><h2><a href="{{ library.url | prepend: site.github.url }}">{{ library.title }}</a></h2>
{% endfor %}

<h4>Key To Library Types</h4>
<ol>
<li>Builtin pure ruby eg boids</li>
<li>Builtin wrapping java eg video_event</li>
<li>Custom pure ruby</li>
<li>Custom java (see also pbox2d gem)</li>
<li>Regular processing libraries (see also gems for toxiclibs etc)</li>
</ol>
