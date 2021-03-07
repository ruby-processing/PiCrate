---
layout: page
title: Modules
---

{% for module in site.modules %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}
  <p><h2><a href="{{ module.url | prepend: site.github.url }}">{{ module.title }}</a></h2>
{% endfor %}

<p>If you are new to ruby, you can read more about <a href="https://www.tutorialspoint.com/ruby/ruby_modules.htm">modules here</a>
