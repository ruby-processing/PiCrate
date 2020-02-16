# frozen_string_literal: true

require_relative 'sketch_writer'
# Sketch Factory
class SketchFactory
  NAMES = %w[One Two Three].freeze
  def initialize(_argc)
    NAMES.each do |name|
      SketchWriter.new(File.basename(name, '.rb')).write
    end
  end
end

SketchFactory.new(File.join(ENV['HOME'], 'test'))
