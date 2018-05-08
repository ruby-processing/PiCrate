---
layout: page
title: Gems
---
{% for gem in site.libraries %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}</p>
  <p><h2><a href="{{ library.url | prepend: site.github.url }}">{{ gem.title }}</a></h2></p>
{% endfor %}

<p>If you are new to ruby, see <a href="http://guides.rubygems.org/what-is-a-gem/">what is a gem here</a></p>
