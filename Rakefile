require_relative './lib/picrate/version'

HOME_DIR = ENV['HOME']

task default: %i[init compile install test gem]

# depends on installed processing, with processing on path
desc 'Copy Jars'
task :init do
  processing_root = File.join(HOME_DIR, 'processing-3.4')
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
  system './mvnw javadoc:javadoc'
end

desc 'Compile'
task :compile do
  system './mvnw package'
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
