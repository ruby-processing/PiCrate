require 'rbconfig'

# Utility to load native binaries on Java CLASSPATH
#HACK until jruby returns a more specific 'host_os' than 'linux'
class NativeFolder
  attr_reader :os, :bit

  LINUX_FORMAT = 'linux%s'.freeze
  ARM32 = '-armv6hf'.freeze
  # ARM64 = '-aarch64'.freeze

  def initialize
    @os = RbConfig::CONFIG['host_os'].downcase
    @bit = java.lang.System.get_property('os.arch')
  end

  def name
    if /linux/.match?(os)
      return format(LINUX_FORMAT, '64') if /amd64/.match?(bit)
      return format(LINUX_FORMAT, ARM32) if /arm/.match?(bit)
    end
    raise RuntimeError, "Unsupported Architecture"
  end

  def extension
    '*.so'
  end
end
