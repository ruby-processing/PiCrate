---
layout: page
title: Gems
---
{% for gem in site.gems %}
  <h2>{{ item.title }}</h2>
  <p>{{ item.description }}
  <p><h2><a href="{{ gem.url | prepend: site.github.url }}">{{ gem.title }}</a></h2>
{% endfor %}

<p>If you are new to ruby, see <a href="http://guides.rubygems.org/what-is-a-gem/">what is a gem here</a>
