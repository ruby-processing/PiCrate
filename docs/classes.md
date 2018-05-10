---
layout: page
title: Classes
---

<% for classes in site.classess %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ classes.url | prepend: site.github.url }}">{{ classes.title }}</a></h2></p>
{% endfor %}
