# frozen_string_literal: true

require_relative 'parameters'
require 'erb'

TEMPLATE = ERB.new <<~SKETCH
  require 'picrate'

  class <%= @class %> < Processing::App
    def settings
      size <%= @width %>, <%= @height %>, <%= @mode %>
    end

    def setup
      sketch_title '<%= @title %>'
    end

    def draw

    end
  end

  <%= @class %>.new
SKETCH

# The SketchClass class knows how to format title & class name
class SketchClass
  attr_reader :name
  def initialize(name)
    @name = name
  end

  def class_name
    name.split('_').collect(&:capitalize).join
  end

  def title
    name.split('_').collect(&:capitalize).join(' ')
  end

  def filename
    name << '.rb'
  end
end

# The file writer can write a sketch when given instance of Sketch type
class SketchWriter
  attr_reader :file, :name, :width, :height, :mode, :sketch
  def initialize(path)
    param = Parameters.read
    @sketch = SketchClass.new(path)
    @class = sketch.class_name
    @title = sketch.title
    sketch_param = param.fetch('sketch')
    @width = sketch_param.fetch('width')
    @height = sketch_param.fetch('height')
    @mode = sketch_param.fetch('mode')
    binding
  end

  def write
    File.open(sketch.filename, 'w+') { |f| f.write TEMPLATE.result(binding) }
  end
end
