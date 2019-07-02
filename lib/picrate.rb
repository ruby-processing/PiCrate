# frozen_string_literal: true
require 'java'
unless defined? PICRATE_ROOT
  $LOAD_PATH << File.dirname(__dir__)
  PICRATE_ROOT = File.dirname(__dir__)
end

Dir["#{PICRATE_ROOT}/lib/*.jar"].each do |jar|
  require jar
end
require_relative 'picrate/app'
