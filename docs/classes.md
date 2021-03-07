---
layout: page
title: Classes
---

{% for class in site.classes %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}
  <p><h2><a href="{{ class.url | prepend: site.github.url }}">{{ class.title }}</a></h2>
{% endfor %}
