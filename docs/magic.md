---
layout: page
title: Magic
---
Spoiler alert, initially a least it is better just to accept the magic works...

<% for magic in site.magics %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ magic.url | prepend: site.github.url }}">{{ magic.title }}</a></h2></p>
{% endfor %}
