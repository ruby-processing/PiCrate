---
layout: post
title:  "Geany"
permalink: /editors/geany/
keywords: editor, ide, ruby, picrate, vim, emacs, geany
---
Geany is highly configurable, and we have created a `geany` project file `picrate.geany` and sketch template `picrate.rb` for you, that get installed when you:-

```bash
picrate --install samples
```

#### Using picrate.rb template ####

![select picrate.rb]({{ site.github.url }}/assets/picrate_template.png)

Remember to give untitled sketch a new name (PS: it's easy to create your own templates):-

![new with template]({{ site.github.url }}/assets/new_with_template.png)

Use `Build/rubocop` to check syntax, and `run` button to run the
 sketch

See running sketch below:-

![geany]({{ site.github.url }}/assets/geany.png)

#### Advanced options

1. rubocop

If you install the `rubocop` gem you can use the Build/Rubocop control to do a static test on the current file. You can configure `rubocop` to ignore selected rules if you wish. Or use it to auto-correct issues.

![geany]({{ site.github.url }}/assets/rubocop.png)

2. reek

If you are keen to develop your `OO` skills analysing your sketch code for smells can be instructive see references:-

* Practical Object-Oriented Design in Ruby - Sandi Metz
* Refactoring (Ruby Edition) - Jay Fields, Shane Harvie, Martin Fowler
* 99 Bottles of OOP - Sandi Metz and Katrina Owen

![geany]({{ site.github.url }}/assets/reek.png)
