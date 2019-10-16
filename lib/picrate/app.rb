    include Math, MathTool, HelperMethods, Render
    # Alias some methods for familiarity for Shoes coders.
    alias oval ellipse
    alias stroke_width stroke_weight
    alias rgb color
    alias gray color

    def sketch_class
      self.class.sketch_class
    end

    def sketch_title(title)
      warn 'Sketch Title To Be Implemented'
      # surface.set_title(title)
    end

    # Keep track of what inherits from the Processing::App, because we're
    # going to want to instantiate one.
    def self.inherited(subclass)
      super(subclass)
      @sketch_class = subclass
    end

    # App should be instantiated with an optional list of options
    # and array of arguments.
    #
    # App.new
    #
    class << self
      # Handy getters and setters on the class go here:
      attr_accessor :sketch_class, :library_loader, :arguments, :options
      attr_reader :surface
      def load_libraries(*args)
        library_loader ||= LibraryLoader.new
        library_loader.load_library(*args)
      end
      alias load_library load_libraries

      def library_loaded?(library_name)
        library_loader.library_loaded?(library_name)
      end

      def load_ruby_library(*args)
        library_loader.load_ruby_library(*args)
      end

      def load_java_library(*args)
        library_loader.load_java_library(*args)
      end

      # When certain special methods get added to the sketch, we need to let
      # Processing call them by their expected Java names.
      def method_added(method_name) #:nodoc:
        return unless METHODS_TO_ALIAS.key?(method_name)
        alias_method METHODS_TO_ALIAS[method_name], method_name
      end
    end

    def library_loaded?(library_name)
      self.class.library_loaded?(library_name)
    end

    def initialize(options = {}, arguments = [])
      # Guard against invalid input.
      proxy_java_fields
      raise TypeError unless options.is_a?(Hash) && arguments.is_a?(Array)
      # Set up the sketch.
      super()
      post_initialize(options)
      Processing.app = self
      @arguments = arguments
      @options   = options
      @surface   = get_surface
      run_picrate
    end

    def size(*args)
      w, h, mode = *args
      @width ||= w
      @height ||= h
      @render_mode ||= mode
      import_opengl if /opengl/ =~ mode
      super(*args)
    end

    def post_initialize(_args)
    end

    def data_path(dat)
      dat_root = File.join(SKETCH_ROOT, 'data')
      Dir.mkdir(dat_root) unless File.exist?(dat_root)
      File.join(dat_root, dat)
    end

    private

    def import_opengl
      # Include processing opengl classes that we'd like to use:
      %w[FontTexture FrameBuffer LinePath LineStroker PGL
         PGraphics2D PGraphics3D PGraphicsOpenGL PShader
         PShapeOpenGL Texture].each do |klass|
        java_import "processing.opengl.#{klass}"
      end
    end

    def proxy_java_fields
      fields = %w[sketchPath key frameRate mousePressed keyPressed]
      methods = fields.map { |field| java_class.declared_field(field) }
      @declared_fields = Hash[fields.zip(methods)]
    end

    # When certain special methods get added to the sketch, we need to let
    # Processing call them by their expected Java names.
    def method_added(method_name) #:nodoc:
      return unless METHODS_TO_ALIAS.key?(method_name)
      alias_method METHODS_TO_ALIAS[method_name], method_name
    end
  end

  # @HACK purists may prefer 'forwardable' to the use of Proxy
  # Importing PConstants here to access the processing constants
  module Proxy
    include Math
    include HelperMethods
    include Java::ProcessingCore::PConstants

    def respond_to_missing?(symbol, include_priv = false)
      Processing.app.respond_to?(symbol, include_priv) || super
    end

    def method_missing(name, *args, &block)
      return Processing.app.send(name, *args) if Processing.app.respond_to? name
      super
    end
  end # Processing::Proxy
end # Processing
