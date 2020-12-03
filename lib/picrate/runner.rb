# frozen_string_literal: false

require 'optparse'
require_relative 'version'

module Processing
  # Utility class to handle the different commands that the 'picrate' offers
  class Runner
    attr_reader :options, :argc, :filename

    def initialize
      @options = {}
    end

    # Start running a picrate command from the passed-in arguments
    def self.execute
      runner = new
      runner.parse_options(ARGV)
      runner.execute
    end

    # Dispatch central.
    def execute
      parse_options('-h') if options.empty?
      show_version if options[:version]
      create if options[:create]
      install(filename) if options[:install]
    end

    # Parse the command-line options. Keep it simple.
    def parse_options(args)
      opt_parser = OptionParser.new do |opts|
        # Set a banner, displayed at the top
        # of the help screen.
        opts.banner = 'Usage: picrate [options] [<name>]'

        # Define the options, and what they do
        options[:version] = false
        opts.on('-v', '--version', 'PiCrate Version') do
          options[:version] = true
        end

        options[:install] = false
        message = '<Samples><Video> Install samples or library'
        opts.on('-i', '--install', message) do
          options[:install] = true
        end

        options[:create] = false
        opts.on('-c', '--create', 'Create new sketch outline') do
          options[:create] = true
        end

        # This displays the help screen, all programs are
        # assumed to have this option.
        opts.on_tail('-h', '--help', 'Display this screen') do
          puts opts
          puts ''
          puts 'Run a sketch: jruby [--dev] [<sketch.rb>]'
          exit
        end
      end
      @argc = opt_parser.parse(args)
      @filename = argc.shift
    end

    def create
      require_relative 'creators/sketch_writer'
      # in case user supplied extension, we remove it
      SketchWriter.new(File.basename(filename, '.rb')).write
    end

    def show_version
      require 'erb'
      template = ERB.new <<-EOF
      PiCrate version <%= PiCrate::VERSION %>
      JRuby version <%= JRUBY_VERSION %>
      EOF
      puts template.result(binding)
    end

    def install(library = nil)
      library ||= 'new'
      choice = library.downcase
      case choice
      when /samples|sound|video/
        system "cd #{PICRATE_ROOT}/vendors && rake install_#{choice}"
      when /new/
        # install samples and config geany
        system "cd #{PICRATE_ROOT}/vendors && rake"
      else
        warn format('No installer for %s', library)
      end
    end
  end
  # class Runner
end
# module Processing
