# frozen_string_literal: true

require 'yaml'
require 'fileutils'

# module to read sketch.yml
# and or create sketch.yml with default parameters
module Parameters
  PATH = File.join(ENV['HOME'], '.picrate').freeze
  FILE = File.join(PATH, 'sketch.yml').freeze
  PARAM = { 'sketch' =>
    { 'width' => 640, 'height' => 480, 'mode' => 'P2D' } }.freeze

  def self.write
    FileUtils.mkdir_p PATH
    File.write(FILE, PARAM.to_yaml)
  end

  def self.read
    write unless File.exist?(FILE)
    YAML.load_file(FILE)
  end
end
