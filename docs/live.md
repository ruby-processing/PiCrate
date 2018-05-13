---
layout: page
title: live
---
Spoiler alert, initially a least it is better just to accept the live works...

{% for live_mode in site.live %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ live_mode.url | prepend: site.github.url }}">{{ live_mode.title }}</a></h2></p>
{% endfor %}
