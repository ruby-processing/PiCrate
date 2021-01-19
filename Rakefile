# frozen_string_literal: true

require_relative './lib/picrate/version'

HOME_DIR = ENV['HOME']

task default: %i[init compile install test gem]

# Currently depends on local jogl-2.4.0 jars on path ~/jogl24
desc 'Copy Jars'
task :init do
  jogl24 = File.join(HOME_DIR, 'jogl-2.4-rc2021011')
  opengl = Dir.entries(jogl24).grep(/amd64|armv6hf|aarch64/).select { |jar| jar =~ /linux/ }
  opengl.concat %w[jogl-all.jar gluegen-rt.jar]
  opengl.each do |gl|
    FileUtils.cp(File.join(jogl24, gl), File.join('.', 'lib'))
  end
end

desc 'Install'
task :install do
  FileUtils.mv "target/picrate-#{PiCrate::VERSION}.jar", 'lib'
end

desc 'Gem'
task :gem do
  system 'jruby -S gem build picrate.gemspec'
end

desc 'Document'
task :javadoc do
  system './mvnw javadoc:javadoc'
end

desc 'Compile'
task :compile do
  system './mvnw package'
end

desc 'Test'
task :test do
  system 'jruby --dev test/helper_methods_test.rb'
  system 'jruby --dev test/respond_to_test.rb' # Skip this test on Travis etc
  system 'jruby --dev test/math_tool_test.rb'
  system 'jruby --dev test/deglut_spec_test.rb'
  system 'jruby --dev test/vecmath_spec_test.rb'
end

desc 'JDeps Tool'
task :jdeps do
  system './mvnw jdeps:jdkinternals'
end

desc 'clean'
task :clean do
  Dir['./**/*.{jar,gem}'].each do |path|
    puts "Deleting #{path} ..."
    File.delete(path)
  end
  FileUtils.rm_rf('./target')
end
