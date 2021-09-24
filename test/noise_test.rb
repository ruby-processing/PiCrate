# frozen_string_literal: true

require_relative 'test_helper'

Java::Monkstone::PicrateLibrary.new.load(JRuby.runtime, false)

# include Processing::HelperMethods

Dir.chdir(File.dirname(__FILE__))
# Test processing map functions
class ProcessingNoiseTest < Minitest::Test
  include NoiseModule
  def test_noise1d
    x = 0.5
    assert_in_delta(noise(x), 0.5, 0.00001)
  end
end
