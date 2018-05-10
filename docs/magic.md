---
layout: page
title: Magic
---
Spoiler alert, initially a least it is better just to accept the magic works...

{% for spell in site.magic %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ spell.url | prepend: site.github.url }}">{{ spell.title }}</a></h2></p>
{% endfor %}
