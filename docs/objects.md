---
layout: page
title: Variables and Objects
---
{% for object in site.objects %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}
  <p><h2><a href="{{ object.url | prepend: site.github.url }}">{{ object.title }}</a></h2>
{% endfor %}
