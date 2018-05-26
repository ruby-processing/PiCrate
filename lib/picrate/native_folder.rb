require 'rbconfig'

# Utility to load native binaries on Java CLASSPATH
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
    return format(LINUX_FORMAT, bit) if os =~ /linux/ && bit =~ '64'
    format(LINUX_FORMAT, ARM32)
  end

  def extension
    '*.so' if os =~ /linux/
    raise RuntimeError, "Unsupported Archicture"
  end
end
