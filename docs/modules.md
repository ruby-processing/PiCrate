---
layout: page
title: Modules
---

{% for module in site.modules %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ module.url | prepend: site.github.url }}">{{ module.title }}</a></h2></p>
{% endfor %}

{% for library in site.libraries %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ library.url | prepend: site.github.url }}">{{ library.title }}</a></h2></p>
{% endfor %}

<p>If you are new to ruby, you can read more about <a href="https://www.tutorialspoint.com/ruby/ruby_modules.htm">modules here</a></p>
