# frozen_string_literal: true
require 'java'
unless defined? PICRATE_ROOT
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__))
  PICRATE_ROOT = File.absolute_path(File.dirname(__dir__))
end
Dir["#{PICRATE_ROOT}/lib/*.jar"].each do |jar|
  require jar
end
require_relative 'picrate/app'
