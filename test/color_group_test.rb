# frozen_string_literal: true
require_relative 'test_helper'
require 'java'
require_relative '../lib/picrate'
require_relative '../library/color_group/color_group'

Java::Monkstone::PicrateLibrary.new.load(JRuby.runtime, false)
java_import Java::Monkstone::ColorUtil

Dir.chdir(File.dirname(__FILE__))

PALETTE = %w[#FFFFFF #FF0000 #0000FF].freeze
COLORS = [16777215, 16711680, 255].to_java(:int)

class ColorGroupTest < Minitest::Test
  def test_new
    group = ColorGroup.new(COLORS)
    assert group.kind_of? ColorGroup
  end

  def test_web_array
    group = ColorGroup.from_web_array(PALETTE)
    assert group.kind_of? ColorGroup
  end

  def test_ruby_string
    p5array = [16777215, 16711680, 255]
    group = ColorGroup.new(COLORS)
    code_string = "%w[#FFFFFF #FF0000 #0000FF]\n"
    result = group.ruby_code
    assert_equal(result, code_string)
  end
end