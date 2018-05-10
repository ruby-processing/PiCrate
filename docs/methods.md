---
layout: page
title: Methods
---

{% for method in site.methods %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ method.url | prepend: site.github.url }}">{{ method.title }}</a></h2></p>
{% endfor %}
