require_relative './lib/picrate/version'

HOME_DIR = ENV['HOME']

task default: %i[init compile install test gem]

def create_manifest
  title = 'Implementation-Title: PiCrate'
  version = "Implementation-Version: #{PiCrate::VERSION}"
  File.open('MANIFEST.MF', 'w') do |f|
    f.puts(title)
    f.puts(version)
    f.puts('Class-Path: gluegen-rt.jar jog-all.jar')
  end
end

# depends on installed processing, with processing on path
desc 'Create Manifest and Copy Jars'
task :init do
  create_manifest
  # for Archlinux etc
  processing_root = File.dirname(`readlink -f $(which processing)`)
  # alternative for debian linux etc
  # processing_root = File.join(HOME_DIR, 'processing-3.5.3')
  jar_dir = File.join(processing_root, 'core', 'library')
  opengl = Dir.entries(jar_dir).grep(/amd64|armv6hf/).select { |jar| jar =~ /linux/ }
  opengl.concat %w[jogl-all.jar gluegen-rt.jar]
  opengl.each do |gl|
    FileUtils.cp(File.join(jar_dir, gl), File.join('.', 'lib'))
  end
end

desc 'Install'
task :install do
  FileUtils.mv "target/picrate-#{PiCrate::VERSION}.jar", 'lib'
end

desc 'Gem'
task :gem do
  system 'gem build picrate.gemspec'
end

desc 'Document'
task :javadoc do
  system 'mvn javadoc:javadoc'
end

desc 'Compile'
task :compile do
  system 'mvn package'
end

desc 'Test'
task :test do
  system 'jruby test/helper_methods_test.rb'
  system 'jruby test/respond_to_test.rb' # Skip this test on Travis etc
  system 'jruby test/math_tool_test.rb'
  system 'jruby test/deglut_spec_test.rb'
  system 'jruby test/vecmath_spec_test.rb'
end

desc 'clean'
task :clean do
  Dir["./**/*.{jar,gem}"].each do |path|
    puts "Deleting #{path} ..."
    File.delete(path)
  end
  FileUtils.rm_rf('./target')
end
