---
layout: page
title: Editors
---

{% for editor in site.editors %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ editor.url | prepend: site.github.url }}">{{ editor.title }}</a></h2></p>
{% endfor %}
